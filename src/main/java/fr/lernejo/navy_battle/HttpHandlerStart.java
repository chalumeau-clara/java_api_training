package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpHandlerStart implements HttpHandler {
        private final int HTTP_ACCEPTED_STATUS = 202;
        private final int HTTP_NOT_FOUND_STATUS = 404;
        private final int HTTP_BAD_REQUEST_STATUS = 400;
        private final int port;
        private final Cell cell;
        private final ManageBattle manageBattle;

        public HttpHandlerStart(int port, Cell cell, ManageBattle manageBattle) {
            this.port = port;
            this.cell = cell;
            this.manageBattle = manageBattle;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!(exchange.getRequestMethod().equals("POST"))) {
                exchange.sendResponseHeaders(HTTP_NOT_FOUND_STATUS, -1);
            } else {
                handlePost(exchange);
            }
        }

        public void handlePost(HttpExchange exchange) throws IOException {
            String resp = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
            System.out.println(resp);
            String body = "{\"id\":\" " + this.port + "\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"May the code win\"}";
            exchange.getResponseHeaders().set("Content-type", "application/json");
            exchange.sendResponseHeaders(HTTP_ACCEPTED_STATUS, body.length());
            try (OutputStream os = exchange.getResponseBody()){
                os.write(body.getBytes());}
            String[] split = resp.split("url\":\"");
            split = split[1].split("\", \"message");
            manageBattle.addClient(port, split[0], manageBattle);
            manageBattle.fire();
        }
}
