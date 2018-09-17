package com.bearwithheadphones.ledz.Led;

/**
 * Created by bartoszcwynar on 12.04.2016.
 */
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


public class LedView extends ImageView {

    public Led led;
    public LedView(Context context,Led led) {
        super(context);
        this.led = led;
    }


    public LedView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LedView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec); // This is the key that will make the height equivalent to its width
    }


}

