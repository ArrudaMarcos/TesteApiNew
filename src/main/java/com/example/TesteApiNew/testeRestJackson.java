package com.example.TesteApiNew;

import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class testeRestJackson {



    public static void main(String[] args) {

        RestTemplate restTemplate = new RestTemplate();
        String fooResourceUrl = "https://www.metaweather.com/api/location/1521894";
        ResponseEntity<JsonNode> response = restTemplate.getForEntity(fooResourceUrl, JsonNode.class);
        String teste = response.getBody().get("consolidated_weather").get(0).get("min_temp").toString();

        if (response.getStatusCode().equals(HttpStatus.OK)) {
            System.out.println("info longitude ");
            System.out.println("info longitude ");
        }

    }

}

