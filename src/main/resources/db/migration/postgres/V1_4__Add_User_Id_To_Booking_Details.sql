ALTER TABLE booking_details
ADD user_id UUID REFERENCES "user" (id)