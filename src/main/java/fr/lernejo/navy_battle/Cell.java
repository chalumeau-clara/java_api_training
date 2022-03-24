package fr.lernejo.navy_battle;

public class Cell {

    // 0 means nothing
    // 1 means boat
    // 2 means boat touch
    final int[][] cell;
    final int[] nb_rest;

    public Cell() {
        this.cell = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                cell[i][j] = 0;
            }
        }
        this.nb_rest = new int[1];
        this.nb_rest[0] = 5;
        placeBoat();
    }

    void placeBoat() {
        // Porte avion
        for (int i = 0; i < 5; i++) { cell[0][i] = 1; }
        // Croiseur
        for (int i = 0; i < 4; i++) { cell[1][i] = 1; }
        // 2 contre torpilleur
        for (int i = 0; i < 3; i++) { cell[2][i] = 1; cell[3][i] = 1; }
        // torpilleur
        for (int i = 0; i < 2; i++) { cell[4][i] = 1; }
    }

    int getNumber(int i) {
        if (i == 0)
            return 4;
        else if (i == 1)
            return 3;
        else if (i == 2 || i == 3)
            return 2;
        else
            return 1;
    }

    Consequence getConsequence(String target) {
        int i = (int)target.charAt(0) - 65;
        int j = Integer.parseInt(String.valueOf(target.charAt(1)));
        if (i == 0 && j < 5 || i == 1 && j < 4 || (i == 2 || i == 3 ) && j < 3 || i == 4 && j < 2) {
            int k = getNumber(i);
            this.cell[i][j] = 2;
            for (;k != 0; k--) {
                if (this.cell[i][k] == 1)
                    return new Consequence("hit", true); }
            this.nb_rest[0] -= 1;
            if (nb_rest[0] <= 0)
                return new Consequence("sunk", false);
            return new Consequence("sunk", true);
        }
        return new Consequence("miss", true);
    }
}
