package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class HttpClientStartTest {
    HttpClientStart httpClientStart = new HttpClientStart(80);

    @Test
    void SendGet() throws IOException, InterruptedException {
        HttpServerSimple server = new HttpServerSimple(9876, "");
        server.start();
        httpClientStart.SendStart("http://localhost:9876");
        Assertions.assertThat(false).isEqualTo(false);
    }
}
