package com.sportscape.api.facilityservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;
import java.util.List;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SportsFacility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String amenities;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private Double reservationPrice;
    private Long ownerId;

    @ManyToMany
    @JoinTable(
            name = "location_sportsFacilities",
            joinColumns = @JoinColumn(name = "sports_facility_id"),
            inverseJoinColumns = @JoinColumn(name = "location_id")
    )
    private List<Location> locations;

    @ManyToOne()
    private SportType sportType;


}
