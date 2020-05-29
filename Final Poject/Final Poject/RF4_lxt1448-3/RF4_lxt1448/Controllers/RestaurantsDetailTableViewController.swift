/*
   Group ZG8
*/

import UIKit

class RestaurantsDetailTableViewController: UITableViewController {
    
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var imageRestaurant: CachableImageView!
    @IBOutlet weak var textRestaurant: UITextView!
    @IBOutlet weak var typeRestaurant: UILabel!
    
    
    var restaurant: Restaurant?
    @IBOutlet weak var btn: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        guard let restaurant = restaurant else { return }
        nameLabel.text = restaurant.name
        imageRestaurant.loadImage(using: restaurant.imageUrl)
        textRestaurant.text = restaurant.about
        typeRestaurant.text = restaurant.type.description
        
    }
    @IBAction func visitWebsite(_ sender: Any) {
        if let url = restaurant?.website {UIApplication.shared.openURL(url)}
    }
    
    }

