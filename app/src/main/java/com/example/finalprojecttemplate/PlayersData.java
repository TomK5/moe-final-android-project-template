package com.example.finalprojecttemplate;

public class PlayersData {
    private String p1;
    private String p2;
    private int playerTurn;

    public PlayersData(String p1, String p2, int playerTurn) {
        this.p1 = p1;
        this.p2 = p2;
        this.playerTurn = playerTurn;
    }

    public String getP1() {
        return p1;
    }

    public String getP2() {
        return p2;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }
}
