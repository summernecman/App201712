package com.siweisoft.heavycenter.module.main.msg.trans;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;

import java.util.ArrayList;

public class TransDAOpe extends BaseDAOpe {

    public TransDAOpe(Context context) {
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
