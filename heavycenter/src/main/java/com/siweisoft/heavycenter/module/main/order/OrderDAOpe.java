package com.siweisoft.heavycenter.module.main.order;

//by summer on 2017-12-11.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.module.main.order.begin.BeginFrag;

import java.util.ArrayList;

public class OrderDAOpe extends BaseDAOpe {

    ArrayList<Fragment> pages = new ArrayList<>();

    public OrderDAOpe(Context context) {
        super(context);
    }


    public ArrayList<Fragment> initPages(){
        pages.clear();
        BeginFrag beginFrag = new BeginFrag(); beginFrag.setArguments(new Bundle());beginFrag.getArguments().putString(ValueConstant.DATA_DATA, OrdersReq.STATUS_NEW);pages.add(beginFrag);
        BeginFrag doingFrag = new BeginFrag(); doingFrag.setArguments(new Bundle());doingFrag.getArguments().putString(ValueConstant.DATA_DATA, OrdersReq.STATUS_ING);pages.add(doingFrag);
        BeginFrag doneFrag = new BeginFrag(); doneFrag.setArguments(new Bundle());doneFrag.getArguments().putString(ValueConstant.DATA_DATA, OrdersReq.STATUS_DONE);pages.add(doneFrag);
        return pages;
    }

    public ArrayList<Fragment> getPages() {
        return pages;
    }
}
