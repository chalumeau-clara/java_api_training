package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class HttpHandlerFire implements HttpHandler {

    private final int HTTP_ACCEPTED_STATUS = 202;
    private final int HTTP_OK_STATUS = 200;
    private final int HTTP_NOT_FOUND_STATUS = 404;
    private final int HTTP_BAD_REQUEST_STATUS = 400;
    private final int port;
    private final Cell cell;
    private final ManageBattle manageBattle;

    public HttpHandlerFire(int port, Cell cell, ManageBattle manageBattle) {
        this.port = port;
        this.cell = cell;
        this.manageBattle = manageBattle;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (!(exchange.getRequestMethod().equals("GET")) )
            exchange.sendResponseHeaders(HTTP_NOT_FOUND_STATUS, -1);
        else
            handleFire(exchange);
    }

    public void handleFire(HttpExchange exchange) throws IOException {
        exchange.getResponseHeaders().set("Content-type", "application/json");
        String query = exchange.getRequestURI().getQuery();
        String[] entry = query.split("=");
        String target = entry[1];
        Consequence resp = cell.getConsequence(target);
        String body = "{\"consequence\":\"" + resp.consequence + "\", \"shipLeft\":\"" + resp.still + "\"}";
        exchange.sendResponseHeaders(HTTP_OK_STATUS, body.length());
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(body.getBytes());}
        if (resp.still)
            this.manageBattle.fire();
    }
}
