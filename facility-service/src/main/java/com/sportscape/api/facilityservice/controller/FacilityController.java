package com.sportscape.api.facilityservice.controller;

import com.sportscape.api.clients.user.UserClient;
import com.sportscape.api.clients.user.UserResponse;
import com.sportscape.api.facilityservice.dto.SportsFacilityDto;
import com.sportscape.api.facilityservice.model.Location;
import com.sportscape.api.facilityservice.model.SportType;
import com.sportscape.api.facilityservice.model.SportsFacility;
import com.sportscape.api.facilityservice.service.LocationService;
import com.sportscape.api.facilityservice.service.SportTypeService;
import com.sportscape.api.facilityservice.service.SportsFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.sportscape.api.clients.user.Role;

import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/api/facility/")
public class FacilityController {

    @Autowired
    private LocationService locationService;

    @Autowired
    private SportsFacilityService sportsFacilityService;

    @Autowired
    private SportTypeService sportTypeService;

    @Autowired
    private UserClient userClient;

    // Test endpoint
    @GetMapping("/TestGateWay")
    public ResponseEntity<String> testGateWay() {
        return ResponseEntity.ok("Hi You received this from facility service! ");
    }

    // Location Endpoints
    @GetMapping("/locations")
    public ResponseEntity<?> getAllLocations() {
        try {
            List<Location> locations = locationService.getAllLocations();
            return ResponseEntity.ok(locations);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching locations. Please try again later.");
        }
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable Long id) {
        Optional<Location> locationOptional = locationService.getLocationById(id);
        return locationOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PostMapping("/locations")
    public ResponseEntity<?> createLocation(@RequestBody Location location) {
        if (location == null) {
            return ResponseEntity.badRequest().body("Location request body is null!");
        }
        Location savedLocation = locationService.saveLocation(location);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLocation);
    }

    @DeleteMapping("/locations/{id}")
    public ResponseEntity<String> deleteLocation(@PathVariable Long id) {
        try {
            locationService.deleteLocation(id);
            return ResponseEntity.ok("Location with ID " + id + " deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Location not found with ID: " + id);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the location. Please try again later.");
        }
    }


    // SportsFacility Endpoints
    @GetMapping("/sports-facilities")
    public ResponseEntity<?> getAllSportsFacilities() {
        List<SportsFacility> sportsFacilities = sportsFacilityService.getAllSportsFacilities();
        return ResponseEntity.ok(sportsFacilities);
    }

    @GetMapping("/sports-facilities/{id}")
    public ResponseEntity<?> getSportsFacilityById(@PathVariable Long id) {
        try {
            Optional<SportsFacility> sportsFacilityOptional = sportsFacilityService.getSportsFacilityById(id);
            if (sportsFacilityOptional.isPresent()) {
                return ResponseEntity.ok(sportsFacilityOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sports Facility not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the sports facility. Please try again later.");
        }
    }


    @PostMapping("/sports-facilities")
    public ResponseEntity<?> createSportsFacility(@RequestBody SportsFacilityDto sportsFacilityDto) {
        ResponseEntity<UserResponse> userResponseEntity = userClient.getCurrentUserInfo();

        // if you successfully get userInfo
        if (userResponseEntity.getStatusCode().is2xxSuccessful()) {
            UserResponse userResponse = userResponseEntity.getBody();
            if (userResponse != null && userResponse.getRole() == Role.OWNER) {
                // if he is owner he can add facility
                SportsFacility sportsFacility = SportsFacility.builder()
                        .name(sportsFacilityDto.getName())
                        .address(sportsFacilityDto.getAddress())
                        .amenities(sportsFacilityDto.getAmenities())
                        .openingHour(LocalTime.parse(sportsFacilityDto.getOpeningHour()))
                        .closingHour(LocalTime.parse(sportsFacilityDto.getClosingHour()))
                        .reservationPrice(sportsFacilityDto.getReservationPrice())
                        .ownerId(userResponse.getId()) // from the current user which he is owner
                        .build();


                SportsFacility savedSportsFacility = sportsFacilityService.saveSportsFacility(sportsFacility);

                // Return ResponseEntity with the saved sports facility object in the body
                return ResponseEntity.ok(savedSportsFacility);
            } else {
                // User does not have the OWNER role ERROR!
                return ResponseEntity.status(401).body("User does not have the required role to add a sports facility");
            }
        } else {
            // Error occurred while fetching user info
            return ResponseEntity.status(userResponseEntity.getStatusCodeValue()).build();
        }
    }

    @DeleteMapping("/sports-facilities/{id}")
    public ResponseEntity<Void> deleteSportsFacility(@PathVariable Long id) {
        sportsFacilityService.deleteSportsFacility(id);
        return ResponseEntity.noContent().build();
    }

    // SportType Endpoints
    @GetMapping("/sport-types")
    public ResponseEntity<?> getAllSportTypes() {
        List<SportType> sportTypes = sportTypeService.getAllSportTypes();
        return ResponseEntity.ok(sportTypes);
    }

    @GetMapping("/sport-types/{id}")
    public ResponseEntity<?> getSportTypeById(@PathVariable Long id) {
        try {
            Optional<SportType> sportTypeOptional = sportTypeService.getSportTypeById(id);
            if (sportTypeOptional.isPresent()) {
                return ResponseEntity.ok(sportTypeOptional.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Sport Type not found with ID: " + id);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while fetching the sport type. Please try again later.");
        }
    }

    @PostMapping("/sport-types")
    public ResponseEntity<?> addSportType(@RequestBody SportType sportType) {
        if (sportType == null) {
            return ResponseEntity.badRequest().body("Sport Type request body cannot be null");
        }
        SportType savedSportType = sportTypeService.saveSportType(sportType);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSportType);
    }

    @DeleteMapping("/sport-types/{id}")
    public ResponseEntity<Void> deleteSportType(@PathVariable Long id) {
        sportTypeService.deleteSportType(id);
        return ResponseEntity.noContent().build();
    }


}
