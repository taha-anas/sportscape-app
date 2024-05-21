package com.sportscape.api.facilityservice.repository;

import com.sportscape.api.facilityservice.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
