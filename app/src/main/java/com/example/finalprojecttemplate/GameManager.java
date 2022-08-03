package com.example.finalprojecttemplate;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class GameManager {
    private int currentPlayer;
    private Board board;
    private GameUiInterface gui;

    public GameManager(GameUiInterface gui) {
        this.gui = gui;
        this.currentPlayer = 1;
        this.board = new Board();
        this.gui.render(this.board);
    }

    public boolean isMoveLegal(int row, int col) {
        return this.board.getValue(row, col) == Board.EMPTY;
    }

    public void makeMove(int row, int col) {
        this.board.makeMove(row, col, currentPlayer);
        gui.render(this.board);
        currentPlayer = currentPlayer == 1 ? 2 : 1;
    }

    public void setCurrentPlayer(int p) {
        this.currentPlayer = p;
    }

    public int checkVictory() {
        return this.board.checkVictory();
    }

    public PlayersData load(FileInputStream fis) {
        gui.render(this.board);
        return this.board.load(fis);
    }

    public void save(FileOutputStream fos, int turn, String p1, String p2) {
        this.board.save(fos, turn, p1, p2);
    }
}
