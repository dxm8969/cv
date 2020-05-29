/*
   Group ZG8
*/

import Foundation
import UIKit

class CustomCell: UITableViewCell {
    
    static var reuseIdentifier: String {
        return String(describing: self)
    }
    
    @IBOutlet weak var img: CachableImageView!
    @IBOutlet weak var ttl: UILabel!
    @IBOutlet weak var dsc: UILabel!
    
    func configure(restaurant: Restaurant) {
        img.loadImage(using: restaurant.imageUrl)
        ttl.text = restaurant.name
        dsc.text = restaurant.type.description
    }
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }
    
    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        
        // Configure the view for the selected state
    }
    
}
