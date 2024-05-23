package com.sportscape.api.clients.facility;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Location {
    private Long id;
    private String city;
    private String postalCode;
    private String coordinates;
}
