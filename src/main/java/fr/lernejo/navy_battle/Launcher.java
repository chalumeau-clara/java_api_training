package fr.lernejo.navy_battle;
import java.io.IOException;

public class Launcher {

    private static int port = 9876;
    private static final int HTTP_OK_STATUS = 200;


    public static void main(String[] args) throws IOException {
        // Create server
        HttpServerSimple server = new HttpServerSimple(port, new HttpHandlerSimple());
        // Start
        server.start();
    }
}
