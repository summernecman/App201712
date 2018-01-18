package com.siweisoft.heavycenter.module.main.order.begin;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;

public class BeginFrag extends AppFrag<BeginUIOpe,BeginDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh(this,this);
        getP().getU().autoRefresh();
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:

                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getU().finishLoadmore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().orders(getArguments().getString(ValueConstant.DATA_DATA),new UINetAdapter<OrdersRes>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, OrdersRes o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(getArguments().getString(ValueConstant.DATA_DATA),o);
                getP().getU().finishRefresh();
            }
        });

    }
}
