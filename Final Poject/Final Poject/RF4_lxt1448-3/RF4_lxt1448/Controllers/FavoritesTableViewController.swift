//
//  FavoritesTableViewController.swift
//  RF4_lxt1448
//
//  Created by Luka Toncic on 14/11/2019.
//  Copyright © 2019 Luka Toncic. All rights reserved.
//

import UIKit

class FavoritesTableViewController: UITableViewController {
    
    var data: [Restaurant] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.rightBarButtonItem = self.editButtonItem
        let nib = UINib(nibName: "CustomCell", bundle: nil)
        tableView.register(nib, forCellReuseIdentifier: CustomCell.reuseIdentifier)
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
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
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if let detailViewController = segue.destination as? RestaurantsDetailTableViewController,
            let row = tableView.indexPathForSelectedRow?.row {
            let restaurant = data[row]
            detailViewController.restaurant = restaurant
        }
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.performSegue(withIdentifier: "detail", sender: self)
    }
    
    
}
