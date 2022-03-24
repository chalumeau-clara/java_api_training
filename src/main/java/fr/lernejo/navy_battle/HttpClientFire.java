package fr.lernejo.navy_battle;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpClientFire {
    private final HttpClient client;
    public final int port;
    public final String adversaryUrl;
    private final ManageBattle manageBattle;

    public HttpClientFire(int port, String url, ManageBattle manageBattle) {
        this.client = HttpClient.newHttpClient();
        this.port = port;
        this.adversaryUrl = url;
        this.manageBattle = manageBattle;
    }

    public boolean SendFire(String cell){
        HttpRequest requestPost = HttpRequest.newBuilder()
            .uri(URI.create(this.adversaryUrl + "/api/game/fire?cell=" + cell))
            .setHeader("Accept", "application/json")
            .setHeader("Content-Type", "application/json")
            .GET()
            .build();
        try{
            this.client.sendAsync(requestPost, HttpResponse.BodyHandlers.ofString()).thenAccept(resp -> System.out.println("Response: " +
                resp.statusCode() + " : " + resp.body()));
        }
        catch (Exception e) {
            System.err.println("Error while receiving response when Fire request: " + e);}
        return true;
    }
}
