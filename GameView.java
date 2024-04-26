package com.example.brickbreaker;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameThread = new GameThread(holder, getWidth(), getHeight());
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // No implementation needed
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        gameThread.running = false;
        boolean retry = true;

        while (retry) {
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