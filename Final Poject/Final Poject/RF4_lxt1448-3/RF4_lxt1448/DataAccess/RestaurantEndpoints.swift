/*
   Group ZG8
*/
import Foundation

enum RestaurantEndpoints: EndpointProvider {
    case restaurants
    
    var baseUrl: String {
        return "http://localhost:4567/restaurants"
    }
        
    var path: String {
        return ""
    }
    
    var httpMethod: HttpMethod {
        switch self {
        case .restaurants:
            return .get
        }
    }
    
    var parametersEncoding: ParameterEncoding {
        switch self {
        case .restaurants:
            return .url(parameters: [:])
        default:
            return .none
        }
    }
    
}
