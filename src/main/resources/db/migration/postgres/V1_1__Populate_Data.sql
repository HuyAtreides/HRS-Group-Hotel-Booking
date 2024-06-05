INSERT INTO "user" (
	first_name,
	last_name,
	email,
	created_at,
	modified_at
) VALUES (
	'Phan Gia',
	'Huy',
	'phangiahuy@gmail.com',
	timezone('utc', now()),
	timezone('utc', now())
);

INSERT INTO "user" (
	first_name,
	last_name,
	email,
	created_at,
	modified_at
) VALUES (
	'Dinh',
	'Nghia',
	'Dinh.Nghia@hrs.com',
	timezone('utc', now()),
	timezone('utc', now())
);

--hotel 1
INSERT INTO hotel (
  id,
	name,
	description,
	image_url ,
	created_at ,
	modified_at
) VALUES (
  '72314052-2101-42a5-aafe-c9e1326591c2',
	'Sheraton Frankfurt Airport Hotel & Conference Center',
	'This completely renovated hotel is directly connected to Frankfurt Airport´s Terminal 1.

It offers soundproofed rooms, fitness facilities, 3 à la carte restaurants, and a cocktail bar.

The Sheraton Fitness includes fitness and cardio equipment. Massages and beauty treatments are available.

The lobby area offers computer terminals.

German specialties are served in the Frankfurt Airport Sheraton’s Taverne restaurant. The Flavors restaurant prepares international cuisine. The Eatery kitchen and bar serves American food and shows live sports events and the Davidoff Lounge is the only smokers bar at Frankfurt Airport.

The city center can be reached within 15 minutes. The hotel is only 6 minutes away from the local football stadium. Trains run from Frankfurt Airport Train Station to Frankfurt Main Station in 20 minutes.',
	'https://image.hrs.com/hotel-1.jpg',
	timezone('utc', now()),
	timezone('utc', now())
);

INSERT INTO location (
  id,
	address ,
	latitude,
	longitude,
	city,
	country,
	hotel_id
) VALUES (
  '52920a66-f256-4e6a-8097-b84f19c4f490',
	'Hugo-Eckener-Ring 15, Airport / Terminal 1, 60549 Frankfurt, Germany',
	50.051750,
	8.568590,
	'Frankfurt',
	'Germany',
	'72314052-2101-42a5-aafe-c9e1326591c2'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'52920a66-f256-4e6a-8097-b84f19c4f490',
	5,
	141.70989,
	'https://image.hrs.com/room-1.jpg',
	2,
	'Twin Room'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'52920a66-f256-4e6a-8097-b84f19c4f490',
	9,
	265.91057,
	'https://image.hrs.com/room-2.jpg',
	4,
	'Standard King Room with Sofa Bed '
);

--hotel 2

INSERT INTO hotel (
  id,
	name,
	description,
	image_url ,
	created_at ,
	modified_at
) VALUES (
  '31c02112-f2e4-4f04-b2fe-c33c4c8bef2d',
	'Hyatt Place Frankfurt Airport',
	'Located just within a 4-minute drive from Frankfurt''s International Airport, Hyatt Place Frankfurt Airport offers free WiFi, 24/7 reception and gym. Frankfurt''s city center can be reached within 12 minutes by train.

The spacious and modern rooms of Hyatt Place Frankfurt Airport feature signature Hyatt Grand Beds, 42-inch HDTV, refrigerator, a seating area and coffee and tea facilities. Other perks include such mulitfunctional docking station, in room safe and a bathroom with either bath or a shower.

Optional delicious breakfast is offered each morning which includes hot items, cheese, cold cuts, fresh juices, pastries and more. Guests are also welcome to dine in for lunch and dinner at the casual urban restaurant Zoom Glocal Dining. Perfectly packaged grab and go items such as fresh snacks, sandwiches and salads are also avaialble. 24/7 coffee and cocktail bar offers hot drinks, premium beers, wines and cocktails.

Hyatt Place also offers a total of 300 square yards of flexible meeting space.Other facilities include an e-room with printers and computer work stations.

Frankfurt Airport is 1.2 mi away. Frankfurt Messe Fairgrounds and Frankfurt''s city center is 4.3 mi away from the property. The Gateway Gardens train station next to the hotel provides connections to Frankfurt Airport, Frankfurt Central Station and the Deutsche Bank Park as well as to the Taunusanlage and Hauptwache stations in the city center.',
	'https://image.hrs.com/hotel-2.jpg',
	timezone('utc', now()),
	timezone('utc', now())
);

INSERT INTO location (
  id,
	address ,
	latitude,
	longitude,
	city,
	country,
	hotel_id
) VALUES (
  '14b52149-3c09-4d28-b3f7-da8490b2069f',
	'De-Saint-Exupéry-Strasse 4, 60549 Frankfurt, Germany',
	50.054348,
	8.596584,
	'Frankfurt',
	'Germany',
	'31c02112-f2e4-4f04-b2fe-c33c4c8bef2d'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'14b52149-3c09-4d28-b3f7-da8490b2069f',
	9,
	189.27515,
	'https://image.hrs.com/room-3.jpg',
	1,
	'Small Double Room'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'14b52149-3c09-4d28-b3f7-da8490b2069f',
	7,
	289.97119,
	'https://image.hrs.com/room-4.jpg',
	1,
	'Superior Comfort'
);

-- hotel 3

INSERT INTO hotel (
  id,
	name,
	description,
	image_url ,
	created_at ,
	modified_at
) VALUES (
  '9336d8ae-414e-4b31-9ac6-510419f1bf41',
	'MEININGER Hotel Berlin Hauptbahnhof',
	'This hotel is located in the heart of Berlin, between Berlin Central Station and the Reichstag Parliament. The MEININGER offers soundproofed rooms with modern design and a flat-screen TV.

The nonsmoking rooms at MEININGER Hotel Berlin Hauptbahnhof all feature a private bathroom. Free Wi-Fi is available in all rooms.

Breakfast can be purchased each morning in the trendy M-Lounge.

Guests are welcome to use the MEININGER''s shared kitchen, TV and stereo, or relax in the Game Zone area. A washing machine and tumble dryer are also available for a fee.

The MEININGER Berlin Hauptbahnhof offers panoramic views of the Government District and the Tiergarten Park. The Brandenburg Gate and Unter den Linden boulevard are a 10-minute walk away, and excellent public transport connections are available from Berlin Central Station.',
	'https://image.hrs.com/hotel-3.jpg',
	timezone('utc', now()),
	timezone('utc', now())
);

INSERT INTO location (
  id,
	address ,
	latitude,
	longitude,
	city,
	country,
	hotel_id
) VALUES (
  '76b0f0b2-20ec-4ac6-8a51-d9b8515f5d44',
	'Ella-Trebe-Straße 9, Mitte, 10557 Berlin, Germany',
	52.524134,
	13.367557,
	'Berlin',
	'Germany',
	'9336d8ae-414e-4b31-9ac6-510419f1bf41'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'76b0f0b2-20ec-4ac6-8a51-d9b8515f5d44',
	2,
	373.73183,
	'https://image.hrs.com/room-5.jpg',
	1,
	'Junior Suite'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'76b0f0b2-20ec-4ac6-8a51-d9b8515f5d44',
	5,
	271.00281,
	'https://image.hrs.com/room-6.jpg',
	2,
	'Premium Twin Room'
);

-- Hotel 4

INSERT INTO hotel (
  id,
	name,
	description,
	image_url ,
	created_at ,
	modified_at
) VALUES (
  'e3cf2d41-72c0-4508-b1f0-89ed38a21d78',
	'Schulz Hotel Berlin Wall at the East Side Gallery',
	'This hotel is located directly next the East Side Gallery in the cool Friedrichshain district of Berlin. The hotel features free WiFi, bar & canteen and a beer garden directly at the Berlin Wall.

All rooms offer free WiFi, a TV and a bathroom with shower and complimentary toiletries.

The Bakery offers toasted panini and several Portuguese pastries, coffee, a range of beers and soft drinks.

Other facilities for guests at Schulz Hotel Berlin Wall include a guest kitchen and a youth lounge with football table.',
	'https://image.hrs.com/hotel-4.jpg',
	timezone('utc', now()),
	timezone('utc', now())
);

INSERT INTO location (
  id,
	address ,
	latitude,
	longitude,
	city,
	country,
	hotel_id
) VALUES (
  '9f5cbf4c-4f34-4ab6-a219-80e7c36ae0c1',
	'Stralauer Platz 36, Friedrichshain-Kreuzberg, 10243 Berlin, Germany',
	52.509323,
	13.43245,
	'Berlin',
	'Germany',
	'e3cf2d41-72c0-4508-b1f0-89ed38a21d78'
);


INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'9f5cbf4c-4f34-4ab6-a219-80e7c36ae0c1',
	9,
	150.71939,
	'https://image.hrs.com/room-7.jpg',
	2,
	'Deluxe Double with River View'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'9f5cbf4c-4f34-4ab6-a219-80e7c36ae0c1',
	2,
	107.21254,
	'https://image.hrs.com/room-8.jpg',
	1,
	'Single Room'
);

-- Hotel 5

INSERT INTO hotel (
  id,
	name,
	description,
	image_url ,
	created_at ,
	modified_at
) VALUES (
  '15b1cbab-dbf0-451d-9598-4c1a5850e646',
	'The Song Vita''s Apartment',
	'A good location for a stress-free stay in Vung Tau, The Song Vita''s Apartment is an apartment surrounded by views of the river. The property features a private beach area, rooftop pool, and private parking among other facilities. With sea views, this accommodation features a balcony. The apartment offers mountain views, a sun terrace, a 24-hour front desk, and free Wifi is available throughout the property.

At the apartment complex, units have air conditioning, a seating area, a flat-screen TV with streaming services, a kitchen, a dining area, and a private bathroom with slippers, a bidet, and a hair dryer. A microwave, a fridge, and stovetop are also offered, as well as a kettle. At the apartment complex, the units have bed linen and towels.

There is a coffee shop, and packed lunches are also available.

You can play table tennis at the apartment, and bike rental and car rental are available. A water park and kids pool are available at The Song Vita''s Apartment, while guests can also relax in the garden.

Back Beach is a 9-minute walk from the accommodation, while Christ of Vung Tau is 2.1 miles from the property. The nearest airport is Tan Son Nhat International, 62 miles from The Song Vita''s Apartment, and the property offers a paid airport shuttle service.',
	'https://image.hrs.com/hotel-5.jpg',
	timezone('utc', now()),
	timezone('utc', now())
);

INSERT INTO location (
  id,
	address ,
	latitude,
	longitude,
	city,
	country,
	hotel_id
) VALUES (
  '11d54a1d-8ab9-4e0f-8e5c-80f914b8cce5',
	'28 Đường Thi Sách, Vung Tau, Vietnam',
	10.34599,
	107.08426,
	'Vung Tau',
	'Viet Nam',
	'15b1cbab-dbf0-451d-9598-4c1a5850e646'
);


INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'11d54a1d-8ab9-4e0f-8e5c-80f914b8cce5',
	7,
	39.529561,
	'https://image.hrs.com/room-8.jpg',
	3,
	'King Studio with Sofa Bed'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'11d54a1d-8ab9-4e0f-8e5c-80f914b8cce5',
	6,
	82.716571,
	'https://image.hrs.com/room-9.jpg',
	4,
	'Two-Bedroom Apartment'
);

-- Hotel 6

INSERT INTO hotel (
  id,
	name,
	description,
	image_url ,
	created_at ,
	modified_at
) VALUES (
  '4824f723-5af0-4d4d-a59b-d6bfdf9ead66',
	'Apartment 5 Stars - High Pool Infinity',
	'Apartment 5 Stars - High Pool Infinity - View Icon HCMC is a recently renovated condo hotel in Ho Chi Minh City near Nha Rong Wharf. With pool views, this accommodation provides a terrace. The condo hotel features lake views, a year-round outdoor pool, a 24-hour front desk, and free Wifi is available throughout the property.

All units at the condo hotel come with air conditioning, a seating area, a flat-screen TV with streaming services, a kitchen, a dining area, a safety deposit box, and a private bathroom with a bidet, slippers, and a hair dryer. An oven, a microwave, and fridge are also offered, as well as a kettle. At the condo hotel, the units are equipped with bed linen and towels.

Guests can also relax in the garden.

Popular points of interest near the condo hotel include Fine Arts Museum, Takashimaya Vietnam, and Ben Thanh Street Food Market. The nearest airport is Tan Son Nhat International Airport, 5 miles from Apartment 5 Stars - High Pool Infinity',
	'https://image.hrs.com/hotel-6.jpg',
	timezone('utc', now()),
	timezone('utc', now())
);

INSERT INTO location (
  id,
	address ,
	latitude,
	longitude,
	city,
	country,
	hotel_id
) VALUES (
  'aa26243c-c368-49a6-b1fc-fab242c26478',
	'151 Đường Bến Vân Đồn, Ho Chi Minh City, Vietnam',
	10.761928,
	106.699291,
	'Ho Chi Minh city',
	'Viet Nam',
	'4824f723-5af0-4d4d-a59b-d6bfdf9ead66'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'aa26243c-c368-49a6-b1fc-fab242c26478',
	2,
	68.585,
	'https://image.hrs.com/room-10.jpg',
	3,
	'Suite Apartment'
);

-- Hotel 7

INSERT INTO hotel (
  id,
	name,
	description,
	image_url ,
	created_at ,
	modified_at
) VALUES (
'3797cb05-e2bf-4af6-b248-2cb05a9e8f06',
	'Sun River Hotel & Apartment',
	'Featuring quiet street views, Sun River Hotel & Apartment in Danang features accommodations, free bikes, a garden, and a shared lounge. This property offers access to a balcony and free private parking. The condo hotel provides city views, a sun terrace, a 24-hour front desk, and free Wifi is available throughout the property.

At the condo hotel, every unit includes air conditioning, a seating area, a flat-screen TV with cable channels, a kitchen, a dining area, a safety deposit box, and a private bathroom with a walk-in shower, bathrobes, and slippers. A microwave, a fridge, and stovetop are also provided, as well as a kettle. Additional in-room amenities include wine or champagne, fruit, and chocolates or cookies.

Buffet and Asian breakfast options with warm dishes, local specialities, and fruit are available every morning at the condo hotel. For those times when you''d rather not eat out, you can have groceries delivered.

Sightseeing tours are available nearby.

Bac My An Beach is a 8-minute walk from Sun River Hotel & Apartment, while My Khe Beach is half a mile away. The nearest airport is Da Nang International, 4.3 miles from the accommodation, and the property offers a paid airport shuttle service.',
	'https://image.hrs.com/hotel-7.jpg',
	timezone('utc', now()),
	timezone('utc', now())
);

INSERT INTO location (
  id,
	address ,
	latitude,
	longitude,
	city,
	country,
	hotel_id
) VALUES (
  '9d9a8bae-3735-4747-b841-56ddb79a1479',
	'78 Che Lan Vien , Danang, Vietnam',
	16.043737,
	108.244963,
	'Da Nang',
	'Viet Nam',
	'3797cb05-e2bf-4af6-b248-2cb05a9e8f06'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'9d9a8bae-3735-4747-b841-56ddb79a1479',
	8,
	121.70857,
	'https://image.hrs.com/room-10.jpg',
	2,
	'Apartment with Balcony'
);

INSERT INTO room (
	location_id ,
	availability ,
	price,
	image_url ,
	number_of_guests,
	type
) VALUES (
	'9d9a8bae-3735-4747-b841-56ddb79a1479',
	2,
	235.17266,
	'https://image.hrs.com/room-11.jpg',
	4,
	'Two-Bedroom Apartment'
);
