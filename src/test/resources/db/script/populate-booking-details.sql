INSERT INTO booking_details (
	id,
	check_in_date ,
	check_out_date ,
	hotel_id,
	total_price,
	booked_at,
	last_modified_at,
	user_id
) VALUES (
	'37120f8c-2f90-4eed-9a05-a99b2c12beee',
	'2024-03-15',
	'2024-03-17',
	'72314052-2101-42a5-aafe-c9e1326591c2',
	70.5,
	'2024-03-07T09:17:00',
	'2024-03-07T09:17:00',
	'd5ba2bf3-cb3b-41aa-8a5c-6c20b0da6aa9'
);

INSERT INTO booked_room (
	id,
	booking_details_id ,
	quantity,
	room_id
) VALUES (
	'ad920863-2c2a-4d1f-86b1-6deab2918634',
	'37120f8c-2f90-4eed-9a05-a99b2c12beee',
	3,
	'b0e940b7-c937-42ae-9417-816dbe0394a5'
);

INSERT INTO booked_room (
	id,
	booking_details_id ,
	quantity,
	room_id
) VALUES (
	'449ef0ac-b64e-47f8-95f7-c66b3a312226',
	'37120f8c-2f90-4eed-9a05-a99b2c12beee',
	2,
	'9ed11b12-992f-45aa-b223-195c9b752e14'
);


INSERT INTO booking_details (
	id,
	check_in_date ,
	check_out_date ,
	hotel_id,
	total_price,
	booked_at,
	last_modified_at,
	user_id
) VALUES (
	'9a6e6a50-c6d6-4f88-a9aa-70338e4af401',
	'2024-03-19',
	'2024-03-22',
	'72314052-2101-42a5-aafe-c9e1326591c2',
	55.5,
	'2024-03-07T09:17:00',
	'2024-03-07T09:17:00',
	'25d31a88-17ed-44cf-aee3-7701e0c4e5d4'
);

INSERT INTO booked_room (
	id,
	booking_details_id ,
	quantity,
	room_id
) VALUES (
	'f7246019-2eee-4654-8b18-0ac9912eea9c',
	'9a6e6a50-c6d6-4f88-a9aa-70338e4af401',
	5,
	'9ed11b12-992f-45aa-b223-195c9b752e14'
);
