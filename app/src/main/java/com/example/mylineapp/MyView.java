package com.example.mylineapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MyView extends View {

    Random rdm = new Random();
    HashMap<Integer, Line> linesMap = new HashMap<>();

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        int pointerId = event.getPointerId(index);
        int action = event.getActionMasked();

        switch (action) {
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN:
                Line line = new Line(rdm.nextInt());
                line.lineStroke.add(new Stroke(event.getX(index), event.getY(index),event.getX(index), event.getY(index)));
                linesMap.put(pointerId, line);
                break;

            case MotionEvent.ACTION_MOVE:
                this.updateLinesValues(event);
                break;

            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_UP:
                this.linesMap.remove(pointerId);
                break;
        }

        this.invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        this.linesMap.forEach((pointerId, line) -> {
            line.lineStroke.forEach(stroke -> {
                canvas.drawLine(stroke.prevX, stroke.prevY, stroke.newX, stroke.newY, line.paint);
            });
        });
    }

    public void updateLinesValues(MotionEvent event) {
        int index = 0;
        int pointerId = 0;
        while (index != event.getPointerCount()) {
            pointerId = event.getPointerId(index);
            Line line = this.linesMap.get(pointerId);

            float prevX = line.lineStroke.get(line.lineStroke.size()-1).newX;
            float prevY = line.lineStroke.get(line.lineStroke.size()-1).newY;
            line.lineStroke.add(new Stroke(prevX, prevY, event.getX(index), event.getY(index)));

            index++;
        }
    }

}