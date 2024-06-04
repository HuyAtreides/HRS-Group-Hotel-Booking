package com.hrs.hotelbooking.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.Getter;

@Entity
@Table(name = "room", schema = "public")
@Getter
public class Room {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private UUID id;

    // Indicate how many rooms of this room type is available.
    @Column(name = "availability")
    private int availability;

    @Column(name = "type")
    private String type;

    // Price of this room in USD.
    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "image_url")
    private String imageURL;

    @Column(name = "number_of_guests")
    private int numberOfGuests;

    @ManyToOne
    @JoinColumn(name ="location_id")
    private Location location;
}
