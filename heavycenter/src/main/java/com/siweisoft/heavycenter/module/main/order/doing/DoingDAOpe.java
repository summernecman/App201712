package com.siweisoft.heavycenter.module.main.order.doing;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;

import java.util.ArrayList;

public class DoingDAOpe extends AppDAOpe {
    public DoingDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public void orders(NetI<OrdersRes> adapter){
        OrdersReq ordersReq = new OrdersReq();
        ordersReq.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        ordersReq.setIsApp(1);
        ordersReq.setPageIndex(0);
        ordersReq.setPageSize(1000);
        ordersReq.setOrderStatus(OrdersReq.STATUS_NEW);
        NetDataOpe.Order.orders(getActivity(),ordersReq,adapter);
    }
}
