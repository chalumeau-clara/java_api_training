package fr.lernejo.navy_battle;
import java.io.IOException;

public class Launcher {

    public static void main(String[] args) throws IOException, InterruptedException {
        if (args.length != 2 && args.length != 1) {
            System.err.println("Main usage: port or port url");
        }
        else if (args.length == 1) {
            HttpServerSimple server = new HttpServerSimple(Integer.parseInt(args[0]), "");
            server.start();
        }
        else {
            HttpServerSimple server2 = new HttpServerSimple(Integer.parseInt(args[0]), args[1]);
            server2.start();
            HttpClientStart client = new HttpClientStart(Integer.parseInt(args[0]));
            client.SendStart(args[1]);
        }

    }
}
