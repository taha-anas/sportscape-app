package com.sportscape.api.clients.facility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportFacilityResponse {
    private Long id;
    private String name;
    private String address;
    private String amenities;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private Double reservationPrice;
    private Long ownerId;
    private List<Location> locations;
    private List<SportType> sportTypes;
}
