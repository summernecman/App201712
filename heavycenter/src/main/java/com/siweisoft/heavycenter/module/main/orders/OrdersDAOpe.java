package com.siweisoft.heavycenter.module.main.orders;

//by summer on 2017-12-11.

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
import com.siweisoft.heavycenter.module.main.orders.order.OrderFrag;

import java.util.ArrayList;

public class OrdersDAOpe extends BaseDAOpe {

    ArrayList<Fragment> pages = new ArrayList<>();

    private  ArrayList<Integer> totalcounts = new ArrayList<>();

    private String[] statuss = new String[]{OrdersReq.新订单,OrdersReq.进行中订单,OrdersReq.已完成订单};



    public ArrayList<Fragment> initPages(){
        pages.clear();
        for(int i=0;i<statuss.length;i++){
            OrderFrag orderFrag = OrderFrag.getInstance(statuss[i],getFrag().get容器());
            orderFrag.getP().getD().setOrdersFrag((OrdersFrag) getFrag());
            pages.add(orderFrag);
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
