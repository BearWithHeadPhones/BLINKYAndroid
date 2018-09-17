package com.bearwithheadphones.ledz.Control;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Toast;

import com.bearwithheadphones.ledz.Interface.AnimMsg;
import com.bearwithheadphones.ledz.Interface.BTMsg;
import com.bearwithheadphones.ledz.Interface.CustomAnimMsg;
import com.bearwithheadphones.ledz.Interface.LedMsg;
import com.bearwithheadphones.ledz.Interface.StripeMsg;
import com.bearwithheadphones.ledz.MainActivity;
import com.bearwithheadphones.ledz.R;

import java.util.Random;


/**
 * Created by bartoszcwynar on 05.12.2017.
 */

public class FixedAnimationsListFragment extends Fragment {

    public FixedAnimationsListFragment() {

    }

    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);

            final View rootView = inflater.inflate(R.layout.fixed_animations, container, false);

            listView  = rootView.findViewById(R.id.animationsList);

            String[] animationList = {"clear", "fire", "strobo", "pulse", "stripe", "customAnim"};

            ArrayAdapter<String> animations = new ArrayAdapter<String>(this.getContext(), R.layout.animations_listview, animationList);

            listView.setAdapter(animations);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    String item = adapterView.getAdapter().getItem(position).toString();

                    Toast.makeText(getContext(),item,Toast.LENGTH_LONG).show();

                    if(item == "stripe")
                    {
                        sendStripe();

                    }
                    else if(item == "customAnim")
                    {

                        sendCustomAnim();
                    }
                    else
                    {
                        BTMsg.BTMessage.Builder btMessage = BTMsg.BTMessage.newBuilder();
                        btMessage.setType(BTMsg.BTMessage.Type.ANIM);
                        AnimMsg.Anim.Builder anim = AnimMsg.Anim.newBuilder();
                        anim.setAnimName(item);
                        btMessage.setAnim(anim);
                        ((MainActivity)getActivity()).sendMessage(btMessage.build().toByteArray());

                    }

                }
            });



            return rootView;

    }


    void sendStripe()
    {
        BTMsg.BTMessage.Builder btMessage = BTMsg.BTMessage.newBuilder();
        btMessage.setType(BTMsg.BTMessage.Type.STRIPE);
        StripeMsg.Stripe.Builder stripe = StripeMsg.Stripe.newBuilder();

        Random r = new Random();
        for(int x = 0;x <280;x++)
        {
            LedMsg.Led.Builder led = LedMsg.Led.newBuilder();
            led.setBlue(r.nextInt(255));
            led.setGreen(r.nextInt(255));
            led.setRed(r.nextInt(255));
            led.setIndex(x);

            stripe.addLeds(led);
        }

        btMessage.setStripe(stripe);
        ((MainActivity)getActivity()).sendMessage(btMessage.build().toByteArray());

    }

    void sendCustomAnim()
    {
        BTMsg.BTMessage.Builder btMessage = BTMsg.BTMessage.newBuilder();
        btMessage.setType(BTMsg.BTMessage.Type.CUSTOMANIM);
        Random r = new Random();
        CustomAnimMsg.CustomAnim.Builder customAnim = CustomAnimMsg.CustomAnim.newBuilder();
        customAnim.setFps(10);
        for(int i =0; i<20;i++)
        {
            StripeMsg.Stripe.Builder stripe = StripeMsg.Stripe.newBuilder();
            for(int x = 0;x <140;x++)
            {
                LedMsg.Led.Builder led = LedMsg.Led.newBuilder();
                led.setBlue(6*i%255);
                led.setGreen(12*i%255);
                led.setRed(24*i%255);
                led.setIndex(x);

                stripe.addLeds(led);
            }
            customAnim.addFrames(stripe);
        }
        btMessage.setCustomAnimation(customAnim);
        ((MainActivity)getActivity()).sendMessage(btMessage.build().toByteArray());
    }


}
