package com.kenzie.app;


import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CustomHttpClient {

    static final String GET_URL = "https://jservice.kenzie.academy/api/clues/";

    //TODO: Write sendGET method that takes URL and returns response
    public static String sendGET(String URLString) {
        //** Start of GET request algorithm

        HttpClient client = HttpClient.newHttpClient();
        URI uri = URI.create(URLString);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Accept", "application/json")
                .GET()
                .build();
        try {
            HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
            int statusCode = httpResponse.statusCode();
            if (statusCode == 200) {
                return httpResponse.body();
            } else {
                return String.format("GET request failed: %d status code received", statusCode);
            }
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }
    }



//    public static List<CluesDTO> getCluesList(String httpResponseBody) throws JsonProcessingException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        TypeReference<List<CluesDTO>> typeReferenceCluesDTO = new TypeReference<>(){};
//        List<CluesDTO> allClues = objectMapper. readValue(httpResponseBody, typeReferenceCluesDTO);
//
//        return allClues;
//    }
//
//    public static String formatCluesOutput(String jsonString) throws Exception {
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper();
//            CluesDTO clue = objectMapper.readValue(jsonString, CluesDTO.class);
//            return clue.toString();
//        } catch (Exception e) {
//            return "No activity found";
//        }
//    }
//
//
//// Trying to pull specific question
//    public static String getClueType(String URL, String type) throws com.fasterxml.jackson.core.JsonProcessingException,IOException{
//
//        String newURL= formatURL(URL,"type", type);
//        String activityType = sendGET(newURL);
//
//        return activityType;
//
}

