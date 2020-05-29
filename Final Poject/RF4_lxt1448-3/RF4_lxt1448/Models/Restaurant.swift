/*
   Group ZG8
*/
import Foundation

struct Restaurant: Codable, CustomStringConvertible, Equatable{
    static func == (lhs: Restaurant, rhs: Restaurant) -> Bool {
        return lhs.name == rhs.name
    }
    
    let id: Int
    let name: String
    let about: String
    let website: URL
    let imageUrl: URL
    let type: RestaurantType
    let location: Location
    
    var description: String {
        return """
        {
        \t id: \(id)
        \t name: \(name)
        \t about: \(about)
        \t website: \(website)
        \t imageUrl: \(imageUrl)
        \t type: \(type)
        \t location: \(location)
        }
        """
    }
    
}
