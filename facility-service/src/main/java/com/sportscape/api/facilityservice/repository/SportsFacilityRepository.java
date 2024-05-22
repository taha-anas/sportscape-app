package com.sportscape.api.facilityservice.repository;

import com.sportscape.api.facilityservice.model.SportsFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportsFacilityRepository extends JpaRepository<SportsFacility, Long> {
}
