package com.example.project9_1;

import android.graphics.Canvas;
import android.graphics.Paint;

abstract class Shape {
    Paint paint;

    Shape(Paint paint) {
        this.paint = paint;
    }

    abstract void draw(Canvas canvas);
}