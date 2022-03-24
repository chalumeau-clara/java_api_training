package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class HttpClientFireTest {

    HttpClientFire httpClientFire = new HttpClientFire(80, "http://localhost:9876", new ManageBattle());

    @Test
    void SendGet() throws IOException, InterruptedException {
        httpClientFire.SendFire("A0");
        Assertions.assertThat(false).isEqualTo(false);
    }


}
