package com.siweisoft.heavycenter.module.main.store.check;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.BaseTextWather;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.databinding.FragMainStoreCheckBinding;
import com.siweisoft.heavycenter.databinding.FragManaStoreListBinding;
import com.siweisoft.heavycenter.databinding.ItemMainStoreBinding;
import com.siweisoft.heavycenter.databinding.ItemMainStoreCheckBinding;

import java.util.List;

public class CheckUIOpe extends AppUIOpe<FragMainStoreCheckBinding>{


    public CheckUIOpe(Context context) {
        super(context);
    }


    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(final StoresResBean o) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_main_store_check, BR.item_main_store_check, o.getResults()){

            int darkcolor = context.getResources().getColor(R.color.color_item_main_trans_dark);
            int lightcolor = context.getResources().getColor(R.color.color_item_main_trans_light);

            @Override
            public void onBindViewHolder(AppViewHolder holder, final int position) {
                super.onBindViewHolder(holder, position);
                final ItemMainStoreCheckBinding itemMainStoreCheckBinding = (ItemMainStoreCheckBinding) holder.viewDataBinding;
                itemMainStoreCheckBinding.tvCurrent.setText(StringUtil.getStr(o.getResults().get(position).getCurrentStock())+"t");
                itemMainStoreCheckBinding.etInput.addTextChangedListener(new BaseTextWather(){
                    @Override
                    public void afterTextChanged(Editable s) {
                        super.afterTextChanged(s);
                        String ss = s.toString();
                        if(NullUtil.isStrEmpty(ss.trim())){
                            ss= "0";
                        }
                        itemMainStoreCheckBinding.tvAfter.setText(StringUtil.getStr( Float.parseFloat(ss.toString())- o.getResults().get(position).getCurrentStock())+"t");
                        o.getResults().get(position).setAfterAdjust(Float.parseFloat(ss.toString()));
                    }
                });
            }
        });
    }

}
