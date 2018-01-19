package com.siweisoft.heavycenter.module.main.order.detail;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.data.netd.order.news.NewsOrderReqBean;
import com.siweisoft.heavycenter.databinding.FragMainOrderBeginBinding;
import com.siweisoft.heavycenter.databinding.FragMainOrderDetailBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderBeginBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDetailDriverBinding;

import java.util.Date;
import java.util.List;

public class DetailUIOpe extends AppUIOpe<FragMainOrderDetailBinding>{


    public DetailUIOpe(Context context) {
        super(context);
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
        bind.title.getMidTV().setText(StringUtil.getStr(res.getOrderNo()));
        bind.setVariable(BR.frag_main_order_detail,res);
        bind.itemPlantime.setMidTVTxt(StringUtil.getStr(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH,new Date(res.getPlanTime()))));
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

    public void initdata(List<CarsResBean.ResultsBean> list, ViewListener listener ){
        bind.recycle.setAdapter(new AppsDataBindingAdapter(getActivity(),R.layout.item_main_order_detail_driver,BR.item_main_order_detail_driver,list,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder, position);
                ItemMainOrderDetailDriverBinding binding = (ItemMainOrderDetailDriverBinding) holder.viewDataBinding;
                binding.menu.setOnClickListener(this);
                binding.menu.setTag(R.id.data,list.get(position));
                binding.menu.setTag(R.id.position,position);
            }
        });
    }


}
