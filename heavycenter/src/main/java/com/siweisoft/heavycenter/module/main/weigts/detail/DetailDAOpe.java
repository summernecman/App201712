package com.siweisoft.heavycenter.module.main.weigts.detail;

//by summer on 2017-12-11.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppDAOpe;

import java.util.ArrayList;

public class DetailDAOpe extends AppDAOpe {

    public DetailDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<50;i++){
            data.add("");
        }
        return data;
    }


}
