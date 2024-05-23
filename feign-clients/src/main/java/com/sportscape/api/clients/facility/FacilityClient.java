package com.sportscape.api.clients.facility;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(
        value = "user-service",
        path = "api/users"
)
public interface FacilityClient {
}
