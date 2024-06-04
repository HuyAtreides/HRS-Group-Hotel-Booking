package com.hrs.hotelbooking.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;

@Entity
@Table(name = "hotel", schema = "public")
@Getter
public class Hotel {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageURL;

    @OneToMany(mappedBy = "hotel")
    private Set<Location> locations;
}
