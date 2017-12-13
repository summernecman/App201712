package com.siweisoft.heavycenter.module.main.order;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;

import java.util.ArrayList;

public class OrderDAOpe extends BaseDAOpe {

    public OrderDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }
}
