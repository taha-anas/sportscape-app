package com.sportscape.api.facilityservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class SportsFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String amenities;
    private String openingHour;
    private String closingHour;
    private Double reservationPrice;
    private Long ownerId;

    @ManyToMany
    @JoinTable(
            name = "location_sportsFacilities",
            joinColumns = @JoinColumn(name = "sports_facility_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<Location> locations;

    @OneToMany(mappedBy = "sportsFacility")
    private List<SportType> sportTypes;
}
