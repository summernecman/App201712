package com.siweisoft.heavycenter.module.mana.storemana;

//by summer on 2017-12-14.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppDAOpe;

import java.util.ArrayList;

public class StoreDAOpe extends AppDAOpe {
    public StoreDAOpe(Context context) {
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
