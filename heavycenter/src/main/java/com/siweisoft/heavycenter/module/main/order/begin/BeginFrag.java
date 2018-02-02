package com.siweisoft.heavycenter.module.main.order.begin;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.Test;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.order.detail.DetailFrag;

import java.io.Serializable;

public class BeginFrag extends AppFrag<BeginUIOpe,BeginDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh(this,this);
        onRefresh(null);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                Bundle bundle = new Bundle();
                bundle.putString(ValueConstant.TYPE,(String)v.getTag(R.id.type));
                OrdersRes.ResultsBean resultsBean = (OrdersRes.ResultsBean) v.getTag(R.id.data);
                bundle.putInt(ValueConstant.DATA_DATA, resultsBean.getOrderId());
                FragManager2.getInstance().start(getBaseUIActivity(), MainAct.订单,MainAct.订单ID,new DetailFrag(),bundle);
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getD().setPageIndex(getP().getD().getPageIndex()+1);
        getP().getD().orders(getArguments().getString(ValueConstant.DATA_DATA),getP().getD().getPageIndex(),new UINetAdapter<OrdersRes>(activity) {
            @Override
            public void onResult(boolean success, String msg, OrdersRes o) {
                super.onResult(success, msg, o);
                //o = new Test().getOrdersRes();
                getP().getU().finishLoadmore();
                if(o==null||o.getResults()==null){
                    return;
                }
                getP().getD().getOrdersRes().getResults().addAll(o.getResults());
                getP().getU().notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().setPageIndex(NetValue.PAGE_INDEX_START);
        getP().getD().getOrdersRes().getResults().clear();
        getP().getD().orders(getArguments().getString(ValueConstant.DATA_DATA),getP().getD().getPageIndex(),new UINetAdapter<OrdersRes>(activity) {
            @Override
            public void onResult(boolean success, String msg, OrdersRes o) {
                super.onResult(success, msg, o);
                //o = new Test().getOrdersRes();
                if(o==null){
                    return;
                }
                getP().getD().getOrdersRes().getResults().addAll(o.getResults());
                getP().getU().LoadListData(getArguments().getString(ValueConstant.DATA_DATA),getP().getD().getOrdersRes(),BeginFrag.this);
                getP().getU().finishRefresh();
            }
        });

    }
}
