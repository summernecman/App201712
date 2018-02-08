package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.Test;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.trans.detail.TransDetailRes;
import com.siweisoft.heavycenter.data.netd.trans.sign.TransSignRes;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransRes;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;
import com.siweisoft.heavycenter.module.main.trans.detail.TransDetailFrag;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import butterknife.OnClick;

public class TransFrag extends AppFrag<TransUIOpe,TransDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener {

    public TransFrag(){
        LogUtil.E("fdf");
    }

    @Override
    public void onFristVisibleInit() {
        getP().getU().initRefresh(this,this);
        getP().getU().initRecycle();
        onRefresh(null);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                final TransDetailRes resultsBean = (TransDetailRes) v.getTag(R.id.data);
                switch (v.getId()){
                    case R.id.bt_sure:
                        getP().getD().signTrans(resultsBean.getTransportrecordId(), new UINetAdapter<TransSignRes>(getContext()) {
                            @Override
                            public void onResult(boolean success, String msg, TransSignRes o) {
                                super.onResult(success, msg, o);
                                if(success){
                                    resultsBean.setSignStatus(TransDetailRes.SING_STATUS_已确认);
                                    getP().getU().notifyDataSetChanged();
                                }
                            }
                        });
                        break;
                        default:
                            Bundle bundle = new Bundle();
                            bundle.putInt(ValueConstant.DATA_DATA,resultsBean.getTransportrecordId());
                            FragManager2.getInstance().start(getBaseUIAct(), MainValue.运输单,MainValue.运输单ID,new TransDetailFrag(),bundle);
                            break;
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @OnClick({R.id.ftv_right2,R.id.search,R.id.view})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)getBaseUIAct()).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right2:
                getP().getU().search(new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        boolean b = (boolean) o;
                        if(b){
                            getP().getD().setPageIndex(0);
                            getP().getD().transs(getP().getU().getTransReq(getP().getD().getTransReq(getP().getD().getPageIndex())), new UINetAdapter<TransRes>(getBaseUIAct()) {
                                @Override
                                public void onResult(boolean success, String msg, TransRes o) {
                                    super.onResult(success, msg, o);

                                }
                            });
                        }
                    }
                });
                break;
            case R.id.view:
                if(getP().getU().bind.search.getRoot().getVisibility()==View.VISIBLE){
                    getP().getU().bind.title.getRightIV2().setSelected(false);
                    getP().getU().bind.search.getRoot().setVisibility(View.GONE);
                }
                break;
            case R.id.search:

                getP().getU().refreshSearch();
                break;
            case R.id.ftv_right:
                if(getActivity() instanceof MainAct){
                    MainAct mainAct = (MainAct) getActivity();
                    Intent intent = new Intent(mainAct, CaptureActivity.class);
                    getBaseUIAct().startActivityForResult(intent, ValueConstant.CODE_REQUSET);
                }
                break;
        }
    }

    @Override
    public void onRefresh(final RefreshLayout refreshlayout) {
        getP().getD().setPageIndex(NetValue.PAGE_INDEX_START);
        getP().getD().getTransRes().getResults().clear();
        getP().getD().transs(getP().getU().getTransReq(getP().getD().getTransReq(getP().getD().getPageIndex())), new UINetAdapter<TransRes>(getBaseUIAct()) {
            @Override
            public void onResult(boolean success, String msg, TransRes o) {
                super.onResult(success, msg, o);
                //o = new Test().getTransRes();
               getP().getU().finishRefresh();
               if(o!=null&& o.getResults()!=null){
                   getP().getD().getTransRes().getResults().addAll(o.getResults());
                   getP().getU().LoadListData(getP().getD().getTransRes().getResults(),TransFrag.this);
               }

            }
        });

    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getD().setPageIndex(getP().getD().getPageIndex()+1);
        getP().getD().transs(getP().getU().getTransReq(getP().getD().getTransReq(getP().getD().getPageIndex())), new UINetAdapter<TransRes>(getBaseUIAct()) {
            @Override
            public void onResult(boolean success, String msg, TransRes o) {
                super.onResult(success, msg, o);
                //o = new Test().getTransRes();
                getP().getU().finishLoadmore();
                if(o.getResults()!=null){
                    getP().getD().getTransRes().getResults().addAll(o.getResults());
                    getP().getU().notifyDataSetChanged();
                }
            }
        });
    }
}
