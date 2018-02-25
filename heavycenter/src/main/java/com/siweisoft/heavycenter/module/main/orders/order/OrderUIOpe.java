package com.siweisoft.heavycenter.module.main.orders.order;

//by summer on 2017-12-19.

import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.listener.ViewListener;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.databinding.FragMainOrderBeginBinding;

public class OrderUIOpe extends AppUIOpe<FragMainOrderBeginBinding>{

    NewOrderUIOpe newOrderUIOpe = new NewOrderUIOpe();

    IngOrderUIOpe ingOrderUIOpe = new IngOrderUIOpe();

    DoneOrderUIOpe doneOrderUIOpe = new DoneOrderUIOpe();


    public void initUI() {
        initRecycle();
        newOrderUIOpe.copy(this);
        ingOrderUIOpe.copy(this);
        doneOrderUIOpe.copy(this);
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
        switch (type){
            case OrdersReq.新订单:
                newOrderUIOpe.LoadListData(bind.recycle,type,s,listener);
                break;
            case OrdersReq.进行中订单:
                ingOrderUIOpe.LoadListData(bind.recycle,type,s,listener);
                break;
            case OrdersReq.已完成订单:
                doneOrderUIOpe.LoadListData(bind.recycle,type,s,listener);
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
