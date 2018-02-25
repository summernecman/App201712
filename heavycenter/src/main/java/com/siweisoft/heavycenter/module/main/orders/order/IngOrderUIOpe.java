package com.siweisoft.heavycenter.module.main.orders.order;

//by summer on 2018-02-25.

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDoingBinding;

import java.text.DecimalFormat;
import java.util.Date;

public class IngOrderUIOpe extends BaseUIOpe {

    public void LoadListData(RecyclerView recyclerView, final String type, final OrdersRes s, ViewListener listener){

        final String comname = LocalValue.get登录返回信息().getAbbreviationName();
        recyclerView.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_doing, BR.item_main_order_doing, s.getResults(),listener) {

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
                if(NullUtil.isStrEmpty(s.getResults().get(position).getCarLicenseNo())){
                    doingBinding.tvCarno.setVisibility(View.GONE);
                }else{
                    doingBinding.tvCarno.setText(StringUtil.getStr(s.getResults().get(position).getCarLicenseNo()));
                }

                doingBinding.tvCarnum.setText(StringUtil.getStr(s.getResults().get(position).getTotalRecord()));


                DecimalFormat df = new DecimalFormat("#.##");
                doingBinding.tvCurrent.setText(StringUtil.getStr(Double.parseDouble(df.format(s.getResults().get(position).getActualSh())))+"t");



            }
        });
    }
}
