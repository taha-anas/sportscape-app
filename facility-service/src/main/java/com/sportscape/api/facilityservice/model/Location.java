package com.sportscape.api.facilityservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="Location_city")
    private String city;

    @Column(name="Postal_code")
    private String postalCode;

    @Column(name="Location_coordinates")
    private String coordinates;

    @ManyToMany(mappedBy = "locations")
    private List<SportsFacility> sportsFacilities;
}
