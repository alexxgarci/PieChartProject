package com.example.alex.piechart;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Alex on 12/6/16.
 */
public class CustomView extends View {
    OnCustomEventListener mListener;

    public CustomView(Context context) {
        super(context);
    }

    public interface OnCustomEventListener {
        void onEvent();
    }

    public void setCustomEventListener(OnCustomEventListener eventListener) {
        mListener = eventListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        boolean fin = false;
        if (ev.getAction()==MotionEvent.ACTION_DOWN) {
            if(mListener!=null)
                mListener.onEvent();
            fin = true;
        }
        return fin;
    }

}