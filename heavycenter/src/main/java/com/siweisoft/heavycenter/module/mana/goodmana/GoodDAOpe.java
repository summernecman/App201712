package com.siweisoft.heavycenter.module.mana.goodmana;

//by summer on 2017-12-14.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppDAOpe;

import java.util.ArrayList;

public class GoodDAOpe extends AppDAOpe {
    public GoodDAOpe(Context context) {
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
