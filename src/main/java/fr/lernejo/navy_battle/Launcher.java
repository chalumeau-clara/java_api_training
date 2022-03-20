package fr.lernejo.navy_battle;
import java.io.IOException;

public class Launcher {

    private final int port = 9876;


    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length == 1) {
            HttpServerSimple server = new HttpServerSimple(Integer.parseInt(args[0]), "");
            server.start();
        }
        else if (args.length == 2) {
            HttpServerSimple server2 = new HttpServerSimple(Integer.parseInt(args[0]), args[1]);
            server2.start();
        }

    }
}
