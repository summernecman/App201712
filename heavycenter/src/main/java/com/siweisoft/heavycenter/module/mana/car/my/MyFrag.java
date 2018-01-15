package com.siweisoft.heavycenter.module.mana.car.my;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.car.detail.DetailFrag;
import com.siweisoft.heavycenter.module.mana.car.news.NewFrag;

import butterknife.OnClick;

public class MyFrag extends AppFrag<MyUIOpe,MyDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener{

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
                FragManager2.getInstance().start(getBaseUIActivity(), MainAct.主界面,MainAct.ID_CONTENT,new DetailFrag());
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getU().finishLoadmore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().Cars(getArguments().getString(ValueConstant.DATA_POSITION),new UINetAdapter<CarsResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, CarsResBean o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o,getArguments().getString(ValueConstant.DATA_POSITION),MyFrag.this);
                getP().getU().finishRefresh();
            }
        });
    }
}
