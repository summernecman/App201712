package com.siweisoft.heavycenter.module.main.store;

//by summer on 2017-12-11.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.store.check.CheckFrag;
import com.siweisoft.heavycenter.module.main.store.detail.DetailFrag;
import com.siweisoft.heavycenter.module.main.trans.detail.TransDetailFrag;

import butterknife.OnClick;

public class StoreFrag extends AppFrag<StoreUIOpe,StoreDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener{


    @Override
    public void lazyInit() {
        getP().getU().initRefresh(this,this);
        getP().getU().initRecycle();
        getP().getU().autoRefresh();
    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)getActivity()).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right2:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.仓库,MainAct.仓库ID,new CheckFrag());
        }
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                StoreDetail storeDetail = (StoreDetail) v.getTag(R.id.data);
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.DATA_DATA,storeDetail.getWarehouseId());
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.仓库,MainAct.仓库ID,new DetailFrag(),bundle);
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getD().setPageIndex(getP().getD().getPageIndex()+1);
        getP().getD().storesInfo(new NetAdapter<StoresResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, StoresResBean o) {
                super.onResult(success, msg, o);
                getP().getD().addData(o);
                getP().getU().notifyDataSetChanged();
                getP().getU().finishLoadmore();
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().setPageIndex(1);
        getP().getD().getStoresResBean().getResults().clear();
        getP().getD().storesInfo(new NetAdapter<StoresResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, StoresResBean o) {
                super.onResult(success, msg, o);
                getP().getD().addData(o);
                getP().getU().LoadListData(getP().getD().getStoresResBean(),StoreFrag.this);
                getP().getU().finishRefresh();
            }
        });
    }
}
