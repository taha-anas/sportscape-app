package com.sportscape.api.facilityservice.service;

import com.sportscape.api.facilityservice.model.SportsFacility;
import com.sportscape.api.facilityservice.repository.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SportsFacilityService {

    @Autowired
    private SportsFacilityRepository sportsFacilityRepository;

    public List<SportsFacility> getAllSportsFacilities() {
        return sportsFacilityRepository.findAll();
    }

    public Optional<SportsFacility> getSportsFacilityById(Long id) {
        return sportsFacilityRepository.findById(id);
    }

    public SportsFacility saveSportsFacility(SportsFacility sportsFacility) {
        return sportsFacilityRepository.save(sportsFacility);
    }

    public void deleteSportsFacility(Long id) {
        sportsFacilityRepository.deleteById(id);
    }
}
