package com.siweisoft.heavycenter.module.main.store;

//by summer on 2017-12-11.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.util.fragment.two.FragManager2;
import com.google.zxing.integration.android.IntentIntegrator;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.Test;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;
import com.siweisoft.heavycenter.module.main.store.check.CheckFrag;
import com.siweisoft.heavycenter.module.main.store.check.CheckValue;
import com.siweisoft.heavycenter.module.main.store.detail.DetailFrag;
import com.siweisoft.heavycenter.module.view.scan.ScanAct;

import butterknife.OnClick;

public class StoreFrag extends AppFrag<StoreUIOpe,StoreDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener{


    @Override
    public void onFristVisibleDelayInit() {
        getP().getU().initRefresh(this,this);
        getP().getU().initRecycle();
        onRefresh(null);
    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)getActivity()).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right2:
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.FARG_REQ,1);
                FragManager2.getInstance().start(getBaseUIAct(), get容器(),CheckFrag.getInstance(CheckValue.盘点所有仓库,-1),bundle);
                break;
            case R.id.ftv_right:
                if(getActivity() instanceof MainAct){
                    new IntentIntegrator(getBaseAct()).setCaptureActivity(ScanAct.class).initiateScan();
                }
                break;
        }
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                StoreDetail storeDetail = (StoreDetail) v.getTag(R.id.data);
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.DATA_DATA,storeDetail.getWarehouseId());
                bundle.putInt(ValueConstant.FARG_REQ,2);
                FragManager2.getInstance().start(getBaseUIAct(),get容器(),new DetailFrag(),bundle);
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getD().setPageIndex(getP().getD().getPageIndex()+1);
        getP().getD().storesInfo(new NetAdapter<StoresResBean>(this) {
            @Override
            public void onSuccess(StoresResBean o) {
                //o = new Test().getStoresResBean();
                getP().getD().addData(o);
                getP().getU().notifyDataSetChanged();
                getP().getU().finishLoadmore();
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().setPageIndex(NetValue.PAGE_INDEX_START);
        getP().getD().getStoresResBean().getResults().clear();
        getP().getD().storesInfo(new NetAdapter<StoresResBean>(this) {
            @Override
            public void onSuccess(StoresResBean o) {
                //o = new Test().getStoresResBean();
                getP().getD().addData(o);
                getP().getU().LoadListData(getP().getD().getStoresResBean(),StoreFrag.this);
                getP().getU().finishRefresh();
            }
        });
    }

    @Override
    public void onResult(int res, Bundle bundle) {
        super.onResult(res, bundle);
        switch (res){
            case 1:
                if(bundle!=null&&bundle.getBoolean(ValueConstant.FRAG_KEY,false)){
                    getP().getU().autoRefresh(600);
                }
                break;
            case 2:
                if(bundle!=null&&bundle.getBoolean(ValueConstant.FRAG_KEY,false)){
                    getP().getU().autoRefresh(600);
                }
                break;
        }
    }
}
