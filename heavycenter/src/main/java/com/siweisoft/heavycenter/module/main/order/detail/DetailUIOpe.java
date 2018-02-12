package com.siweisoft.heavycenter.module.main.order.detail;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.data.netd.order.news.NewsOrderReqBean;
import com.siweisoft.heavycenter.databinding.FragMainOrderDetailBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDetailDriverBinding;
import com.tubb.smrv.SwipeHorizontalMenuLayout;
import com.tubb.smrv.SwipeMenuLayout;

import java.util.Date;
import java.util.List;

public class DetailUIOpe extends AppUIOpe<FragMainOrderDetailBinding>{



    public void initUI() {
        initRecycle();

    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void initUI(String type){
        switch (type){
            case OrdersReq.STATUS_NEW:
                bind.itemStarttime.setVisibility(View.GONE);
                bind.itemEndtime.setVisibility(View.GONE);
                break;
            case OrdersReq.STATUS_ING:
                bind.itemEndtime.setVisibility(View.GONE);
                break;
            case OrdersReq.STATUS_DONE:
                bind.llDrivers.setVisibility(View.GONE);
                break;
        }
    }

    public void initUI(String type, OrdersRes.ResultsBean res){
        switch (type){
            case OrdersReq.STATUS_NEW:
                bind.itemStarttime.setVisibility(View.GONE);
                bind.itemEndtime.setVisibility(View.GONE);
                break;
            case OrdersReq.STATUS_ING:
                bind.itemEndtime.setVisibility(View.GONE);
                break;
            case OrdersReq.STATUS_DONE:
                bind.llDrivers.setVisibility(View.GONE);
                break;
        }
        if(res==null){
            return;
        }
        bind.title.getMidTV().setText(StringUtil.getStr(res.getOrderNo()));
        bind.setVariable(BR.frag_main_order_detail,res);
        bind.itemPlantime.setMidTVTxt(StringUtil.getStr(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM,new Date(res.getPlanTime()))));
        if(NewsOrderReqBean.发货.equals(res.getOrderType())){
            bind.tvOrdertype.setText("发往");
            bind.tvUnit.setText(StringUtil.getStr(res.getShdwName()));
        }else{
            bind.tvOrdertype.setText("来自");
            bind.tvUnit.setText(StringUtil.getStr(res.getFhdwName()));
        }
        bind.itemAddr.setMidTVTxt(StringUtil.getStr(res.getAddress()));
        bind.itemRule.setMidTVTxt(StringUtil.getStr(res.getSignRule()));
    }

    public void initdata(List<CarsResBean.CarInfoRes> list, ViewListener listener ){
        bind.recycle.setAdapter(new AppsDataBindingAdapter(getActivity(),R.layout.item_main_order_detail_driver,BR.item_main_order_detail_driver,list,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                final ItemMainOrderDetailDriverBinding binding = (ItemMainOrderDetailDriverBinding) holder.viewDataBinding;

                View content = binding.sml.getChildAt(1);
                TextView menu = (TextView) binding.sml.getChildAt(0);


                menu.setOnClickListener(this);
                menu.setTag(R.id.data,list.get(position));
                menu.setTag(R.id.position,position);
                menu.setTag(R.id.data1,binding.sml);

                content.setOnClickListener(this);
                content.setTag(R.id.position,position);
                content.setTag(R.id.data,list.get(position));



                binding.sml.setSwipeListener(new com.siweisoft.heavycenter.module.view.swipe.MySwipeListener(){
                    @Override
                    public void endMenuOpened(SwipeMenuLayout swipeMenuLayout) {
                        for(int i=0;i<bind.recycle.getChildCount();i++){
                            SwipeHorizontalMenuLayout swipeLayout= (SwipeHorizontalMenuLayout) bind.recycle.getChildAt(i);
                            if(swipeLayout!=binding.sml){
                                swipeLayout.smoothCloseMenu(400);
                            }
                        }
                    }
                });

            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                switch (v.getId()){
                    case R.id.smMenuViewRight:
                        SwipeHorizontalMenuLayout swipeLayout= (SwipeHorizontalMenuLayout) v.getTag(R.id.data1);
                        swipeLayout.smoothCloseMenu(400);
                        break;
                }

            }
        });




        bind.recycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                switch (newState){
                    case RecyclerView.SCROLL_STATE_DRAGGING:
                        for(int i=0;i<recyclerView.getChildCount();i++){
                            SwipeHorizontalMenuLayout swipeLayout= (SwipeHorizontalMenuLayout) recyclerView.getChildAt(i);
                            swipeLayout.smoothCloseMenu(400);
                        }
                        break;
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


}
