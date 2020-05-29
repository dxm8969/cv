/*
   Group ZG8
*/

import UIKit

class RestaurantsTableViewController: UITableViewController, UpdateDelegate {
    
    private var data: [Restaurant] = []

    
    @IBAction func topSegmentedControl(_ sender: UISegmentedControl) {
        switch (sender.selectedSegmentIndex) {
        case 0:
            data = DataManager.shared.getRestaurants()
            tableView.reloadData()
        case 1:
            data = DataManager.shared.getRestaurants(for: .mediterranean)
            tableView.reloadData()
        case 2:
            data = DataManager.shared.getRestaurants(for: .asian)
            tableView.reloadData()
        case 3:
            data = DataManager.shared.getRestaurants(for: .barbecue)
            tableView.reloadData()
        case 4:
            data = DataManager.shared.getRestaurants(for: .fastFood)
            tableView.reloadData()
        case 5:
            data = DataManager.shared.getRestaurants(for: .pizza)
            tableView.reloadData()
        default:
            data = DataManager.shared.getRestaurants()
            tableView.reloadData()
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        DataManager.shared.delegate = self
        data = DataManager.shared.getRestaurants()
        let nib = UINib(nibName: "CustomCell", bundle: nil)
        tableView.register(nib, forCellReuseIdentifier: CustomCell.reuseIdentifier)
        tableView.reloadData()
    }
    
    func didUpdate(finished: Bool) {
        guard finished else {
            //blabla
            return 
        }
        data =  DataManager.shared.getRestaurants()
        tableView.reloadData()
    }

    
    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: CustomCell.reuseIdentifier, for: indexPath) as? CustomCell else {
            return UITableViewCell()
        }
        
        let restaurant = data[indexPath.row]
        
        cell.configure(restaurant: restaurant)
        
        return cell
    }
    
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.performSegue(withIdentifier: "detail", sender: self)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let detailViewController = segue.destination as? RestaurantsDetailTableViewController,
            let row = tableView.indexPathForSelectedRow?.row {
            let restaurant = data[row]
            detailViewController.restaurant = restaurant
        }
    }
}
