 package com.example.TesteApiNew.service;



 import com.example.TesteApiNew.model.Historico;
 import com.fasterxml.jackson.databind.JsonNode;
 import org.jetbrains.annotations.NotNull;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.stereotype.Service;
 import org.springframework.web.client.RestTemplate;

 import javax.servlet.http.HttpServletRequest;
 import java.util.Objects;

 @Service
 public class ConfigService {

     @Autowired
     private HttpServletRequest request;

     @Autowired
     private RestTemplate restTemplate;

     private ResponseEntity<JsonNode> response;

     public Historico preencherHistorico() {

         Historico historico = new Historico();

         String ipAddress = "45.229.54.251"; //obterIP();

         consomeIpVigilante(historico, ipAddress);
         consomeMetaWeather(historico);

         return historico;

     }

     private String obterIP() {

         String ipAddress = request.getHeader("x-forwarded-for");

         if (ipAddress == null) {
             ipAddress = request.getHeader("X_FORWARDED_FOR");
             if (ipAddress == null){
                 ipAddress = request.getRemoteAddr();
             }
         }
         return ipAddress;

     }

     private void consomeIpVigilante(Historico historico, String ipAddress) {

         String url = "https://ipvigilante.com/json/" + ipAddress;
         response = restTemplate.getForEntity(url, JsonNode.class);
         if (response.getStatusCode().equals(HttpStatus.OK)) {
             historico.setLongitude(Objects.requireNonNull(response.getBody()).path("data").get("longitude").textValue());
             historico.setLatitude(response.getBody().path("data").get("latitude").textValue());
         }

     }

     private void consomeMetaWeather(@NotNull Historico historico){

         String url = "https://www.metaweather.com/api/location/search/?lattlong="
                 + historico.getLatitude() + "," + historico.getLongitude();

         response = restTemplate.getForEntity(url, JsonNode.class);

         if (response.getStatusCode().equals(HttpStatus.OK)) {
             pegaTemperatura(Objects.requireNonNull(response.getBody()).
                     path(0).get("woeid").toString(), historico);
         }

     }

     private void pegaTemperatura(String woeid, Historico historico) {

         String url = "https://www.metaweather.com/api/location/" + woeid;
         response = restTemplate.getForEntity(url, JsonNode.class);

         if (response.getStatusCode().equals(HttpStatus.OK)) {
             historico.setTemperaturaMin(Objects.requireNonNull(response.getBody())
                     .path("consolidated_weather").get(0).get("min_temp").toString());
             historico.setTemperaturaMax(response.getBody().
                     path("consolidated_weather").get(0).get("max_temp").toString());
         }
     }

}


