/*
   Group ZG8
*/

import UIKit

class ImageCache {
    static let shared = ImageCache()

    private init() {}

    private let cache = NSCache<NSString, UIImage>()

    func add(image: UIImage, url: String) {
        cache.setObject(image, forKey: url as NSString)
    }

    func get(url: String) -> UIImage? {
        return cache.object(forKey: url as NSString)
    }
}

final class CachableImageView: UIImageView {
    var url: String?

    func loadImage(using url: URL) {
        downloadImage(using: url)
    }

    func loadImage(using url: String) {
        guard let imageUrl = URL(string: url) else { return }
        downloadImage(using: imageUrl)
    }

    private func downloadImage(using imageUrl: URL) {

        backgroundColor = .lightGray

        self.url = imageUrl.absoluteString

        image = nil

        if let cachedImage = ImageCache.shared.get(url: imageUrl.absoluteString) {
            image = cachedImage
            return
        }

        URLSession.shared.dataTask(with: imageUrl) { (data, response, error) in
            if error != nil {
                print(error?.localizedDescription ?? "There was an error while downloading image")
                return
            }

            guard let data = data else { return }

            DispatchQueue.main.async {
                guard let downloadedImage = UIImage(data: data) else { return }
                ImageCache.shared.add(image: downloadedImage, url: imageUrl.absoluteString)
                if imageUrl.absoluteString == self.url {
                    self.image = downloadedImage
                }
            }
        }.resume()
    }
}
