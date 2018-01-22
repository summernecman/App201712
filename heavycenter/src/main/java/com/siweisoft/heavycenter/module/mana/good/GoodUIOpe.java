package com.siweisoft.heavycenter.module.mana.good;

//by summer on 2017-12-14.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.mana.good.list.GoodListRes;
import com.siweisoft.heavycenter.databinding.FragManaGoodBinding;
import com.siweisoft.heavycenter.databinding.ItemManaGoodBinding;

import java.util.List;

public class GoodUIOpe extends AppUIOpe<FragManaGoodBinding> {

    public GoodUIOpe(Context context) {
        super(context);
    }


    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(GoodListRes o, final ViewListener listener) {
        if(o==null || o.getResults()==null || o.getResults().size()==0){
            getFrag().showTips("暂无数据");
            return;
        }
        getFrag().removeTips();
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_mana_good, BR.item_mana_good, o.getResults(),listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
                ItemManaGoodBinding binding = (ItemManaGoodBinding) holder.viewDataBinding;
                binding.swipe.setOnClickListener(this);
            }
        });
    }

    public void initRefresh(OnRefreshListener refreshListener){
        bind.refreshLayout.setOnRefreshListener(refreshListener);
    }

    public void finishRefresh(){
        bind.refreshLayout.finishRefresh();
    }


    public void autoRefresh(){
        bind.refreshLayout.autoRefresh();
    }


}
