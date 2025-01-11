package com.healthcare.system.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class NearbyHospitalController {

    // Injecting the Google Maps API key from application.properties
    @Value("${google.maps.api.key}")
    private String googleMapsApiKey;

    // REST endpoint to get nearby hospitals
    @GetMapping("/nearby-hospitals")
    public Map<String, Object> getNearbyHospitals(@RequestParam double latitude, @RequestParam double longitude) {
        String baseUrl = "https://maps.googleapis.com/maps/api/place/nearbysearch/json";

        // Construct the Google Places API URL
        String url = String.format(
                "%s?location=%f,%f&radius=5000&type=hospital&key=%s",
                baseUrl, latitude, longitude, googleMapsApiKey
        );

        // Make a GET request to the API
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(url, Map.class);

        // Return the JSON response from Google API
        return response;
    }
}
