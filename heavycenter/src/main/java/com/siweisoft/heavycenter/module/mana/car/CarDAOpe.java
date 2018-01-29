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

    public CarDAOpe(Context context) {
        super(context);
    }


    public void initPages(){
        pages.clear();
        BaseUIFrag sendFrag = new MyFrag(); sendFrag.setArguments(new Bundle());sendFrag.getArguments().putString(ValueConstant.DATA_POSITION,CarsReqBean.WHAT_FH);pages.add(sendFrag);
        BaseUIFrag myFrag = new MyFrag(); myFrag.setArguments(new Bundle());myFrag.getArguments().putString(ValueConstant.DATA_POSITION,CarsReqBean.WHAT_MY);pages.add(myFrag);
        BaseUIFrag receiptFrag = new MyFrag(); receiptFrag.setArguments(new Bundle());receiptFrag.getArguments().putString(ValueConstant.DATA_POSITION,CarsReqBean.WHAT_SH);pages.add(receiptFrag);
    }

    public ArrayList<Fragment> getPages() {
        return pages;
    }
}
