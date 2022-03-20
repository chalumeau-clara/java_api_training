package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpHandlerPost implements HttpHandler {
        private final int HTTP_ACCEPTED_STATUS = 202;
        private final int HTTP_NOT_FOUND_STATUS = 404;
        private final int HTTP_BAD_REQUEST_STATUS = 400;
        private final int port;
        private final Cell cell;
        private final ManageBattle manageBattle;

        public HttpHandlerPost (int port, Cell cell, ManageBattle manageBattle) {
            this.port = port;
            this.cell = cell;
            this.manageBattle = manageBattle;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if (!(exchange.getRequestMethod().equals("POST"))) {// || (exchange.getRequestMethod().equals("GET")))) {
                exchange.sendResponseHeaders(HTTP_NOT_FOUND_STATUS, -1);
            } else {
                String resp = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                String[] split = resp.split("url\":\"");
                split = split[1].split("\", \"message");
                String body = "{\"id\":\"2\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"May the code win\"}";
                exchange.getResponseHeaders().set("Content-type", "application/json");
                exchange.sendResponseHeaders(HTTP_ACCEPTED_STATUS, body.length());
                try (OutputStream os = exchange.getResponseBody()){
                    os.write(body.getBytes());
                }
                manageBattle.addClient(port, split[0], manageBattle);
                try {
                    manageBattle.fire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
}
