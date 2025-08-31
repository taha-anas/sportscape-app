package com.sportscape.api.facilityservice.dto;

import com.sportscape.api.facilityservice.model.Location;
import com.sportscape.api.facilityservice.model.SportType;
import lombok.*;

import java.time.LocalTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportsFacilityDto {
    private String name;
    private String address;
    private String amenities;
    private LocalTime openingHour;
    private LocalTime closingHour;
    private Double reservationPrice;
    private Long ownerId;
    private List<Location> locations;
    private SportType sportType;
}
