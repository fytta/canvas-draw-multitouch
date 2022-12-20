package com.example.mylineapp;

import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

public class Line {
    public Paint paint = new Paint();
    public int color = Color.BLACK;
    public ArrayList<Stroke> lineStroke = new ArrayList<Stroke>();

    Line(){
        
    }
    
    Line(int color){
        this.paint.setAntiAlias(true);
        this.paint.setStrokeWidth(6f);
        this.paint.setColor(color);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeJoin(Paint.Join.ROUND);
    }
}