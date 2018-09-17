package com.bearwithheadphones.ledz.Led;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by bartoszcwynar on 05.12.2017.
 */

public class LedViewAdapter extends BaseAdapter {
    private Context mContext;

    // Keep all Images in array
    public ArrayList<LedView> ledViews = new ArrayList<LedView>();

    // Constructor
    public LedViewAdapter(Context c){
        mContext = c;
    }


    public void addLed(Led led){
        LedView ledView = new LedView(mContext, led);
        ledView.setImageBitmap(led.getBitmap(100,100));
        ledView.setScaleType(ImageView.ScaleType.FIT_XY);
        ledViews.add(ledView);
    }

    @Override
    public int getCount() {
        return ledViews.size();
    }

    @Override
    public Object getItem(int position) {
        return ledViews.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return ledViews.get(position);
    }

}

