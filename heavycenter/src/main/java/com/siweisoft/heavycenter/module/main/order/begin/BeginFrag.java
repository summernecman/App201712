package com.siweisoft.heavycenter.module.main.order.begin;

//by summer on 2017-12-19.

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.StringUtil;
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
import com.siweisoft.heavycenter.data.netd.order.receipt.ReceiptOrderReq;
import com.siweisoft.heavycenter.data.netd.order.receipt.ReceiptOrderRes;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;
import com.siweisoft.heavycenter.module.main.order.detail.DetailFrag;
import com.siweisoft.heavycenter.module.myce.test.DetailTransition;
import com.siweisoft.heavycenter.module.myce.test.HeadTestFrag;
import com.siweisoft.heavycenter.module.test.SharedElementFragment1;
import com.siweisoft.heavycenter.module.test.SharedElementFragment2;

public class BeginFrag extends AppFrag<BeginUIOpe,BeginDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener{

    @Override
    protected void onFristVisibleDelayInit() {
        getP().getU().initRefresh(this,this);
        if(StringUtil.equals(OrdersReq.STATUS_NEW,getArguments().getString(ValueConstant.DATA_DATA))){
            getP().getU().autoRefresh();
        }else{
            onRefresh(getP().getU().bind.refresh);
        }
    }

    @Override
    protected void onFristVisibleInit() {

    }

    @SuppressLint("ResourceType")
    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()){
                    case R.id.ll_ingorder:



//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//
//                            SharedElementFragment1 detailFrag = new SharedElementFragment1();
//                            detailFrag.iid = 14346;
//                            getFragmentManager().beginTransaction()
//                                    .add(detailFrag.iid,detailFrag)
//                                    .addToBackStack(null)
//                                    .commit();
//                            return;
//                        }



//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                            DetailFrag sharedElementFragment2 = new DetailFrag();
//
//                            Slide slideTransition = new Slide(Gravity.RIGHT);
//                            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
//
//                            ChangeBounds changeBoundsTransition = new ChangeBounds();
//                            changeBoundsTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
//
//                            sharedElementFragment2.setEnterTransition(slideTransition);
//                            sharedElementFragment2.setAllowEnterTransitionOverlap(false);
//                            sharedElementFragment2.setAllowReturnTransitionOverlap(false);
//                            sharedElementFragment2.setSharedElementEnterTransition(changeBoundsTransition);
//
//                            getFragmentManager().beginTransaction()
//                                    .add(14346, sharedElementFragment2)
//                                    .hide(this)
//                                    .setReorderingAllowed(true)
//                                    .addToBackStack(null)
//                                    .addSharedElement(v, "doingorder")
//                                    .commit();
//                            return;
//                        }



//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//
//                            SharedElementFragment1 detailFrag = new SharedElementFragment1();
//
//                            Slide slideTransition = new Slide(Gravity.RIGHT);
//                            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
//
//                            ChangeBounds changeBoundsTransition = new ChangeBounds();
//                            changeBoundsTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
//
//                            detailFrag.setEnterTransition(slideTransition);
//                            detailFrag.setAllowEnterTransitionOverlap(false);
//                            detailFrag.setAllowReturnTransitionOverlap(false);
//                            detailFrag.setSharedElementEnterTransition(changeBoundsTransition);
//
//                            getActivity().getSupportFragmentManager().beginTransaction()
//                                    .add(get容器(), detailFrag)
//                                    .setReorderingAllowed(true)
//                                    .hide(this)
//                                    .addToBackStack(null)
//                                    .addSharedElement(v, getString(R.string.square_blue_name))
//                                    .commit();
//                        }



//                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
//                            DetailFrag sharedElementFragment2 = new DetailFrag();
//
//                            Slide slideTransition = new Slide(Gravity.RIGHT);
//                            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
//
//                            ChangeBounds changeBoundsTransition = new ChangeBounds();
//                            changeBoundsTransition.setDuration(getResources().getInteger(R.integer.anim_duration_medium));
//
//                            sharedElementFragment2.setEnterTransition(slideTransition);
//                            sharedElementFragment2.setAllowEnterTransitionOverlap(true);
//                            sharedElementFragment2.setAllowReturnTransitionOverlap(true);
//                            sharedElementFragment2.setSharedElementEnterTransition(changeBoundsTransition);
//
//                            getActivity().getSupportFragmentManager().beginTransaction()
//                                    .replace(get容器(), sharedElementFragment2)
//                                    .addSharedElement(v, getString(R.string.square_blue_name))
//                                    .commit();
//                        }





                        Bundle bundle1 = new Bundle();
                        bundle1.putString(ValueConstant.TYPE,(String)v.getTag(R.id.type));
                        OrdersRes.ResultsBean resultsBean1 = (OrdersRes.ResultsBean) v.getTag(R.id.data);
                        bundle1.putInt(ValueConstant.DATA_DATA, resultsBean1.getOrderId());
                        FragManager2.getInstance().start(getBaseUIAct(), get容器(),new DetailFrag(),bundle1);
                        break;
                    case R.id.ll_neworder:
                        Bundle bundle = new Bundle();
                        bundle.putString(ValueConstant.TYPE,(String)v.getTag(R.id.type));
                        OrdersRes.ResultsBean resultsBean = (OrdersRes.ResultsBean) v.getTag(R.id.data);
                        bundle.putInt(ValueConstant.DATA_DATA, resultsBean.getOrderId());
                        FragManager2.getInstance().start(getBaseUIAct(), get容器(),new DetailFrag(),bundle);
                        break;
                    case R.id.bt_sure:
                        final OrdersRes.ResultsBean data = (OrdersRes.ResultsBean) v.getTag(R.id.data);
                        getP().getD().receipt(data.getOrderId(), ReceiptOrderReq.AUDIO_STATE_接收, new UINetAdapter<ReceiptOrderRes>(this) {
                            @Override
                            public void onSuccess(ReceiptOrderRes o) {
                                data.setAuditState(OrdersRes.ResultsBean.AUDITSTATE_接收);
                                getP().getU().notifyDataSetChanged();
                            }
                        });
                        break;
                    case R.id.bt_reject:
                        final OrdersRes.ResultsBean data1 = (OrdersRes.ResultsBean) v.getTag(R.id.data);
                        getP().getD().receipt(data1.getOrderId(), ReceiptOrderReq.AUDIO_STATE_拒绝, new UINetAdapter<ReceiptOrderRes>(this) {
                            @Override
                            public void onSuccess(ReceiptOrderRes o) {
                                data1.setAuditState(OrdersRes.ResultsBean.AUDITSTATE_拒绝);
                                getP().getU().notifyDataSetChanged();
                            }
                        });
                        break;
                }
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getD().setPageIndex(getP().getD().getPageIndex()+1);
        getP().getD().orders(getArguments().getString(ValueConstant.DATA_DATA),getP().getD().getPageIndex(),new UINetAdapter<OrdersRes>(this) {
            @Override
            public void onSuccess(OrdersRes o) {
                //o = new Test().getOrdersRes();
                getP().getD().getOrdersRes().getResults().addAll(o.getResults());
                getP().getU().notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().setPageIndex(NetValue.PAGE_INDEX_START);
        getP().getD().getOrdersRes().getResults().clear();
        getP().getD().orders(getArguments().getString(ValueConstant.DATA_DATA),getP().getD().getPageIndex(),new UINetAdapter<OrdersRes>(this) {
            @Override
            public void onSuccess(OrdersRes o) {
                getP().getD().getOrdersRes().getResults().addAll(o.getResults());
                getP().getU().LoadListData(getArguments().getString(ValueConstant.DATA_DATA),getP().getD().getOrdersRes(),BeginFrag.this);
            }
        });

    }
}
