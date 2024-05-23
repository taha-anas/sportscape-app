package com.sportscape.api.facilityservice.dto;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportsFacilityDto {
    private String name;
    private String address;
    private String amenities;
    private String openingHour;
    private String closingHour;
    private Double reservationPrice;


}
