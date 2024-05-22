package com.sportscape.api.facilityservice.repository;

import com.sportscape.api.facilityservice.model.SportType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportTypeRepository extends JpaRepository<SportType, Long> {
}
