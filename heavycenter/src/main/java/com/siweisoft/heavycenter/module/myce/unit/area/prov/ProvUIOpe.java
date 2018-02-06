package com.siweisoft.heavycenter.module.myce.unit.area.prov;

//by summer on 2017-12-19.

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.NullUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;
import com.siweisoft.heavycenter.databinding.FragMyceUnitNewProvBinding;
import com.siweisoft.heavycenter.databinding.ItemMyceUnitNewAreaBinding;

import java.util.List;

public class ProvUIOpe extends AppUIOpe<FragMyceUnitNewProvBinding>{




    public void initRecycle(){
        bind.recycle.setLayoutManager(new GridLayoutManager(context,3));
    }

    public void LoadListData(final List<CityResBean.ProvinceListBean> s, final ViewListener listener) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_myce_unit_new_area, BR.item_myce_unit_new_area, s,listener){
            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
                ItemMyceUnitNewAreaBinding binding = (ItemMyceUnitNewAreaBinding) holder.viewDataBinding;
                if(NullUtil.isStrEmpty(s.get(position).getValue())){
                    binding.llPros.setVisibility(View.GONE);
                }else{
                    binding.llPros.setVisibility(View.VISIBLE);
                }
                binding.ivState.setType(s.get(position).getCheckStatus());
            }
        });
    }

    public void notifyDataSetChanged(){
        if(bind.recycle.getAdapter()!=null){
            bind.recycle.getAdapter().notifyDataSetChanged();
        }
    }

}
