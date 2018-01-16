package com.siweisoft.heavycenter.module.mana.store;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.status.StatusStoresResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.store.news.NewFrag;

import butterknife.OnClick;

public class StoreFrag extends AppFrag<StoreUIOpe,StoreDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh(this,this);
        getP().getU().initRecycle();
        getP().getU().autoRefresh();

    }


    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                FragManager2.getInstance().start(getBaseUIActivity(),getContainerName(),new NewFrag());
                break;
        }
    }


    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
               switch (v.getId()){
                   case R.id.munu:
                       final StoresResBean.ResultsBean data  = (StoresResBean.ResultsBean) v.getTag(R.id.data);
                       getP().getD().statusStore(getP().getU().getStatusStoresReqBean(getP().getD().getStatusStoresReqBean(),data.getWarehouseId(),data.getStatus()), new UINetAdapter<StatusStoresResBean>(getActivity()) {
                           @Override
                           public void onResult(boolean success, String msg, StatusStoresResBean o) {
                               super.onResult(success, msg, o);
                               if(success){
                                   data.setStatus(1-data.getStatus());
                                   getP().getU().notifyDataSetChanged();
                               }
                           }
                       });
                       break;
               }
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getU().finishLoadmore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().storesInfo(new UINetAdapter<StoresResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, StoresResBean o) {
                super.onResult(success, msg, o);
               getP().getU().finishRefresh();
               getP().getU().LoadListData(o,StoreFrag.this);
            }
        });
    }
}
