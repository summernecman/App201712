package com.siweisoft.heavycenter.module.myce.unit.newunit.area;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragMyceUnitNewAreaBinding;
import com.siweisoft.heavycenter.databinding.FragMyceUnitNewBinding;
import com.siweisoft.heavycenter.databinding.ItemManaGoodBinding;

import java.util.List;

public class AreaUIOpe extends AppUIOpe<FragMyceUnitNewAreaBinding>{


    public AreaUIOpe(Context context) {
        super(context);
    }


    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    public void LoadListData(List<String> s, final ViewListener listener) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_myce_unit_new_area, BR.item_myce_unit_new_area, s,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
            }
        });
    }

}
