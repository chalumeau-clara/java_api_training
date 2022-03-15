package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.http.HttpRequest;
import java.util.Collections;
import java.util.regex.Pattern;

public class HttpHandlerPost implements HttpHandler {
        private final int HTTP_ACCEPTED_STATUS = 202;
        private final int HTTP_NOT_FOUND_STATUS = 404;
        private final int HTTP_BAD_REQUEST_STATUS = 400;
        private final int port;

        public HttpHandlerPost (int port) {
            this.port = port;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Pattern pattern = Pattern.compile("id.*url.*message.*");
            if (!(exchange.getRequestMethod().equals("POST"))) {// || (exchange.getRequestMethod().equals("GET")))) {
                exchange.sendResponseHeaders(HTTP_NOT_FOUND_STATUS, -1);
            //} else if (!pattern.matcher(exchange.getRequestBody().toString()).matches()) {
            //    exchange.sendResponseHeaders(HTTP_BAD_REQUEST_STATUS, -1);
            } else {
                String body = "{\"id\":\"2\", \"url\":\"http://localhost:" + this.port + "\", \"message\":\"May the code win\"}";
                exchange.getResponseHeaders().set("Content-type", "application/json");
                exchange.sendResponseHeaders(HTTP_ACCEPTED_STATUS, body.length());
                // Create new server
                try (OutputStream os = exchange.getResponseBody()){
                    os.write(body.getBytes());
                }
            }
        }
}
