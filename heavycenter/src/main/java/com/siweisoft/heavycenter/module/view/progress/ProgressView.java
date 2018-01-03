package com.siweisoft.heavycenter.module.view.progress;

//by summer on 2018-01-03.

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.siweisoft.heavycenter.R;

public class ProgressView extends RelativeLayout {

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, @Nullable AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_store_progress,this,true);

    }
}
