package com.example.brickbreaker;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private GameView gameView;
    private Slider slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView = findViewById(R.id.gameView);
        slider = findViewById(R.id.slider);

        GameThread gameThread = new GameThread(gameView.getHolder(), gameView.getWidth(), gameView.getHeight(), slider);
        gameView.setGameThread(gameThread);
    }
}