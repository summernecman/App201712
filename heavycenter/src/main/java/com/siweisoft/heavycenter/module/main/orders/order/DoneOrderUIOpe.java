package com.siweisoft.heavycenter.module.main.orders.order;

//by summer on 2018-02-25.

import android.support.v7.widget.RecyclerView;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.databinding.ItemMainOrderDoneBinding;

public class DoneOrderUIOpe extends BaseUIOpe {

    public void LoadListData(RecyclerView recyclerView, final String type, final OrdersRes s, ViewListener listener) {

        final String comname = LocalValue.get登录返回信息().getAbbreviationName();

        recyclerView.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_done, BR.item_main_order_done,s.getResults(),listener){

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
    }
}
