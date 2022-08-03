package com.example.finalprojecttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent gameIntent = new Intent(getApplicationContext(), GameUIActivity.class);
        Intent animIntent = new Intent(getApplicationContext(), AnimActivity.class);

        findViewById(R.id.launch).setOnClickListener(view -> {
            gameIntent.putExtra("Names",
                    getName(R.id.p1Name, "White") + ";" + getName(R.id.p2Name, "Black"));
            startActivity(gameIntent);
        });

        findViewById(R.id.load).setOnClickListener(view -> {
            gameIntent.putExtra("Load", true);
            startActivity(gameIntent);
        });

        findViewById(R.id.animButton).setOnClickListener(view -> startActivity(animIntent));

    }

    private String getName(int id, String d) {
        String input = ((EditText) findViewById(id)).getText().toString();
        return input.isEmpty() ? d : input;
    }
}