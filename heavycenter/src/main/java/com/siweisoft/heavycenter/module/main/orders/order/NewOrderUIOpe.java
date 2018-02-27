package com.siweisoft.heavycenter.module.main.orders.order;

//by summer on 2018-02-13.

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.LogUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.data.netd.order.news.NewsOrderReqBean;
import com.siweisoft.heavycenter.databinding.ItemMainOrderBeginBinding;

import java.util.Date;

public class NewOrderUIOpe extends BaseUIOpe {





    public void LoadListData(RecyclerView recyclerView,final String type, final OrdersRes s, ViewListener listener){
        final String comname = LocalValue.get登录返回信息().getAbbreviationName();
        recyclerView.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_begin, BR.item_main_order_begin,s.getResults(),listener){

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {

                ItemMainOrderBeginBinding beginBinding = (ItemMainOrderBeginBinding) holder.viewDataBinding;
                beginBinding.getRoot().setSelected(position%2==0?true:false);

                beginBinding.llNeworder.setTag(com.android.lib.R.id.data, list.get(position));
                beginBinding.llNeworder.setTag(com.android.lib.R.id.position, position);
                beginBinding.llNeworder.setOnClickListener(this);
                beginBinding.setVariable(vari, list.get(position));
                beginBinding.executePendingBindings();//加一行，问题解决

                beginBinding.tvPlantime.setText("开始时间 "+ StringUtil.getStr(DateFormatUtil.getdDateStr(DateFormatUtil.MM_DD_HH_MM,new Date(s.getResults().get(position).getPlanTime()))));
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
                    beginBinding.btSure.setVisibility(View.VISIBLE);
                    beginBinding.btReject.setVisibility(View.VISIBLE);
                }else{
                    beginBinding.btSure.setVisibility(View.GONE);
                    beginBinding.btReject.setVisibility(View.GONE);
                }
                beginBinding.btSure.setOnClickListener(this);
                beginBinding.btSure.setTag(R.id.data,s.getResults().get(position));
                beginBinding.btSure.setTag(R.id.position,position);

                beginBinding.btReject.setOnClickListener(this);
                beginBinding.btReject.setTag(R.id.data,s.getResults().get(position));
                beginBinding.btReject.setTag(R.id.position,position);
            }
        });
    }
}
