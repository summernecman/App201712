package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.heavycenter.module.view.map.MapUtil;

import java.util.ArrayList;

public class TransDAOpe extends BaseDAOpe {


    public TransDAOpe(Context context) {
        super(context);
    }

    public MapUtil getMapUtil() {
       return MapUtil.getInstance();
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public void  stopMap(){
        if(getMapUtil().getLocationClient()==null){
            return;
        }
        getMapUtil().getLocationClient().stop();
    }

    public void startMap(){
        if(getMapUtil().getLocationClient()==null){
            return;
        }
        getMapUtil().getLocationClient().start();
    }

}
