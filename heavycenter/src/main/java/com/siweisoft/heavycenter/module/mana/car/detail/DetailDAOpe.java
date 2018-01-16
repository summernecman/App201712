package com.siweisoft.heavycenter.module.mana.car.detail;

//by summer on 2017-12-19.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;

import java.util.ArrayList;

public class DetailDAOpe extends AppDAOpe {

    private CarsResBean.ResultsBean carinfo;

    public DetailDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public CarsResBean.ResultsBean getCarinfo() {
        return carinfo;
    }

    public void setCarinfo(CarsResBean.ResultsBean carinfo) {
        this.carinfo = carinfo;
    }
}
