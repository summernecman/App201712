package com.siweisoft.heavycenter.module.main.orders.detail;

//by summer on 2017-12-19.

import android.databinding.DataBindingUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.data.netd.order.news.NewsOrderReqBean;
import com.siweisoft.heavycenter.databinding.FragMainOrderDetailBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDetailBeginBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDetailDoingBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDetailDoneBinding;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDetailDriverBinding;
import com.tubb.smrv.SwipeHorizontalMenuLayout;
import com.tubb.smrv.SwipeMenuLayout;

import java.text.DecimalFormat;
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
            case OrdersReq.新订单:
                bind.itemStarttime.setVisibility(View.GONE);
                bind.itemEndtime.setVisibility(View.GONE);
                bind.rlTopcontainer.addView(ItemMainOrderDetailBeginBinding.inflate(LayoutInflater.from(context)).getRoot(),new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                break;
            case OrdersReq.进行中订单:
                bind.itemEndtime.setVisibility(View.GONE);
                bind.rlTopcontainer.addView(ItemMainOrderDetailDoingBinding.inflate(LayoutInflater.from(context)).getRoot(),new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                break;
            case OrdersReq.已完成订单:
                bind.rlTopcontainer.addView(ItemMainOrderDetailDoneBinding.inflate(LayoutInflater.from(context)).getRoot(),new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                break;
        }
    }

    public void initUI(String type, OrdersRes.ResultsBean res){
        final String comname = LocalValue.get登录返回信息().getAbbreviationName();

        switch (type){
            case OrdersReq.新订单:
                ItemMainOrderDetailBeginBinding beginBinding = DataBindingUtil.bind(bind.rlTopcontainer.getChildAt(0));
                beginBinding.setVariable(BR.item_main_order_detail_begin,res);
                if(comname.equals(res.getFhdwName())){
                    beginBinding.tvDuimian.setText(res.getShdwName());
                    beginBinding.tvType.setText("发往");
                    beginBinding.tvType.setBackgroundResource(R.drawable.bg_hv_sharp2_yell_solid);
                    beginBinding.tvPlanmun.setBackgroundResource(R.drawable.bg_hv_sharp2_yell_stroke);
                    beginBinding.tvPlanmun.setTextColor(context.getResources().getColor(R.color.color_hv_yelll));
                }else{
                    beginBinding.tvDuimian.setText(res.getFhdwName());
                    beginBinding.tvType.setText("来自");
                    beginBinding.tvType.setBackgroundResource(R.drawable.bg_hv_sharp2_blue_solid);
                    beginBinding.tvPlanmun.setBackgroundResource(R.drawable.bg_hv_sharp2_blue_stroke);
                    beginBinding.tvPlanmun.setTextColor(context.getResources().getColor(R.color.color_hv_blue));
                }
                break;
            case OrdersReq.进行中订单:
                ItemMainOrderDetailDoingBinding doingBinding = DataBindingUtil.bind(bind.rlTopcontainer.getChildAt(0));
                doingBinding.setVariable(BR.item_main_order_detail_doing,res);
                doingBinding.tvGoodname.setText(StringUtil.getStr(res.getProductName()));
                doingBinding.tvSpes.setText(StringUtil.getStr(res.getSpecification()));
                if(comname.equals(res.getFhdwName())){
                    doingBinding.tvDuimian.setText(res.getShdwName());
                    doingBinding.tvType.setText("发往");
                    doingBinding.tvType.setBackgroundResource(R.drawable.bg_hv_sharp2_yell_solid);
                    doingBinding.tvCarno.setSelected(true);
                }else{
                    doingBinding.tvDuimian.setText(res.getFhdwName());
                    doingBinding.tvType.setText("来自");
                    doingBinding.tvType.setBackgroundResource(R.drawable.bg_hv_sharp2_blue_solid);
                    doingBinding.tvCarno.setSelected(false);
                }
                doingBinding.tvPlan.setText(StringUtil.getStr(res.getPlanNumber())+"t");
                doingBinding.tvStarttime.setText("开始时间："+StringUtil.getStr(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM,new Date(res.getPlanTime()))));
                doingBinding.tvEndtime.setText("结束时间：");
                if(NullUtil.isStrEmpty(res.getCarLicenseNo())){
                    doingBinding.tvCarno.setVisibility(View.GONE);
                }else{
                    doingBinding.tvCarno.setText(StringUtil.getStr(res.getCarLicenseNo()));
                }

                doingBinding.tvCarnum.setText(StringUtil.getStr(res.getTotalRecord()));


                DecimalFormat df = new DecimalFormat("#.##");
                doingBinding.tvCurrent.setText(StringUtil.getStr(Double.parseDouble(df.format(res.getActualSh())))+"t");
                int progress = (int) (100*res.getActualSh()/res.getPlanNumber());
                if(progress<0){
                    progress = 0 ;
                }
                if(progress>=50){
                    doingBinding.circlebar.setCircleColor(context.getResources().getColor(R.color.color_hv_blue));
                }else{
                    doingBinding.circlebar.setCircleColor(context.getResources().getColor(R.color.color_hv_yelll));
                }
                doingBinding.circlebar.update(progress,false);

                break;
            case OrdersReq.已完成订单:
                ItemMainOrderDetailDoneBinding doneBinding = DataBindingUtil.bind(bind.rlTopcontainer.getChildAt(0));
                doneBinding.setVariable(BR.item_main_order_detail_done,res);
                doneBinding.tvGoodname.setText(StringUtil.getStr(res.getProductName()));
                doneBinding.tvSpes.setText(StringUtil.getStr(res.getSpecification()));
                if("S".equals(res.getOrderType())){
                    doneBinding.tvType.setText("发往");
                    doneBinding.tvCompanyname.setText(StringUtil.getStr(res.getShdwName()));
                }else{
                    doneBinding.tvType.setText("来自");
                    doneBinding.tvCompanyname.setText(StringUtil.getStr(res.getFhdwName()));
                }
                break;
        }
        if(res==null){
            return;
        }
        bind.title.getMidTV().setText(StringUtil.getStr(res.getOrderNo()));
        bind.setVariable(BR.frag_main_order_detail,res);
        bind.itemPlantime.setMidTVTxt(StringUtil.getStr(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM,new Date(res.getPlanTime()))));
//        if(NewsOrderReqBean.发货.equals(res.getOrderType())){
//            bind.tvOrdertype.setText("发往");
//            bind.tvUnit.setText(StringUtil.getStr(res.getShdwName()));
//        }else{
//            bind.tvOrdertype.setText("来自");
//            bind.tvUnit.setText(StringUtil.getStr(res.getFhdwName()));
//        }
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
