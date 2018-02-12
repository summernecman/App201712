package com.siweisoft.heavycenter.module.mana.car;

//by summer on 2017-12-14.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsReqBean;
import com.siweisoft.heavycenter.module.mana.car.my.MyFrag;

import java.util.ArrayList;

public class CarDAOpe extends AppDAOpe {


    private  ArrayList<Fragment> pages = new ArrayList<>();



    public void initPages(){
        pages.clear();
        String[] str = new String[]{CarsReqBean.WHAT_FH,CarsReqBean.WHAT_MY,CarsReqBean.WHAT_SH};
        for(int i=0;i<str.length;i++){
            BaseUIFrag sendFrag = new MyFrag();
            sendFrag.setArguments(new Bundle());
            sendFrag.getArguments().putString(ValueConstant.DATA_POSITION,str[i]);
            sendFrag.getArguments().putString(ValueConstant.容器,getFrag().get容器());
            pages.add(sendFrag);
        }
    }

    public ArrayList<Fragment> getPages() {
        return pages;
    }
}
