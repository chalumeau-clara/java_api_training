package fr.lernejo.navy_battle;
import java.io.IOException;

public class Launcher {

    private static int port = 9876;


    public static void main(String[] args) throws IOException, InterruptedException {
        // Create server
        HttpServerSimple server = new HttpServerSimple(9876, "");
        HttpServerSimple server2 = new HttpServerSimple(8795, "http://localhost:9876");
        // Start
        server.start();
        server2.start();


    }
}
