
import Foundation

final class HaopService {
    static let shared = HaopService()
    private init() {}
    
    func getPollutant(station: Int, type: Int, time: Int, start: String, end: String, completion: @escaping Completion<[Pollutant]>) {

        // Creating new service every time so that it doesn't override previous request
        let service = NetworkService<HaopEndpoints>()
        
        service.execute(endpoint: .pollutant(station: station, type: type, time:time, start: start, end: end),
                        type: [Pollutant].self) { result in
                            switch result {
                            case .success(let data):
                                completion(.success(data))
                            case .failure(let error):
                                completion(.failure(error))
                            }
        }
        
    }
}




















