package com.example.project9_1;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends Shape{
    int startX, startY, radius;

    Circle(int startX, int startY, int stopX, int stopY, Paint paint) {
        super(paint);
        this.startX = startX;
        this.startY = startY;
        this.radius = (int) Math.sqrt(Math.pow(stopX - startX, 2) + Math.pow(stopY - startY, 2));
    }

    @Override
    void draw(Canvas canvas) {
        canvas.drawCircle(startX, startY, radius, paint);
    }
}
