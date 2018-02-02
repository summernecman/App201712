package com.siweisoft.heavycenter.module.main.weigts.detail;

//by summer on 2017-12-11.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;

import java.util.ArrayList;

public class DetailDAOpe extends AppDAOpe {

    private ArrayList<String> weightMsgs = new ArrayList<>();

    public DetailDAOpe(Context context) {
        super(context);
    }


    public ArrayList<String> getWeightMsgs() {
        return weightMsgs;
    }

    public void setWeightMsgs(ArrayList<String> weightMsgs) {
        this.weightMsgs = weightMsgs;
    }
}
