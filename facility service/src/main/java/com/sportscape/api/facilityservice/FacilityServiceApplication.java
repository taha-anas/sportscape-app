package com.sportscape.api.facilityservice;

import com.sportscape.api.facilityservice.model.Location;
import com.sportscape.api.facilityservice.model.SportsFacility;
import com.sportscape.api.facilityservice.model.SportType;
import com.sportscape.api.facilityservice.repository.LocationRepository;
import com.sportscape.api.facilityservice.repository.SportTypeRepository;
import com.sportscape.api.facilityservice.repository.SportsFacilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class FacilityServiceApplication implements CommandLineRunner {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private SportsFacilityRepository sportsFacilityRepository;

    @Autowired
    private SportTypeRepository sportTypeRepository;

    public static void main(String[] args) {
        SpringApplication.run(FacilityServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert fake data into Location table
        Location location1 = new Location();
        location1.setCity("New York");
        location1.setPostalCode("10001");
        location1.setCoordinates("40.7128,-74.0060");

        Location location2 = new Location();
        location2.setCity("Los Angeles");
        location2.setPostalCode("90001");
        location2.setCoordinates("34.0522,-118.2437");

        Location location3 = new Location();
        location3.setCity("Chicago");
        location3.setPostalCode("60601");
        location3.setCoordinates("41.8781,-87.6298");

        locationRepository.saveAll(Arrays.asList(location1, location2, location3));

        // Insert fake data into SportsFacility table
        SportsFacility facility1 = new SportsFacility();
        facility1.setName("NYC Sports Center");
        facility1.setAddress("123 Main St, New York, NY");
        facility1.setAmenities("Gym, Pool");
        facility1.setOpeningHour("06:00");
        facility1.setClosingHour("22:00");
        facility1.setReservationPrice(20.0);
        facility1.setOwnerId(1L);

        SportsFacility facility2 = new SportsFacility();
        facility2.setName("LA Fitness Hub");
        facility2.setAddress("456 Grand Ave, Los Angeles, CA");
        facility2.setAmenities("Gym, Sauna");
        facility2.setOpeningHour("05:00");
        facility2.setClosingHour("23:00");
        facility2.setReservationPrice(25.0);
        facility2.setOwnerId(2L);

        SportsFacility facility3 = new SportsFacility();
        facility3.setName("Chicago Fitness Arena");
        facility3.setAddress("789 Lake Shore Dr, Chicago, IL");
        facility3.setAmenities("Gym, Pool, Track");
        facility3.setOpeningHour("06:00");
        facility3.setClosingHour("21:00");
        facility3.setReservationPrice(15.0);
        facility3.setOwnerId(3L);

        sportsFacilityRepository.saveAll(Arrays.asList(facility1, facility2, facility3));

        // Insert fake data into SportType table
        SportType sportType1 = new SportType();
        sportType1.setName("Basketball");
        sportType1.setDescription("Indoor basketball court");
        sportType1.setSportsFacility(facility1);

        SportType sportType2 = new SportType();
        sportType2.setName("Swimming");
        sportType2.setDescription("Olympic size swimming pool");
        sportType2.setSportsFacility(facility1);

        SportType sportType3 = new SportType();
        sportType3.setName("Tennis");
        sportType3.setDescription("Outdoor tennis courts");
        sportType3.setSportsFacility(facility2);

        SportType sportType4 = new SportType();
        sportType4.setName("Yoga");
        sportType4.setDescription("Yoga classes and studio");
        sportType4.setSportsFacility(facility3);

        sportTypeRepository.saveAll(Arrays.asList(sportType1, sportType2, sportType3, sportType4));

        // Add relations between locations and sports facilities
        facility1.setLocations(Arrays.asList(location1, location3));
        facility2.setLocations(Arrays.asList(location2));
        facility3.setLocations(Arrays.asList(location1, location3));

        sportsFacilityRepository.saveAll(Arrays.asList(facility1, facility2, facility3));
    }
}
