package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import javax.lang.model.type.NullType;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.http.HttpResponse;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerSimple {

    private final HttpServer server;

    public HttpServerSimple(int port, String url) throws IOException, InterruptedException {
        this.server = HttpServer.create(new InetSocketAddress(port), 0);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        this.server.setExecutor(executorService);
        // New client

        this.server.createContext("/ping", new HttpHandlerPing());
        this.server.createContext("/api/game/start", new HttpHandlerPost(port));
        this.server.createContext("/api/game/fire", new HttpHandlerPost(port));
        if (!url.equals("")) {
            // Async
            HttpClientSimple client = new HttpClientSimple(port);
            client.SendPost(url);
        }

    }

    public void start() {
        this.server.start();
    }
}
