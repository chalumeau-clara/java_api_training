package fr.lernejo.navy_battle;

public class ManageBattle {

    final int[][] adversaryCell;
    final int[] nextCoords;
    final HttpClientFire[] client;

    public ManageBattle(){
        this.adversaryCell = new int[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                adversaryCell[i][j] = 0;
            }
        }
        this.nextCoords = new int[2];
        this.nextCoords[0] = 0;
        this.nextCoords[1] = -1;
        this.client = new HttpClientFire[1];
    }

    public void getNextCoords() {
        this.nextCoords[1] = this.nextCoords[1] + 1;
        if (this.nextCoords[1] >= 10) {
            this.nextCoords[0] = (this.nextCoords[0] + 1) % 10;
            this.nextCoords[1] %= 10;
        }
    }

    public void addClient(int port, String url, ManageBattle manageBattle) {
        this.client[0] = new HttpClientFire(port, url, manageBattle);
    }

    public char coordToChar() {
        return (char)(this.nextCoords[0] + 65);
    }

    public void fire(){
        this.getNextCoords();
        try {
            this.client[0].SendFire(String.valueOf(this.coordToChar()) + String.valueOf(this.nextCoords[1]));
        }
        catch (Exception exception){
            exception.printStackTrace();
        }
    }

}
