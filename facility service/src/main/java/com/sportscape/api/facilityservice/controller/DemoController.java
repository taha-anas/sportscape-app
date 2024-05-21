package com.sportscape.api.facilityservice.controller;

import com.sportscape.api.facilityservice.model.Location;
import com.sportscape.api.facilityservice.model.SportType;
import com.sportscape.api.facilityservice.model.SportsFacility;
import com.sportscape.api.facilityservice.service.LocationService;
import com.sportscape.api.facilityservice.service.SportTypeService;
import com.sportscape.api.facilityservice.service.SportsFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DemoController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private SportsFacilityService sportsFacilityService;

    @Autowired
    private SportTypeService sportTypeService;

    // Location Endpoints
    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    @GetMapping("/locations/{id}")
    public Optional<Location> getLocationById(@PathVariable Long id) {
        return locationService.getLocationById(id);
    }

    @PostMapping("/locations")
    public Location createLocation(@RequestBody Location location) {
        return locationService.saveLocation(location);
    }

    @DeleteMapping("/locations/{id}")
    public void deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
    }

    // SportsFacility Endpoints
    @GetMapping("/sports-facilities")
    public List<SportsFacility> getAllSportsFacilities() {
        return sportsFacilityService.getAllSportsFacilities();
    }

    @GetMapping("/sports-facilities/{id}")
    public Optional<SportsFacility> getSportsFacilityById(@PathVariable Long id) {
        return sportsFacilityService.getSportsFacilityById(id);
    }

    @PostMapping("/sports-facilities")
    public SportsFacility createSportsFacility(@RequestBody SportsFacility sportsFacility) {
        return sportsFacilityService.saveSportsFacility(sportsFacility);
    }

    @DeleteMapping("/sports-facilities/{id}")
    public void deleteSportsFacility(@PathVariable Long id) {
        sportsFacilityService.deleteSportsFacility(id);
    }

    // SportType Endpoints
    @GetMapping("/sport-types")
    public List<SportType> getAllSportTypes() {
        return sportTypeService.getAllSportTypes();
    }

    @GetMapping("/sport-types/{id}")
    public Optional<SportType> getSportTypeById(@PathVariable Long id) {
        return sportTypeService.getSportTypeById(id);
    }

    @PostMapping("/sport-types")
    public SportType createSportType(@RequestBody SportType sportType) {
        return sportTypeService.saveSportType(sportType);
    }

    @DeleteMapping("/sport-types/{id}")
    public void deleteSportType(@PathVariable Long id) {
        sportTypeService.deleteSportType(id);
    }
}
