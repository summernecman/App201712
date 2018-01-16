package com.siweisoft.heavycenter.module.view.progress;

//by summer on 2018-01-03.

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.siweisoft.heavycenter.R;

public class ProgressView extends RelativeLayout {

    ProValue proValue = new ProValue();

    View nowV;

    View minV;


    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_store_progress,this,true);
        nowV = findViewById(R.id.now);
        minV = findViewById(R.id.min);

    }

    public void setValue(float max,float min,float now){
        proValue.max = max;
        proValue.min = min;
        proValue.now = now;

        RelativeLayout.LayoutParams p = (LayoutParams) nowV.getLayoutParams();
        p.width = (int) (getWidth()*proValue.now/proValue.max);
        nowV.setLayoutParams(p);


        RelativeLayout.LayoutParams q = (LayoutParams) minV.getLayoutParams();
        q.leftMargin = (int) (getWidth()*proValue.min/proValue.max);
        minV.setLayoutParams(q);



    }




    public ProValue getProValue() {
        return proValue;
    }
}
