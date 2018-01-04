package com.siweisoft.heavycenter.module.myce.unit.newunit.area.city;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragMyceUnitNewCityBinding;

import java.util.List;

public class CityUIOpe extends AppUIOpe<FragMyceUnitNewCityBinding>{


    public CityUIOpe(Context context) {
        super(context);
    }


    public void initRecycle(){
        bind.recycle.setLayoutManager(new GridLayoutManager(context,3));
    }

    public void LoadListData(List<String> s, final ViewListener listener) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_myce_unit_new_city, BR.item_myce_unit_new_city, s,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
            }
        });
    }

}
