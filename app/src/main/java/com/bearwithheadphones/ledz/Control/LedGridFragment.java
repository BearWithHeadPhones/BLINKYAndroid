package com.bearwithheadphones.ledz.Control;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.bearwithheadphones.ledz.Interface.AnimMsg;
import com.bearwithheadphones.ledz.Led.Led;
import com.bearwithheadphones.ledz.Led.LedView;
import com.bearwithheadphones.ledz.Led.LedViewAdapter;
import com.bearwithheadphones.ledz.MainActivity;
import com.bearwithheadphones.ledz.R;
import com.bearwithheadphones.ledz.Interface.BTMsg;
import com.bearwithheadphones.ledz.Interface.LedMsg;

import java.util.Random;

/**
 * Created by bartoszcwynar on 05.12.2017.
 */

public class LedGridFragment extends Fragment {

    public LedGridFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            final View rootView = inflater.inflate(R.layout.fragment_led_grid, container, false);

            LedViewAdapter ledViewAdapter = new LedViewAdapter(rootView.getContext());

            Random r = new Random();
            int i1 = r.nextInt(80 - 65) + 65;
            for(int i = 0; i<140; i++) {
                //ledViewAdapter.addLed(new Led(i,r.nextInt(255),r.nextInt(255),r.nextInt(255), 250));
                ledViewAdapter.addLed(new Led(i,255,255,255, 255));
            }

            for(final LedView ledView: ledViewAdapter.ledViews){
                ledView.setOnClickListener(new LedView.OnClickListener(){
                                               public void onClick(final View v) {

                                                   BTMsg.BTMessage.Builder btMessage = BTMsg.BTMessage.newBuilder();
                                                   btMessage.setType(BTMsg.BTMessage.Type.LED);

                                                   LedMsg.Led.Builder led = LedMsg.Led.newBuilder();
                                                   led.setBlue(ledView.led.blue);
                                                   led.setGreen(ledView.led.green);
                                                   led.setRed(ledView.led.red);
                                                   led.setIndex(ledView.led.index);
                                                   btMessage.setLed(led);

                                                   /*BTMsg.BTMessage.Builder btMessage = BTMsg.BTMessage.newBuilder();
                                                   btMessage.setType(BTMsg.BTMessage.Type.ANIM);
                                                   AnimMsg.Anim.Builder anim = AnimMsg.Anim.newBuilder();
                                                   anim.setAnimName("basicAnimation");
                                                   btMessage.setAnim(anim);*/
                                                   ((MainActivity)getActivity()).sendMessage(btMessage.build().toByteArray());
                                               }


                });
            }

            GridView gridView = (GridView) rootView.findViewById(R.id.ledGrid);

            gridView.setAdapter(ledViewAdapter);
            return rootView;

    }


}
