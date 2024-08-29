package com.taxi.taxiback.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LocationServiceImpl implements LocationService{

    public String distanceMatrix(String location,String destination){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String>response=restTemplate.getForEntity("https://maps.googleapis.com/maps/api/distancematrix/json\n" +
                "  ?destinations=" + destination +
                "  &origins=" + location +
                "  &units=imperial\n" +
                "  &key=AIzaSyCYkkDA-nwSfQwK8HYAgg-9vE-c9HolewU",String.class);
        return response.toString();
    }
}
