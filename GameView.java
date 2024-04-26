package com.example.brickbreaker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private Slider slider;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        slider = new Slider(context, null); // Initialize the slider instance
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        slider = new Slider(context, attrs); // Initialize the slider instance
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        slider = new Slider(context, attrs); // Initialize the slider instance
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread = new GameThread(holder, getWidth(), getHeight(), slider); // Pass the slider instance
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // No implementation needed
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry && gameThread.isRunning()) { // Use isRunning() method
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // Catch and ignore the exception
            }
        }
    }

    public void setGameThread(GameThread gameThread) {
        this.gameThread = gameThread;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                if (gameThread != null && gameThread.getSlider() != null) {
                    gameThread.getSlider().updateSliderX(x - Slider.SLIDER_WIDTH / 2);
                }
                break;
        }
        return true;
    }
}