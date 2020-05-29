DROP DATABASE IF EXISTS restaurants;
CREATE DATABASE restaurants;
USE restaurants;
-- ORDER OF TABLE CREATION IS IMPORTANT

-- restaurant with name, about, adress, website, image, type, and location
CREATE TABLE restaurant(
	id int NOT NULL DEFAULT '0',
    name varchar(50) NOT NULL,
    about text NOT NULL,
    website varchar(100) NOT NULL,
    imageUrl varchar(100),
    type int,
    latitude double,
    longitude double,
    PRIMARY KEY (id)
);

-- populate

-- important !
INSERT INTO restaurant VALUES
('1', 'Duksa Pizza', 'Amazing pizza from Italy.', 'https://www.facebook.com/Duksa-Pizza-332854106795014/', 'https://media-cdn.tripadvisor.com/media/photo-s/03/23/fe/33/getlstd-property-photo.jpg', '5', '45.822175', '16.011667'),
('2', 'Canzona', 'Italian, Pizza, Mediterranean, European, Vegetarian Friendly, Vegan Options', 'http://canzona.hr/', 'https://media-cdn.tripadvisor.com/media/photo-s/0d/9e/00/8f/canzona-interior.jpg', '5', '45.807962', '15.988237'),
('3', 'Karijola', 'Italian, Pizza, European', 'http://www.pizzeria-karijola.com', 'https://media-cdn.tripadvisor.com/media/photo-s/05/74/ea/6c/karijola.jpg', '5', '45.803889', '15.958608'),
('4', 'Zero Zero Pizzeria', 'Italian, Pizza, European', 'https://www.facebook.com/Pizzeria.Zero.Zero', 'https://media-cdn.tripadvisor.com/media/photo-s/07/14/e1/82/zero-zero-pizzeria.jpg', '5', '45.813808', '15.981915'),
('5', "Kelly's Grill & Bar", 'Cafe, Fast Food, European, Croatian, Vegetarian Friendly', 'https://www.facebook.com/ZagrebKellysGrill/', 'https://media-cdn.tripadvisor.com/media/photo-s/11/81/a7/8e/kelly-s-grill-bar.jpg', '4', '45.799631', '15.912934'),
('6', "Submarine Burger Radnicka", 'All-Natural Homemade Gourmet Burgers and Superfood Salads.', 'http://submarineburger.com', 'https://submarineburger.com/wp-content/uploads/2016/12/submarine1dc.jpg', '4', '45.803678', '15.998374'),
('7', "Ali Kebaba", "'World class flavour' has been our guiding idea since the very beginning.", 'https://www.alikebaba.hr/', 'https://www.alikebaba.hr/site/assets/files/1039/onama.jpg', '4', '45.796803', '15.895420'),
('8', "Batak Grill Cvjetni", "Barbecue, European, Eastern European, Croatian", 'http://batak-grill.hr', 'http://batak-grill.hr/wp-content/uploads/2018/07/IMG_6398.jpg', '3', '45.812233', '15.973195'),
('9', "Pri Zvoncu", "Local cuisine, Steakhouse, European, Eastern European, Croatian", 'https://prizvoncu.com', 'https://prizvoncu.com/wp/wp-content/uploads/2015/11/s1.jpg', '3', '45.795315', '15.969117'),
('10', "Japanese restaurant Tekka", "Japanese restaurant Tekka offers a wide variety of japanese specialties and sake.", 'https://tekka.hr', 'https://tekka.hr/content/uploads/2015/05/IMG_4062.jpg', '2', '45.802757', '16.003147'),
('11', "Dubravkin Put", "Our professionalism and the combination of tradition and innovation will not leave you indifferent.", 'https://www.dubravkin-put.com', 'https://www.dubravkin-put.com/eng/wp-content/gallery/gallery/W5A0667.jpg', '1', '45.818528', '15.973139');