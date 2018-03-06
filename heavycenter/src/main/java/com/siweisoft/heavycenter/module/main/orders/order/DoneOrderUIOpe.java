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


        recyclerView.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_done, BR.item_main_order_done,s.getResults(),listener){

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position) {
                super.onBindViewHolder(holder,position);
                ItemMainOrderDoneBinding doneBinding = (ItemMainOrderDoneBinding) holder.viewDataBinding;
                doneBinding.getRoot().setSelected(position%2==0?true:false);
                doneBinding.getRoot().setTag(R.id.type,type);

            }
        });
    }
}
