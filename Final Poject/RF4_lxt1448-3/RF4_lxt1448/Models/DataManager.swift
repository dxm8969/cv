/*
   Group ZG8
*/

import Foundation

protocol UpdateDelegate {
    func didUpdate(finished: Bool)
}

class DataManager {
    var delegate: UpdateDelegate?
    static let shared = DataManager()
    private var restaurants: [Restaurant] = []
    
    private init() {
        retrieveFromJson()
        
    }
    
    func getRestaurants() -> [Restaurant] {
        return restaurants
    }
    
    func getRestaurants(for type: RestaurantType) -> [Restaurant] {
        return restaurants.filter({ $0.type == type })
    }
    
    func getRestaurant(for id: Int) -> Restaurant? {
        return restaurants.first(where: { $0.id == id })
    }
    
    private func retrieveFromJson(){
        RestaurantService.shared.getRestaurants() { result in
            print(result)
            switch result {
            case .success(let data):
                self.restaurants = data;
                self.delegate?.didUpdate(finished: true)
            case .failure(let error):
                print(error)
            }
        }
    }
}

