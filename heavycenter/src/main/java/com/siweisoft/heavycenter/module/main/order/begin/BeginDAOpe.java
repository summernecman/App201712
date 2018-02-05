package com.siweisoft.heavycenter.module.main.order.begin;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.data.netd.order.receipt.ReceiptOrderReq;
import com.siweisoft.heavycenter.data.netd.order.receipt.ReceiptOrderRes;

import java.util.ArrayList;

public class BeginDAOpe extends AppDAOpe {

    private int pageIndex = NetValue.PAGE_INDEX_START;

    private OrdersRes ordersRes = new OrdersRes();

    public BeginDAOpe(Context context) {
        super(context);
    }


    public void orders(String type,int pageIndex,NetI<OrdersRes> adapter){
        OrdersReq ordersReq = new OrdersReq();
        ordersReq.setCompanyId(LocalValue.get登录返回信息().getCompanyId());
        ordersReq.setIsApp(1);
        ordersReq.setPageIndex(pageIndex);
        ordersReq.setPageSize(10);
        ordersReq.setOrderStatus(type);
        NetDataOpe.Order.orders(getActivity(),ordersReq,adapter);
    }

    public void receipt(int id, int state, NetI<ReceiptOrderRes> adapter){
        ReceiptOrderReq receiptOrderReq = new ReceiptOrderReq();
        receiptOrderReq.setAuditor(LocalValue.get登录返回信息().getUserId());
        receiptOrderReq.setAuditState(state);
        receiptOrderReq.setId(id);
        NetDataOpe.Order.receipt(getActivity(),receiptOrderReq,adapter);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public OrdersRes getOrdersRes() {
        return ordersRes;
    }
}
