package com.example.finalprojecttemplate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AnimActivity extends AppCompatActivity {

    public static final int FRAMES = 15;

    MyHandler handler;
    MyThread thread;

    ImageView imageView;
    private Drawable[] frames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        handler = new MyHandler(this);
        imageView = findViewById(R.id.animation);
        frames = new Drawable[FRAMES];

        for (int i = 0; i < FRAMES - 1; i++)
            frames[i] = ResourcesCompat.getDrawable(
                    getResources(),
                    getResources().getIdentifier("frame_" + i, "drawable", getPackageName()),
                    null);

        findViewById(R.id.playAnim).setOnClickListener(view -> play(imageView));
        findViewById(R.id.pauseAnim).setOnClickListener(view -> pause(imageView));
        findViewById(R.id.home).setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    public void play(View v) {
        if (thread != null) return;
        thread = new MyThread(handler);
        thread.start();
    }

    public void pause(View v) {
        if (thread == null) return;
        thread.setStop(true);
        thread = null;
    }

    public void setFrame(int n) {
        imageView.setImageDrawable(frames[n]);
    }

}