package com.example.brickbreaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Slider extends View {
    private Paint paint;
    private float sliderX;
    private float sliderWidth = 200f;
    private float sliderHeight = 50f;

    public static final float SLIDER_WIDTH = 200f;
    public static final float SLIDER_HEIGHT = 50f;

    public Slider(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.BLUE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(sliderX, getHeight() - sliderHeight, sliderX + sliderWidth, getHeight(), paint);
    }

    public void updateSliderX(float x) {
        sliderX = x;
        invalidate();
    }

    public float getSliderX() {
        return sliderX;
    }
}