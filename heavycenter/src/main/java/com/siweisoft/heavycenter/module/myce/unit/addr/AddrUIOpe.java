package com.siweisoft.heavycenter.module.myce.unit.addr;

//by summer on 2017-12-19.

import android.content.Context;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragMyceUnitAddrBinding;
import com.siweisoft.heavycenter.databinding.ItemMyceUnitAddrBinding;

import java.util.List;

public class AddrUIOpe extends AppUIOpe<FragMyceUnitAddrBinding>{


    public AddrUIOpe(Context context) {
        super(context);
    }

    public void LoadListData(List<String> s, final ViewListener listener) {

        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_myce_unit_addr, BR.item_myce_unit_addr, s,listener){

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
                ItemMyceUnitAddrBinding binding = (ItemMyceUnitAddrBinding) holder.viewDataBinding;
            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                selecPos = (int) v.getTag(R.id.position);
                notifyDataSetChanged();
            }
        });

    }
}
