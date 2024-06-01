package com.example.tictactoe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class TicTacToeBoard extends View {

    private final int boardColor;
    private final int Xcolor;
    private final int Ocolor;
    private final int winningLineColor;
    private final Paint paint = new Paint();

    private final GameLogic game;
    private int cellsize = getWidth()/3;

    public TicTacToeBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
game = new GameLogic();
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
        drawMarkers(canvas);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
    float x = event.getX();
    float y = event.getY();

    int action = event.getAction();
    if (action == MotionEvent.ACTION_DOWN){
        int row= (int) Math.ceil(y/cellsize);
        int col= (int) Math.ceil(x/cellsize);

        if (game.updateGameBoard(row,col)){
            invalidate();
            if (game.getPlayer() % 2 == 0){
                game.setPlayer(game.getPlayer()-1);
            }
            else {
                game.setPlayer(game.getPlayer()+1);
            }
        }

        invalidate();
        return true;
     }

      return false;
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

    private void drawMarkers(Canvas canvas){
        for (int r=0;r<3;r++){
            for (int c=0;c<3;c++){
              if (game.getGameBoard()[r][c] != 0){
                if (game.getGameBoard()[r][c] == 1){
                    drawX(canvas,r,c);
                }
                else {
                    drawO(canvas,r,c);
                }
              }
            }
        }
    }

    private void drawX(Canvas canvas,int row, int col){
        paint.setColor(Xcolor);
        canvas.drawLine((float) ((col+1)*cellsize - cellsize *0.2),
                (float) (row*cellsize+ cellsize *0.2),
                (float) (col*cellsize+ cellsize *0.2),
                (float) ((row+1)*cellsize-cellsize *0.2),paint);

        canvas.drawLine((float) (col*cellsize+ cellsize *0.2),
                (float) (row*cellsize+ cellsize *0.2),
                (float) ((col+1)*cellsize- cellsize *0.2),
                (float) ((row+1)*cellsize- cellsize *0.2),
                paint);
    }

    private void drawO(Canvas canvas,int row, int col){
        paint.setColor(Ocolor);
        canvas.drawOval((float) (col*cellsize+ cellsize *0.2),
                (float) (row*cellsize+ cellsize *0.2),
                (float) ((col*cellsize+cellsize)- cellsize *0.2),
                (float) ((row*cellsize+cellsize)- cellsize *0.2),
                paint);
    }
}
