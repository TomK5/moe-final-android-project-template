package com.example.finalprojecttemplate;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Board {
    public static final String FILENAME = "board.txt";
    public static final int EMPTY = 0;
    int[][] matrix;

    public static final int ROWS_NUM = 6;
    public static final int COLS_NUM = 6;

    public Board() {
        this.matrix = new int[ROWS_NUM][COLS_NUM];
    }

    // Initialises the board - fill each tile.
    // NOTE: int arrays are initialised automatically by Java to be filled with 0s.
    private void initBoard() {
        // Additional changes
    }

    // Returns the state of a specific tile on the board, by row and col values.
    // NOTE: 0 = empty, 1 = player 1, 2 = player 2.
    public int getValue(int row, int col) {
        return matrix[row][col];
    }

    // Set the state of a tile on the board based on the player whose this turn is.
    // NOTE: 1 = white, 2 = black.
    public void makeMove(int row, int col, int player) {
        this.matrix[row][col] = player;
    }

    // Returns the winning player number, 0 for no victory, -1 for a tie.
    public int checkVictory() {
        return 0;
    }

    // Save the current state of the board to a .txt file.
    public void save(FileOutputStream fos, int turn, String p1, String p2) {
        try {
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter writer = new BufferedWriter(osw);
            for (int i = 0; i < this.matrix.length; i++)
                for (int j = 0; j < this.matrix[i].length; j++)
                    if (this.matrix[i][j] != 0)
                        writer.append(i + ";" + j + ";" + this.matrix[i][j] + "\n");
            writer.append("turn;" + turn + ";" + p1 + ";" + p2);
            writer.close();
            osw.close();
            fos.close();
        } catch (Exception ignored) {
        }
    }

    // Load the current state of the board from a .txt file.
    public PlayersData load(FileInputStream fis) {
        PlayersData ret = null;
        try {
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader reader = new BufferedReader(isr);

            // Replace with init
            for (int i = 0; i < this.matrix.length; i++)
                for (int j = 0; j < this.matrix[i].length; j++)
                    this.matrix[i][j] = 0;

            String strLine = reader.readLine();

            while (strLine != null) {
                String[] data = strLine.split(";");

                if (strLine.startsWith("turn"))
                    ret = new PlayersData(data[2], data[3], Integer.parseInt(data[1]));
                else
                    this.matrix[Integer.parseInt(data[0])][Integer.parseInt(data[1])] =
                            Integer.parseInt(data[2]);

                strLine = reader.readLine();
            }
        } catch (Exception ignored) {
        }
        return ret;
    }
}
