package com.example.finalprojecttemplate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

public class GameUIActivity extends AppCompatActivity implements GameUiInterface, View.OnClickListener {

    private ImageView[][] tiles = new ImageView[Board.ROWS_NUM][Board.COLS_NUM];
    private TextView turnDisplay;
    private GameManager gm;
    private String p1Name = "White", p2Name = "Black";
//    private String p1Name = getResources().getString(R.string.default_p1_name),
//            p2Name = getResources().getString(R.string.default_p2_name);
    private Vibrator vb;
    private int turn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameui);

        vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        turnDisplay = findViewById(R.id.turnDisplay);


//        TODO: Customise the initTiles function and un-comment the below line
//        initTiles();

        findViewById(R.id.saveButton).setOnClickListener(view -> save());

        if (getIntent().hasExtra("Names")) {
            String[] names = getIntent().getStringExtra("Names").split(";");
            p1Name = names[0];
            p2Name = names[1];
        }

        gm = new GameManager(this);
        turn = 1;

        if (getIntent().hasExtra("Load")) load();

        gm.setCurrentPlayer(turn);

        turnDisplay.setText((turn == 1 ? p1Name : p2Name) + " - your turn");
    }

    public void initTiles() {
        for (int row = 0; row < Board.ROWS_NUM; row++) {
            for (int col = 0; col < Board.COLS_NUM; col++) {
                tiles[row][col] = findViewById(getResources().getIdentifier(
                        "Tile" + row + "_" + col, "id", getPackageName()));
                tiles[row][col].setOnClickListener(this);
                tiles[row][col].setClickable(true);
                tiles[row][col].setContentDescription(row + "_" + col);
            }
        }
    }

    public void victory(int status) {
        vibrate();
        VictoryDialog dialog = new VictoryDialog(
                status == 1 ? p1Name : (status == 2 ? p2Name : "tie"));
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "DIALOG_FRAGMENT");
    }

    public void vibrate() {
        vb.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
    }

    @Override
    public void onClick(View v) {
        ImageView tile = (ImageView) v;
        String[] hint = tile.getContentDescription().toString().split("_");
        int row = Integer.parseInt(hint[0]), col = Integer.parseInt(hint[1]);

        if (gm.isMoveLegal(row, col)) {
            gm.makeMove(row, col);
            turn = turn == 1 ? 2 : 1;
            turnDisplay.setText((turn == 1 ? p1Name : p2Name) + " - your turn");
        }

        int victoryStatus = gm.checkVictory();
        if (victoryStatus != 0)
            victory(victoryStatus);
    }

    @Override
    public void render(Board board) {
        HashMap<Integer, Integer> tilesID = new HashMap<>();
        tilesID.put(0, R.drawable.empty);
        tilesID.put(1, R.drawable.p1_tile);
        tilesID.put(2, R.drawable.p2_tile);
//        for (int row = 0; row < Board.ROWS_NUM; row++)
//            for (int col = 0; col < Board.COLS_NUM; col++)
//                tiles[row][col].setImageDrawable(ResourcesCompat.getDrawable(
//                                getResources(), tilesID.get(board.getValue(row, col)), null));
    }

    public void save() {
        vibrate();
        try {
            FileOutputStream fos = openFileOutput(Board.FILENAME, Context.MODE_PRIVATE);
            gm.save(fos, turn, p1Name, p2Name);
            fos.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "A problem has occurred saving the board",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    public void load() {
        try {
            FileInputStream fis = openFileInput(Board.FILENAME);
            PlayersData data = gm.load(fis);
            turn = data.getPlayerTurn();
            p1Name = data.getP1();
            p2Name = data.getP2();
            fis.close();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "A problem has occurred loading the board",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}