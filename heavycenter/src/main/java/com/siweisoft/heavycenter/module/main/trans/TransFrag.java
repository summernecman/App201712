package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
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
import com.siweisoft.heavycenter.data.netd.trans.trans.TransRes;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.trans.detail.TransDetailFrag;
import com.siweisoft.heavycenter.module.main.trans.search.SearchFrag;

import butterknife.OnClick;

public class TransFrag extends AppFrag<TransUIOpe,TransDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener {



    @Override
    public void lazyInit() {
        getP().getU().initRefresh(this,this);
        getP().getU().initRecycle();
        getP().getU().autoRefresh();
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.运输单,MainAct.运输单ID,new TransDetailFrag());
                break;
        }
    }

    @OnClick({R.id.ftv_right2,R.id.search})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)activity).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right2:
                getP().getU().search(new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        boolean b = (boolean) o;
                        if(b){
                            getP().getD().transs(getP().getU().getTransReq(getP().getD().getTransReq()), new UINetAdapter<TransRes>(activity) {
                                @Override
                                public void onResult(boolean success, String msg, TransRes o) {
                                    super.onResult(success, msg, o);
                                }
                            });
                        }
                    }
                });
                break;
            case R.id.search:

                getP().getU().refreshSearch();
                break;
            case R.id.ftv_right:
                if(getActivity() instanceof MainAct){
                    MainAct mainAct = (MainAct) getActivity();
                    mainAct.dealScan(this);
                }
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        refreshlayout.finishRefresh(2000);
        getP().getU().LoadListData(getP().getD().getData(),this);
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getU().LoadListData(getP().getD().getData(),this);
        getP().getU().finishLoadmore();
    }
}
