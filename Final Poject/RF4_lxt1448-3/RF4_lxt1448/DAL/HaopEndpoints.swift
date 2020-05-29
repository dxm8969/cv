
import Foundation

enum HaopEndpoints: EndpointProvider {
    case pollutant(station: Int, type: Int, time: Int, start: String, end: String)
    
    var baseUrl: String {
        return "http://iszz.azo.hr/iskzl/rs/podatak/export/json"
    }
        
    var path: String {
        return ""
    }
    
    var httpMethod: HttpMethod {
        switch self {
        case .pollutant:
            return .get
        }
    }
    
    var parametersEncoding: ParameterEncoding {
        switch self {
        case .pollutant(let station, let type, let time, let start, let end):
            return .url(parameters: ["postaja": station, "polutant": type, "tipPodatka": time, "vrijemeOd": start, "vrijemeDo": end])
        default:
            return .none
        }
    }
    
}
