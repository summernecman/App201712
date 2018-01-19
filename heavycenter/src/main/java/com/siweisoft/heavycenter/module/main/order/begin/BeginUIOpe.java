package com.siweisoft.heavycenter.module.main.order.begin;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.mana.good.list.GoodListRes;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.data.netd.order.news.NewsOrderReqBean;
import com.siweisoft.heavycenter.databinding.FragMainOrderBeginBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderBeginBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDoingBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDoneBinding;
import com.siweisoft.heavycenter.databinding.ItemManaGoodBinding;

import java.util.Date;
import java.util.List;

public class BeginUIOpe extends AppUIOpe<FragMainOrderBeginBinding>{


    public BeginUIOpe(Context context) {
        super(context);
        initRecycle();
    }



    private void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final String type, final OrdersRes s, ViewListener listener){
        if(s==null){
            return;
        }

        switch (type){
            case OrdersReq.STATUS_NEW:
                bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_begin, BR.item_main_order_begin,s.getResults(),listener){

                    int darkcolor = context.getResources().getColor(R.color.color_item_main_trans_dark);
                    int lightcolor = context.getResources().getColor(R.color.color_item_main_trans_light);

                    @Override
                    public void onBindViewHolder(AppViewHolder holder, int position) {
                        super.onBindViewHolder(holder, position);
                        ItemMainOrderBeginBinding beginBinding = (ItemMainOrderBeginBinding) holder.viewDataBinding;
                        beginBinding.getRoot().setSelected(position%2==0?true:false);
                        if(NewsOrderReqBean.发货.equals(s.getResults().get(position).getOrderType())){
                            beginBinding.tvType.setText("发往");
                        }else{
                            beginBinding.tvType.setText("来自");
                        }
                        beginBinding.tvPlantime.setText(StringUtil.getStr(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM,new Date(s.getResults().get(position).getPlanTime()))));
                        beginBinding.getRoot().setTag(R.id.type,type);
                    }
                });
                break;
            case OrdersReq.STATUS_ING:
                bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_doing, BR.item_main_order_doing, s.getResults(),listener) {

                    int darkcolor = context.getResources().getColor(R.color.color_item_main_trans_dark);
                    int lightcolor = context.getResources().getColor(R.color.color_item_main_trans_light);

                    @Override
                    public void onBindViewHolder(AppViewHolder holder, int position) {
                        super.onBindViewHolder(holder, position);
                        ItemMainOrderDoingBinding doingBinding = (ItemMainOrderDoingBinding) holder.viewDataBinding;
                        doingBinding.getRoot().setSelected(position % 2 == 0 ? true : false);
                        doingBinding.getRoot().setTag(R.id.type,type);
                    }
                });
                break;
            case OrdersReq.STATUS_DONE:
                bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_done, BR.item_main_order_done,s.getResults(),listener){

                    int darkcolor = context.getResources().getColor(R.color.color_item_main_trans_dark);
                    int lightcolor = context.getResources().getColor(R.color.color_item_main_trans_light);

                    @Override
                    public void onBindViewHolder(AppViewHolder holder, int position) {
                        super.onBindViewHolder(holder, position);
                        ItemMainOrderDoneBinding doneBinding = (ItemMainOrderDoneBinding) holder.viewDataBinding;
                        doneBinding.getRoot().setSelected(position%2==0?true:false);
                        doneBinding.getRoot().setTag(R.id.type,type);
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
        bind.refreshLayout.setOnRefreshListener(refreshListener);
        bind.refreshLayout.setOnLoadmoreListener(loadmoreListener);
    }

    public void finishRefresh(){
        bind.refreshLayout.finishRefresh();
    }

    public void finishLoadmore(){
        bind.refreshLayout.finishLoadmore();
    }


    public void autoRefresh(){
        bind.refreshLayout.autoRefresh();
    }
}
