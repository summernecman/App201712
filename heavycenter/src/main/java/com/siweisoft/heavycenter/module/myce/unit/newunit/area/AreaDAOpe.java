package com.siweisoft.heavycenter.module.myce.unit.newunit.area;

//by summer on 2017-12-19.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppDAOpe;

import java.util.ArrayList;

public class AreaDAOpe extends AppDAOpe {
    public AreaDAOpe(Context context) {
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
