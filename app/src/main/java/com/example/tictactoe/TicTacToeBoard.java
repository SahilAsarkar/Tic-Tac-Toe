package com.example.tictactoe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {

    private final int boardColor;
    private final int Xcolor;
    private final int Ocolor;
    private final int winningLineColor;
    private final Paint paint = new Paint();
    private int cellsize = getWidth()/3;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.TicTacToeBoard,0,0);

        try {
        boardColor = a.getInteger(R.styleable.TicTacToeBoard_boardColor,0);
        Xcolor = a.getInteger(R.styleable.TicTacToeBoard_Xcolor,0);
        Ocolor = a.getInteger(R.styleable.TicTacToeBoard_Ocolor,0);
        winningLineColor = a.getInteger(R.styleable.TicTacToeBoard_winningLineColor,0);
        }finally {
            a.recycle();
        }

    }

    @Override
    protected void onMeasure(int width,int height){
        super.onMeasure(width, height);

        int dimension = Math.min(getMeasuredWidth(), getMeasuredHeight());
        cellsize = dimension/3;

        setMeasuredDimension(dimension,dimension);
    }

    @Override
    protected void onDraw(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

        drawGameBoard(canvas);
    }

    private void drawGameBoard(Canvas canvas) {
        paint.setColor(boardColor);
        paint.setStrokeWidth(16);
    for(int c=1;c<3;c++){
canvas.drawLine(cellsize*c,0,cellsize*c,canvas.getWidth(), paint);
    }
        for(int r=1;r<3;r++){
            canvas.drawLine(0,cellsize*r,canvas.getWidth(),cellsize*r, paint);
        }
    }
}
