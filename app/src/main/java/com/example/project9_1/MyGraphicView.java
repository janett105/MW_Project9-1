package com.example.project9_1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyGraphicView extends View {
    // 전역상수 선언, 메뉴에서 선택한 것이 선인지 원인지를 구분하기 위해 사용할 것
    final static int LINE = 1, CIRCLE = 2, RECT = 3;
    static int curShape = LINE;
    private Paint paint;
    int radius;
    private ArrayList<Shape> drawList = new ArrayList<>();

    // 시작점과 끝점 좌표를 저장히기 위한 클래스 멤버 변수 4개를 선언
    int startX = -1, startY = -1, stopX = -1, stopY = -1;
    public MyGraphicView(Context context) {super(context);}
    public MyGraphicView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setShape(int shape){curShape = shape;}

    {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.RED);
    }

    @Override
    public boolean onTouchEvent(@NonNull MotionEvent event) {
        switch (event.getAction()) {
            // 처음 터치했을 때가 선의 시작점이나 원의 중심점이 되는데 이 위치를 기억
            case MotionEvent.ACTION_DOWN :
                startX = (int) event.getX();
                startY = (int) event.getY();
                break;
            // 화면을 터치한 상태에서 드래그하는 것은 ACTION_MOVE에 해당
            case MotionEvent.ACTION_MOVE :
                stopX = (int) event.getX();
                stopY = (int) event.getY();

//                Shape tempDraw

                // invalidate()를 호출하여 동적으로 그려지는 부분을 화면에 반영
                this.invalidate();
                break;
                // 화면에서 손가락을 떼면 발생
            case MotionEvent.ACTION_UP :
                stopX = (int) event.getX();
                stopY = (int) event.getY();

                switch (curShape) {
                    case LINE :
                        drawList.add(new Line(startX, startY, stopX, stopY, paint));
                        break;
                    case CIRCLE :
                        drawList.add(new Circle(startX, startY, stopX, stopY, paint));
                        break;
                    case RECT :
                        drawList.add(new Rect(startX, startY, stopX, stopY, paint));
                        break;
                }

                // invalidate( )를 호출하면 화면이 무효화되고
                // onDraw( ) 메서드를 자동으로 실행
                this.invalidate();
                break;
        }
        return true;//onTouchEvent(null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Shape shape : drawList) {
            shape.draw(canvas);
        }

        Shape tempShape = null;
        switch (curShape) {
            case LINE :
                tempShape = new Line(startX, startY, stopX, stopY, paint);
                break;
            case CIRCLE :
                tempShape = new Circle(startX, startY, stopX, stopY, paint);
                break;
            case RECT :
                tempShape = new Rect(startX, startY, stopX, stopY, paint);
                break;
        }
        if (tempShape != null) {
            tempShape.draw(canvas);  // 현재 그려지는 도형
        }
    }
}
