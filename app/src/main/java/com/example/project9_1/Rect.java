package com.example.project9_1;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rect extends Shape{
    int startX, startY, stopX, stopY;

    Rect(int startX, int startY, int stopX, int stopY, Paint paint) {
        super(paint);
        this.startX = startX;
        this.startY = startY;
        this.stopX = stopX;
        this.stopY = stopY;
    }

    @Override
    void draw(Canvas canvas) {
        canvas.drawRect(startX, startY, stopX, stopY, paint);
    }
}
