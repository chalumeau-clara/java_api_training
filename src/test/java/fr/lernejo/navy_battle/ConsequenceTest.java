package fr.lernejo.navy_battle;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ConsequenceTest {

    @Test
    void getConsequence() {
        Consequence result = new Consequence("still", false);
        Assertions.assertThat(result.consequence).isEqualTo("still");
        Assertions.assertThat(result.still).isEqualTo(false);
    }

}
