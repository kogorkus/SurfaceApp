package com.example.student2.surfaceapp;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

public class MyThread extends Thread {
    private boolean working = true;
    SurfaceHolder holder;
    int height, weight;
    float speedX = 1;
    MyThread(SurfaceHolder holder, int height, int weight){
        this.holder = holder;
        this.height = height;
        this.weight = weight;
    }
    public void run()
    {
        int x = 100, y = 100;

        int speedY = 1;
        while (working)
        {
            Canvas canvas = holder.lockCanvas();
            if( x == weight || x == 0) speedX = -speedX;
            if( y == height || y == 0) speedY = -speedY;
            x += speedX;
            y += speedY;
        if (canvas != null)
        try {
            Paint p = new Paint();
            canvas.drawColor(Color.argb(255,    255,255,255));
            canvas.drawCircle(x, y, 10, p);
        }
        finally {
            holder.unlockCanvasAndPost(canvas);
        }

        }
    }
    public void finish()
    {
        working = false;
    }
}
