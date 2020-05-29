/*
   Group ZG8
*/

import UIKit
import MapKit

class RestaurantsMapViewController: UIViewController, MKMapViewDelegate {
    
    
    @IBOutlet weak var mapView: MKMapView!
    private let locationManager = CLLocationManager()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        mapView.delegate = self
        
        zoomToRegion()
        
        createAnnotations()
    }
    
    @IBAction func locUserAd(_ sender: Any) {
        mapView.setCenter(mapView.userLocation.coordinate, animated: true)
    }
    
    @IBAction func segAd(_ sender: UISegmentedControl) {
        switch sender.selectedSegmentIndex {
        case 0:
            mapView.mapType = .standard
        case 1:
            mapView.mapType = .satellite
        case 2:
            mapView.mapType = .hybrid
        default:
            mapView.mapType = .standard
        }
    }
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        
        checkAuthStatus()
    }
    
    private func zoomToRegion(center: CLLocationCoordinate2D = MapConfig.initialLocation, radius: CLLocationDistance = MapConfig.regionRadius) {
        
        let region = MKCoordinateRegion(center: center,
                                        latitudinalMeters: radius,
                                        longitudinalMeters: radius)
        mapView.setRegion(region, animated: true)
    }
    
    private func checkAuthStatus() {
        
        if CLLocationManager.authorizationStatus() == .authorizedWhenInUse {
            mapView.showsUserLocation = true
            
            locationManager.startUpdatingLocation()
        } else {
            locationManager.requestWhenInUseAuthorization()
        }
    }
    
    private func createAnnotations() {
        
        let places = DataManager.shared.getRestaurants()
        
        for place in places {
            mapView.addAnnotation(RestaurantAnnotation(restaurant: place))
        }
        
    }
    
    func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        
        if annotation is MKUserLocation {
            return nil
        }
        
        guard let annotation = annotation as? RestaurantAnnotation else { return nil }
        
        if let dequeuedView = mapView.dequeueReusableAnnotationView(withIdentifier: RestaurantAnnotation.reuseIdentifier)
            as? MKMarkerAnnotationView {
            
            dequeuedView.annotation = annotation
            
            return dequeuedView
            
        } else {
            
            let annotationView = MKMarkerAnnotationView(annotation: annotation, reuseIdentifier: RestaurantAnnotation.reuseIdentifier)
            
            annotationView.canShowCallout = true
            annotationView.glyphImage = UIImage(named:
                annotation.restaurant.type.annotationIconName)
            
            let infoBtn = UIButton(type: .infoLight)
            let detailBtn = UIButton(type: .detailDisclosure)
            if #available(iOS 13.0, *) {
                detailBtn.setImage(UIImage(systemName: "location"), for: .normal)
            } else {
            }
            infoBtn.tag = 0
            detailBtn.tag = 1
            
            annotationView.leftCalloutAccessoryView = infoBtn
            annotationView.rightCalloutAccessoryView = detailBtn
            
            return annotationView
        }
    }
    
    
    func mapView(_ mapView: MKMapView, annotationView view: MKAnnotationView, calloutAccessoryControlTapped control: UIControl) {
        
        guard let placeAnnotation = view.annotation as? RestaurantAnnotation else {
            return
        }
        switch control.tag {
        case 0:
            UIApplication.shared.openURL(placeAnnotation.restaurant.website)
        case 1:
            showMapsAppUsingURL(for: placeAnnotation)
        default:
            break
        }
    }
    
    
    private func showMapsAppUsingURL(for annotation: MKAnnotation) {
        guard let coordinate = locationManager.location?.coordinate else {
            showAlert(title: "No user location... can't open maps using URL 😞", message: nil, closeButtonTitle: "Ok")
            return
        }
        
        let userLat = coordinate.latitude
        let userLong = coordinate.longitude
        
        let placeLat = annotation.coordinate.latitude
        let placeLong = annotation.coordinate.longitude
        
        let urlString = "http://maps.apple.com/maps?saddr=\(userLat),\(userLong)&daddr=\(placeLat),\(placeLong)"
        
        guard let url = URL(string: urlString) else {
            return
        }
        
        UIApplication.shared.open(url, options: [:], completionHandler: nil)
    }
    
    private func showMapsAppUsingMKMapItems(for destination: MKAnnotation) {
        guard let coordinate = locationManager.location?.coordinate else {
            showAlert(title: "No user location... can't open maps using MKMapItems 😞", message: nil, closeButtonTitle: "Ok")
            return
        }
        
        openMapsApp(startingLocation: mapItem(for: coordinate, with: "Current Location"), destination: mapItem(for: destination))
    }
    
    private func openMapsApp(startingLocation: MKMapItem, destination: MKMapItem) {
        let success = MKMapItem.openMaps(with: [startingLocation, destination],
                                         launchOptions: [MKLaunchOptionsDirectionsModeKey: MKLaunchOptionsDirectionsModeDriving])
        
        if !success {
            showAlert(title: "Something went wrong while opening Maps", message: nil)
        }
    }
    
    
    private func mapItem(for annotation: MKAnnotation) -> MKMapItem {
        let placemark = MKPlacemark(coordinate: annotation.coordinate, addressDictionary: nil)
        
        let mapItem = MKMapItem(placemark: placemark)
        
        mapItem.name = annotation.title ?? "Some place"
        
        return mapItem
    }
    
    
    private func mapItem(for coordinate: CLLocationCoordinate2D, with name: String) -> MKMapItem {
        let placemark = MKPlacemark(coordinate: coordinate, addressDictionary: nil)
        
        let mapItem = MKMapItem(placemark: placemark)
        
        mapItem.name = name
        
        return mapItem
    }
    
    private func showAlert(title: String?, message: String?, closeButtonTitle: String? = "Close") {
        let alert = UIAlertController(title: title, message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: closeButtonTitle, style: .default, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }
    
}

