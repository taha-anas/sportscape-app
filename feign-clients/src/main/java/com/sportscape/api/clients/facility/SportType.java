package com.sportscape.api.clients.facility;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SportType {
    private Long id;
    private String name;
    private String description;

}
