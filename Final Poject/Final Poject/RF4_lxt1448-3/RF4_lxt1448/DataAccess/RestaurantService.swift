/*
   Group ZG8
*/
import Foundation

final class RestaurantService {
    static let shared = RestaurantService()
    private init() {}
    
    func getRestaurants(completion: @escaping Completion<[Restaurant]>) {

        // Creating new service every time so that it doesn't override previous request
        let service = NetworkService<RestaurantEndpoints>()
        
        service.execute(endpoint: .restaurants,
                        type: [Restaurant].self) { result in
                            switch result {
                            case .success(let data):
                                completion(.success(data))
                            case .failure(let error):
                                completion(.failure(error))
                            }
        }
        
    }
}




















