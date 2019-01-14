package com.example.student2.surfaceapp;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
class TestSurfaceView extends SurfaceView implements SurfaceHolder.Callback{
    SurfaceHolder holder;
    public TestSurfaceView(Context context) {
        super(context);
        holder = getHolder();
        holder.addCallback(this);
    }
    //MyThread myThread;
    mThread myThread;
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        /*Canvas canvas = holder.lockCanvas();
        if (canvas != null)
        try {
            Paint p = new Paint();
            canvas.drawColor(Color.GRAY);
        }
        finally {
            holder.unlockCanvasAndPost(canvas);
        }*/
        //new MyThread(holder, getHeight(), getWidth());
        //myThread.start();
        myThread = new mThread(holder);
        myThread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //myThread.finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        myThread.setX((int)event.getX());
        myThread.setY((int)event.getY());
        myThread.setRadius(10);
        return true;
    }
}
class mThread extends Thread{
    SurfaceHolder holder;
    int radius;
    mThread(SurfaceHolder holder){
        this.holder = holder;
    }
    float x, y;

    public void run()
    {
        Paint p = new Paint();
        while (true)
        {
            radius += 5;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Canvas canvas = holder.lockCanvas();
            if (canvas != null)
                try {
                    p.setColor(Color.YELLOW);
                    canvas.drawColor(Color.BLUE);
                    canvas.drawCircle(x, y, radius, p);
                }
                finally {
                    holder.unlockCanvasAndPost(canvas);
                }

        }
    }


    public void setX(int x) {
        this.x = x;
    }


    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setY(int y) {
        this.y = y;
    }
}
