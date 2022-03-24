package fr.lernejo.navy_battle;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.assertj.core.api.Assertions;
import org.assertj.core.internal.bytebuddy.asm.Advice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class HttpServerSimpleTest {

    //HttpServerSimple httpServerClient = new HttpServerSimple(8795, "http://localhost:9876");
    //HttpServerSimple httpServer = new HttpServerSimple(9876, "");
    //HttpHandlerPost httpHandlerPost = new HttpHandlerPost(9876, cell, manageBattle);
    private HttpServer server;

    /*@BeforeEach
    void startServer() {
        httpServer.start();
        //httpServerClient.start();
    }*/

    @Test
    void ping() throws IOException {
        /*int port = 9876;
        server = HttpServer.create(new InetSocketAddress(port), 0);
        ExecutorService executorService = Executors.newFixedThreadPool(1);//Executors.newSingleThreadExecutor();
        this.server.setExecutor(executorService);
        // Create server
        ManageBattle manageBattle = new ManageBattle();
        Cell cell = new Cell();

        HttpClientSimple httpClientSimple = new HttpClientSimple(8795);
        httpClientSimple.SendPost("http://localhost:9876");
        this.server.createContext("/api/game/start", new HttpHandlerPost(port, cell, manageBattle));
        Assertions.assertThat(manageBattle.client[0].port).isEqualTo(9876);
        Assertions.assertThat(manageBattle.client[0].adversaryUrl).isEqualTo("http://localhost:8795");*/

    }
}
