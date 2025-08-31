package com.sportscape.api.clients.facility;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        value = "facility-service",
        path = "api/facility"
)
public interface FacilityClient {
    @GetMapping("/sports-facilities/{id}")
    public SportFacilityResponse getSportsFacilityById(@PathVariable("id") Long id);
}
