package com.siweisoft.heavycenter.module.view.item;

//by summer on 2017-12-15.

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.siweisoft.heavycenter.R;

public class ItemView extends RelativeLayout{

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(getContext()).inflate(R.layout.item_content,this,true);
    }


}

