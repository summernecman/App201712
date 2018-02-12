package com.siweisoft.heavycenter.module.main.order;

//by summer on 2017-12-11.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.ordernum.OrderNumReq;
import com.siweisoft.heavycenter.data.netd.order.ordernum.OrderNumRes;
import com.siweisoft.heavycenter.module.main.order.begin.BeginFrag;

import java.util.ArrayList;

public class OrderDAOpe extends BaseDAOpe {

    ArrayList<Fragment> pages = new ArrayList<>();

    private  ArrayList<Integer> totalcounts = new ArrayList<>();

    private String[] statuss = new String[]{OrdersReq.STATUS_NEW,OrdersReq.STATUS_ING,OrdersReq.STATUS_DONE};



    public ArrayList<Fragment> initPages(){
        pages.clear();
        for(int i=0;i<statuss.length;i++){
            BeginFrag beginFrag = new BeginFrag();
            beginFrag.setArguments(new Bundle());
            beginFrag.getArguments().putString(ValueConstant.容器,getFrag().get容器());
            beginFrag.getArguments().putString(ValueConstant.DATA_DATA, statuss[i]);pages.add(beginFrag);
        }
        return pages;
    }

    public void getOrderCount(NetI<OrderNumRes> adapter){
        OrderNumReq orderNumReq = new OrderNumReq();
        orderNumReq.setCompanyId(LocalValue.get登录返回信息().getCompanyId());
        NetDataOpe.Order.getOrderCount(getActivity(),orderNumReq,adapter);
    }


    public ArrayList<Fragment> getPages() {
        return pages;
    }

    public ArrayList<Integer> getTotalcounts() {
        return totalcounts;
    }
}
