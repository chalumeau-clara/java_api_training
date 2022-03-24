package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

public class HttpClientStart {
    private final HttpClient client;
    public final int port;

    public HttpClientStart(int port) {
        this.client = HttpClient.newHttpClient();
        this.port = port;

    }

    public void SendStart(String adversaryUrl){
        HttpRequest requestPost = HttpRequest.newBuilder()
            .uri(URI.create(adversaryUrl + "/api/game/start"))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .POST(BodyPublishers.ofString("{\"id\":\"" + this.port + "\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"I will crush you\"}"))
            .build();
        try{
            this.client.sendAsync(requestPost, BodyHandlers.ofString()).thenAccept(resp -> System.out.println("Response: " +
                resp.statusCode() + " : " + resp.body()));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
