package com.siweisoft.heavycenter.module.view.store;

//by summer on 2018-01-08.

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.siweisoft.heavycenter.R;

public class StoreView extends RelativeLayout {


    public StoreView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_main_store_detail_top,this,true);
    }
}
