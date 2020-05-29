/*
   Group ZG8
*/

import Foundation

enum RestaurantType: Int, Codable, CustomStringConvertible {
    case mediterranean = 1
    case asian
    case barbecue
    case fastFood
    case pizza
    
    var description: String {
        switch self {
        case .mediterranean:
            return "Mediterranean"
        case .asian:
            return "Asian"
        case .barbecue:
            return "Barbecue"
        case .fastFood:
            return "Fast Food"
        case .pizza:
            return "Pizza"
        }
    }
    
    var annotationIconName: String {
        return "type_\(self.rawValue)"
    }
}
