/*
    Group ZG8
 */
import Foundation

enum HttpMethod: String {
    case get
    case post
    case put
    case patch
    case delete
}

protocol EndpointProvider {
    var baseUrl: String { get }
    var path: String { get }
    var httpMethod: HttpMethod { get }
    var parametersEncoding: ParameterEncoding { get }
}

typealias Parameters = [String: Any]

enum ParameterEncoding {
    case body(parameters: Parameters)
    case url(parameters: Parameters)
    case none
}

enum NetworkResponse: String, Error {
    case authenticationError = "You need to be authenticated first."
    case badRequest = "Bad request"
    case outdated = "The url you requested is outdated."
    case failed = "Network request failed."
    case noData = "Response returned with no data to decode."
    case unableToDecode = "We could not decode the response."
}

typealias Completion<T> = (Result<T, Error>) -> Void

final class NetworkService<T: EndpointProvider> {

    private var task: URLSessionTask?

    func cancel() {
        task?.cancel()
    }

    func execute<C: Codable>(endpoint: T, type: C.Type, completion: @escaping Completion<C>) {
        guard let request = createRequest(endpoint: endpoint) else { fatalError("URL cannot be created.") }
        task = URLSession.shared.dataTask(with: request, completionHandler: { data, response, error in
                        
            if error != nil {
                DispatchQueue.main.async {
                    completion(.failure(NetworkResponse.failed))
                }
            }

            if let response = response as? HTTPURLResponse {
                if let result = self.handleNetworkResponse(response) {
                    DispatchQueue.main.async {
                        completion(.failure(result))
                    }
                    return
                }

                guard let responseData = data else {
                    DispatchQueue.main.async {
                        completion(.failure(NetworkResponse.noData))
                    }
                    return
                }

                do {
                    let apiResponse = try JSONDecoder().decode(type, from: responseData)
                    DispatchQueue.main.async {
                        completion(.success(apiResponse))
                    }
                }catch {
                    print(error)
                    DispatchQueue.main.async {
                        completion(.failure(NetworkResponse.unableToDecode))
                    }
                }
            }
        })

        DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
            self.task?.resume()
        }
    }

    private func createRequest(endpoint: EndpointProvider) -> URLRequest? {
        guard let url = URL(string: "\(endpoint.baseUrl)\(endpoint.path)") else { return nil }
        var request = URLRequest(url: url, cachePolicy: .reloadIgnoringLocalCacheData, timeoutInterval: 10)
        request.httpMethod = endpoint.httpMethod.rawValue.uppercased()
        encodeParameters(request: &request, endpoint: endpoint)
        print(request)
        return request
    }

    private func encodeParameters(request: inout URLRequest, endpoint: EndpointProvider) {
        switch endpoint.parametersEncoding {
        case .body(let parameters):
            encodeBodyParameters(request: &request, with: parameters)
        case .url(let parameters):
            encodeUrlParameters(request: &request, with: parameters)
        case .none:
            return
        }
    }

    private func encodeUrlParameters(request: inout URLRequest, with parameters: Parameters) {
        guard let url = request.url else { fatalError("Missing URL") }

        if var urlComponents = URLComponents(url: url,
                                             resolvingAgainstBaseURL: false), !parameters.isEmpty {

            urlComponents.queryItems = [URLQueryItem]()

            for (key,value) in parameters {
                let queryItem = URLQueryItem(name: key,
                                             value: "\(value)".addingPercentEncoding(withAllowedCharacters: .urlHostAllowed))
                urlComponents.queryItems?.append(queryItem)
            }
            request.url = urlComponents.url
        }

        if request.value(forHTTPHeaderField: "Content-Type") == nil {
            request.setValue("application/x-www-form-urlencoded; charset=utf-8", forHTTPHeaderField: "Content-Type")
        }
    }

    private func encodeBodyParameters(request: inout URLRequest, with parameters: Parameters) {
        do {
            let data = try JSONSerialization.data(withJSONObject: parameters, options: .prettyPrinted)
            request.httpBody = data
            if request.value(forHTTPHeaderField: "Content-Type") == nil {
                request.setValue("application/json", forHTTPHeaderField: "Content-Type")
            }
        }catch {
            fatalError("Encoding failed")
        }
    }

    private func handleNetworkResponse(_ response: HTTPURLResponse) -> Error? {
        switch response.statusCode {
        case 200...299: return nil
        case 401...500: return NetworkResponse.authenticationError
        case 501...599: return NetworkResponse.badRequest
        case 600: return NetworkResponse.outdated
        default: return NetworkResponse.failed
        }
    }
}

