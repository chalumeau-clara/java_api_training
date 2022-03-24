package fr.lernejo.navy_battle;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CellTest {

    private final Cell cell = new Cell();

    @Test
    void placeBoat() {
        cell.placeBoat();
        int result = cell.cell[0][0];
        int expectedResult = 1;
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
        "0, 4",
        "1, 3",
        "2, 2",
        "3, 2",
        "4, 1"
    })
    void getNumber(int n, int expectedResult) {
        int result = cell.getNumber(n);
        Assertions.assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void getConsequenceMiss() {
        cell.placeBoat();
        Consequence result = cell.getConsequence("E2");
        String expectedResult = "miss";
        Assertions.assertThat(result.consequence).isEqualTo(expectedResult);
    }

    @Test
    void getConsequenceHit() {
        cell.placeBoat();
        Consequence result = cell.getConsequence("A2");
        String expectedResult = "hit";
        Assertions.assertThat(result.consequence).isEqualTo(expectedResult);
    }

    @Test
    void getConsequenceSunkTrue() {
        cell.placeBoat();
        for (int i = 0; i < 4; i++) { this.cell.cell[0][i] = 2; }
        Consequence result = cell.getConsequence("A4");
        Consequence expectedResult = new Consequence("sunk", true);
        Assertions.assertThat(result.consequence).isEqualTo(expectedResult.consequence);
        Assertions.assertThat(result.still).isEqualTo(expectedResult.still);
    }

    @Test
    void getConsequenceSunkFalse() {
        cell.placeBoat();
        for (int i = 0; i < 4; i++) { cell.cell[0][i] = 2; }
        for (int i = 0; i < 4; i++) { cell.cell[1][i] = 2; }
        for (int i = 0; i < 3; i++) { cell.cell[2][i] = 2; cell.cell[3][i] = 2; }
        for (int i = 0; i < 2; i++) { cell.cell[4][i] = 2; }
        cell.nb_rest[0] = 1;
        Consequence result = cell.getConsequence("A4");
        Consequence expectedResult = new Consequence("sunk", false);
        Assertions.assertThat(result.consequence).isEqualTo(expectedResult.consequence);
        Assertions.assertThat(result.still).isEqualTo(expectedResult.still);

    }
}
