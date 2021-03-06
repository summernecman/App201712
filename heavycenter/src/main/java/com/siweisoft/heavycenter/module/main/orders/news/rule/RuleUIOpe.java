package com.siweisoft.heavycenter.module.main.orders.news.rule;

//by summer on 2018-01-17.

import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.bean.AppViewHolder;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.data.netd.order.rule.RuleRes;
import com.siweisoft.heavycenter.databinding.FragMainOrderNewRuleBinding;

import java.util.List;

public class RuleUIOpe extends BaseUIOpe<FragMainOrderNewRuleBinding> {


    public void initUI() {
        initRecycle();

    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(RuleRes s, final ViewListener listener) {
        if(s==null){
            return ;
        }
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_order_new_rule, BR.item_main_order_new_rule, s.getData(),listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);

            }
        });
    }

}
