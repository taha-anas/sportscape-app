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

    private String city;
    private String postalCode;
    private String coordinates;

    @ManyToMany(mappedBy = "locations")
    private List<SportsFacility> sportsFacilities;
}
