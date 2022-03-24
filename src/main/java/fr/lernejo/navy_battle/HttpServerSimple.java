package fr.lernejo.navy_battle;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServerSimple {

    private final HttpServer server;
    public final ManageBattle manageBattle = new ManageBattle();
    public final Cell cell = new Cell();

    public HttpServerSimple(int port, String url) throws IOException, InterruptedException {

        this.server = HttpServer.create(new InetSocketAddress(port), 1);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        this.server.setExecutor(executorService);
        if (!url.equals(""))
            manageBattle.addClient(port, url, manageBattle);
        this.server.createContext("/api/game/start", new HttpHandlerStart(port, cell, manageBattle));
        this.server.createContext("/ping", new HttpHandlerPing());
        this.server.createContext("/api/game/fire", new HttpHandlerFire(port, cell, manageBattle));
    }

    public void start() {
        this.server.start();
    }
}
