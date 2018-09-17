package com.bearwithheadphones.ledz.Led;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by bartoszcwynar on 05.12.2017.
 */

public class Led {

    public int red = 0;
    public int green= 0;
    public int blue = 0;
    public int alpha = 0;
    public int index =0;

    public Led(int index, int red, int green, int blue, int alpha){

        this.index = index;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;

    }

    public Bitmap getBitmap(int width, int height)
    {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        Paint paint2 = new Paint();
        paint.setARGB(alpha, red, green, blue);
        paint.setARGB(0, 0, 0, 0);
        paint2.setStyle(Paint.Style.STROKE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);
        canvas.drawRect(0,0,width,height-1,paint2);
        return bitmap;
    }


}
