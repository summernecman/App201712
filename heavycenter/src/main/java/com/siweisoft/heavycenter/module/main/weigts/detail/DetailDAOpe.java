package com.siweisoft.heavycenter.module.main.weigts.detail;

//by summer on 2017-12-11.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppDAOpe;

import java.util.ArrayList;

public class DetailDAOpe extends AppDAOpe {

    private ArrayList<String> strings = new ArrayList<>();

    public DetailDAOpe(Context context) {
        super(context);
        for(int i=0;i<2;i++){
            strings.add(i+"gjrkoie[gj[opqgjeopw[qjfgeopw[qkjfgoew");
        }
    }


    public ArrayList<String> getStrings() {
        return strings;
    }

    public void setStrings(ArrayList<String> strings) {
        this.strings = strings;
    }
}
