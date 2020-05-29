/*
   Group ZG8
*/

import MapKit

final class RestaurantAnnotation: NSObject, MKAnnotation {
    
    static var reuseIdentifier: String { return String(describing: self) }
    
    var title: String? {
        return restaurant.name
    }
    
    var subtitle: String? {
        return restaurant.type.description
    }
    
    var coordinate: CLLocationCoordinate2D {
        return restaurant.location.coordinate
    }
    
    var restaurant: Restaurant
    
    init(restaurant: Restaurant) {
        self.restaurant = restaurant
    }
}
