CREATE TABLE "user" (
	id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
	last_name VARCHAR(100) NOT NULL,
	email VARCHAR(256) NOT NULL,
	created_at TIMESTAMP NOT NULL,
	modified_at TIMESTAMP NOT NULL
);

CREATE TABLE hotel (
	id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	name VARCHAR(200) NOT NULL,
	description TEXT,
	image_url VARCHAR(2000),
	created_at TIMESTAMP NOT NULL,
	modified_at TIMESTAMP NOT NULL
);


CREATE TABLE "location" (
	id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	address VARCHAR(200) NOT NULL,
	latitude FLOAT8 NOT NULL,
	longitude FLOAT8 NOT NULL,
	city VARCHAR(50) NOT NULL,
	country VARCHAR(60) NOT NULL,
	hotel_id UUID REFERENCES hotel (id)
);

CREATE TABLE room (
	id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	location_id UUID REFERENCES location (id),
	availability INT NOT NULL NOT NULL,
	price NUMERIC(12, 2) NOT NULL,
	image_url VARCHAR(2000),
	type VARCHAR(100) NOT NULL,
	number_of_guests INT NOT NULL,
	UNIQUE (location_id, type)
);

CREATE TABLE booking_details (
	id UUID PRIMARY KEY,
	check_in_date DATE NOT NULL,
	check_out_date DATE NOT NULL,
	hotel_id UUID REFERENCES hotel (id),
	total_price NUMERIC(12, 2) NOT NULL
);

CREATE TABLE booked_room (
  id UUID PRIMARY KEY,
	booking_details_id UUID REFERENCES booking_details (id),
	quantity INT NOT NULL,
	price NUMERIC(12, 2) NOT NULL,
	room_id UUID REFERENCES room (id),
	UNIQUE (booking_details_id, room_id)
);


