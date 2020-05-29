/*
   Group ZG8
*/

import Foundation
import CoreLocation

struct Location: Codable, CustomStringConvertible {
    let latitude: Double
    let longitude: Double
    
    var coordinate: CLLocationCoordinate2D {
        return CLLocationCoordinate2D(latitude: latitude, longitude: longitude)
    }
    
    var description: String {
        return """
        {
        \t\t latitude: \(latitude)
        \t\t longitude: \(longitude)
        \t}
        """
    }
}
