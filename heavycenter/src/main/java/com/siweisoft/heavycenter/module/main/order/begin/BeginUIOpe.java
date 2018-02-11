package com.siweisoft.heavycenter.module.main.order.begin;

//by summer on 2017-12-19.

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.LogUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.view.ItemDecoration.MyItemDecoration2;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.mana.good.list.GoodListRes;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.data.netd.order.news.NewsOrderReqBean;
import com.siweisoft.heavycenter.databinding.FragMainOrderBeginBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderBeginBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDoingBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDoneBinding;
import com.siweisoft.heavycenter.databinding.ItemManaGoodBinding;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public class BeginUIOpe extends AppUIOpe<FragMainOrderBeginBinding>{




    public void initUI() {
        initRecycle();
    }

    private void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final String type, final OrdersRes s, ViewListener listener){
        if(s==null || s.getResults()==null || s.getResults().size()==0){
            getFrag().showTips("暂无数据");
            return;
        }
        getFrag().removeTips();
        final String comname = LocalValue.get登录返回信息().getAbbreviationName();
        switch (type){
            case OrdersReq.STATUS_NEW:
                bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_begin, BR.item_main_order_begin,s.getResults(),listener){

                    int darkcolor = context.getResources().getColor(R.color.color_item_main_trans_dark);
                    int lightcolor = context.getResources().getColor(R.color.color_item_main_trans_light);

                    @Override
                    public void onBindViewHolder(AppViewHolder holder, int position) {


                        ItemMainOrderBeginBinding beginBinding = (ItemMainOrderBeginBinding) holder.viewDataBinding;
                        beginBinding.getRoot().setSelected(position%2==0?true:false);

                        beginBinding.llNeworder.setTag(com.android.lib.R.id.data, list.get(position));
                        beginBinding.llNeworder.setTag(com.android.lib.R.id.position, position);
                        beginBinding.llNeworder.setOnClickListener(this);
                        beginBinding.setVariable(vari, list.get(position));
                        beginBinding.executePendingBindings();//加一行，问题解决

                        beginBinding.tvPlantime.setText("计划开始时间:"+StringUtil.getStr(DateFormatUtil.getdDateStr(DateFormatUtil.MM_DD_HH_MM,new Date(s.getResults().get(position).getPlanTime()))));
                        beginBinding.getRoot().setTag(R.id.type,type);

                        if(comname.equals(s.getResults().get(position).getFhdwName())){
                            beginBinding.tvDuimian.setText(s.getResults().get(position).getShdwName());
                            beginBinding.tvType.setText("发往");
                            beginBinding.tvType.setBackgroundResource(R.drawable.bg_hv_sharp2_yell_solid);
                            beginBinding.tvPlanmun.setBackgroundResource(R.drawable.bg_hv_sharp2_yell_stroke);
                            beginBinding.tvPlanmun.setTextColor(context.getResources().getColor(R.color.color_hv_yelll));
                        }else{
                            LogUtil.E(position+""+s.getResults().get(position).getFhdwName());
                            beginBinding.tvDuimian.setText(s.getResults().get(position).getFhdwName());
                            beginBinding.tvType.setText("来自");
                            beginBinding.tvType.setBackgroundResource(R.drawable.bg_hv_sharp2_blue_solid);
                            beginBinding.tvPlanmun.setBackgroundResource(R.drawable.bg_hv_sharp2_blue_stroke);
                            beginBinding.tvPlanmun.setTextColor(context.getResources().getColor(R.color.color_hv_blue));
                        }

                        if((s.getResults().get(position).getAuditState()==OrdersRes.ResultsBean.AUDITSTATE_未审核)&&
                                NewsOrderReqBean.发货.equals(s.getResults().get(position).getOrderType())){
                                beginBinding.llMenu.setVisibility(View.VISIBLE);
                        }else{
                            beginBinding.llMenu.setVisibility(View.GONE);
                        }
                        beginBinding.btSure.setOnClickListener(this);
                        beginBinding.btSure.setTag(R.id.data,s.getResults().get(position));
                        beginBinding.btSure.setTag(R.id.position,position);

                        beginBinding.btReject.setOnClickListener(this);
                        beginBinding.btReject.setTag(R.id.data,s.getResults().get(position));
                        beginBinding.btReject.setTag(R.id.position,position);
                    }
                });
                break;
            case OrdersReq.STATUS_ING:
                bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_doing, BR.item_main_order_doing, s.getResults(),listener) {

                    int darkcolor = context.getResources().getColor(R.color.color_item_main_trans_dark);
                    int lightcolor = context.getResources().getColor(R.color.color_item_main_trans_light);

                    @Override
                    public void onBindViewHolder(AppViewHolder holder, int position) {
                        ItemMainOrderDoingBinding doingBinding = (ItemMainOrderDoingBinding) holder.viewDataBinding;
                        doingBinding.getRoot().setSelected(position % 2 == 0 ? true : false);
                        doingBinding.getRoot().setTag(R.id.type,type);

                        doingBinding.llIngorder.setTag(com.android.lib.R.id.data, list.get(position));
                        doingBinding.llIngorder.setTag(com.android.lib.R.id.position, position);
                        doingBinding.llIngorder.setOnClickListener(this);
                        doingBinding.setVariable(vari, list.get(position));
                        doingBinding.executePendingBindings();//加一行，问题解决

                        doingBinding.tvGoodname.setText(StringUtil.getStr(s.getResults().get(position).getProductName()));
                        doingBinding.tvSpes.setText(StringUtil.getStr(s.getResults().get(position).getSpecification()));
                        if(comname.equals(s.getResults().get(position).getFhdwName())){
                            doingBinding.tvDuimian.setText(s.getResults().get(position).getShdwName());
                            doingBinding.tvType.setText("发往");
                            doingBinding.tvType.setBackgroundResource(R.drawable.bg_hv_sharp2_yell_solid);
                        }else{
                            LogUtil.E(position+""+s.getResults().get(position).getFhdwName());
                            doingBinding.tvDuimian.setText(s.getResults().get(position).getFhdwName());
                            doingBinding.tvType.setText("来自");
                            doingBinding.tvType.setBackgroundResource(R.drawable.bg_hv_sharp2_blue_solid);
                        }
                        doingBinding.tvPlan.setText(StringUtil.getStr(s.getResults().get(position).getPlanNumber())+"t");
                        doingBinding.tvStarttime.setText("开始时间："+StringUtil.getStr(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM,new Date(s.getResults().get(position).getPlanTime()))));
                        doingBinding.tvEndtime.setText("结束时间：");
                        doingBinding.tvCarno.setText(StringUtil.getStr(s.getResults().get(position).getCarLicenseNo()));


                        doingBinding.tvCarnum.setText(StringUtil.getStr(s.getResults().get(position).getTotalRecord()));



                        DecimalFormat df = new DecimalFormat("#.##");
                        doingBinding.tvCurrent.setText(StringUtil.getStr(Double.parseDouble(df.format(s.getResults().get(position).getActualSh())))+"t");



                    }
                });
                break;
            case OrdersReq.STATUS_DONE:
                bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_done, BR.item_main_order_done,s.getResults(),listener){

                    int darkcolor = context.getResources().getColor(R.color.color_item_main_trans_dark);
                    int lightcolor = context.getResources().getColor(R.color.color_item_main_trans_light);

                    @Override
                    public void onBindViewHolder(AppViewHolder holder, int position) {
                        ItemMainOrderDoneBinding doneBinding = (ItemMainOrderDoneBinding) holder.viewDataBinding;
                        doneBinding.getRoot().setSelected(position%2==0?true:false);

                        doneBinding.llDoneorder.setTag(com.android.lib.R.id.data, list.get(position));
                        doneBinding.llDoneorder.setTag(com.android.lib.R.id.position, position);
                        doneBinding.llDoneorder.setOnClickListener(this);
                        doneBinding.setVariable(vari, list.get(position));
                        doneBinding.executePendingBindings();//加一行，问题解决

                        doneBinding.tvGoodname.setText(StringUtil.getStr(s.getResults().get(position).getProductName()));
                        doneBinding.tvSpes.setText(StringUtil.getStr(s.getResults().get(position).getSpecification()));
                        if("S".equals(s.getResults().get(position).getOrderType())){
                            doneBinding.tvType.setText("发往");
                            doneBinding.tvCompanyname.setText(StringUtil.getStr(s.getResults().get(position).getShdwQName()));
                        }else{
                            doneBinding.tvType.setText("来自");
                            doneBinding.tvCompanyname.setText(StringUtil.getStr(s.getResults().get(position).getFhdwQName()));
                        }
                    }
                });
                break;
        }

//        bind.recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
//                super.onScrollStateChanged(recyclerView, newState);
//                switch (newState){
//                    case RecyclerView.SCROLL_STATE_DRAGGING:
//                        for(int i=0;i<recyclerView.getChildCount();i++){
//                            SwipeLayout swipeLayout = (SwipeLayout) recyclerView.getChildAt(i);
//                            swipeLayout.close(true);
//                        }
//                        break;
//                }
//            }
//
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//            }
//        });
    }


    public void initRefresh(OnRefreshListener refreshListener,OnLoadmoreListener loadmoreListener){
        bind.refresh.setOnRefreshListener(refreshListener);
        bind.refresh.setOnLoadmoreListener(loadmoreListener);
    }

    public void finishRefresh(){
        bind.refresh.finishRefresh();
    }

    public void finishLoadmore(){
        bind.refresh.finishLoadmore();
    }

    public void notifyDataSetChanged(){
        bind.recycle.getAdapter().notifyDataSetChanged();
    }


    public void autoRefresh(){
        bind.refresh.autoRefresh();
    }
}
