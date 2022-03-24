package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class ManageBattleTest {

    ManageBattle manageBattle = new ManageBattle();

    @ParameterizedTest
    @CsvSource({
        "1, 1, 1, 2",
        "1, 2, 9, 0"
    })
    void getNextCoords(int n, int expectedResulti, int n2, int expectedResultj) {
        manageBattle.nextCoords[0] = n;
        manageBattle.nextCoords[1] = n2;
        manageBattle.getNextCoords();
        Assertions.assertThat(manageBattle.nextCoords[0]).isEqualTo(expectedResulti);
        Assertions.assertThat(manageBattle.nextCoords[1]).isEqualTo(expectedResultj);
    }

    @Test
    void addClient() {
        manageBattle.addClient(80, "string", new ManageBattle());

        Assertions.assertThat(manageBattle.client.length).isEqualTo(1);
    }

    @Test
    void coordToChar() {
        manageBattle.nextCoords[0] = 0;
        char expected = (char)(manageBattle.nextCoords[0] + 65);
        char result = manageBattle.coordToChar();
        Assertions.assertThat(result).isEqualTo('A');
    }

    @Test
    void fire() {
        manageBattle.nextCoords[0] = 0;
        manageBattle.nextCoords[1] = 0;
        manageBattle.addClient(80, "http://localhost:9876", manageBattle);
        manageBattle.fire();
        Assertions.assertThat(manageBattle.nextCoords[1]).isEqualTo(1);
        Assertions.assertThat(String.valueOf(manageBattle.coordToChar()) + String.valueOf(manageBattle.nextCoords[1])).isEqualTo("A1");
    }

    @Test
    void fireException() {
        manageBattle.nextCoords[0] = 0;
        manageBattle.nextCoords[1] = 0;
        manageBattle.fire();
        Assertions.assertThat(manageBattle.nextCoords[1]).isEqualTo(1);
        Assertions.assertThat(String.valueOf(manageBattle.coordToChar()) + String.valueOf(manageBattle.nextCoords[1])).isEqualTo("A1");
    }
}
