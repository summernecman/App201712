package com.siweisoft.heavycenter.module.mana.car.detail;

//by summer on 2017-12-19.

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.databinding.FragManaCarDetailBinding;
import com.siweisoft.heavycenter.databinding.ItemManaCarDetailDriverBinding;

import java.util.List;

public class DetailUIOpe extends AppUIOpe<FragManaCarDetailBinding>{


    public DetailUIOpe(Context context) {
        super(context);
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void initUI(BaseUIFrag baseUIFrag) {
        super.initUI(baseUIFrag);
        bind.llInput.setVisibility(View.GONE);
        bind.itemBrand.setEdit(false);
        bind.itemEmptyweight.setEdit(false);
        bind.itemMaxweight.setEdit(false);
        bind.itemIccard.setEdit(false);
    }

    public void LoadListData(List<String> s) {
        bind.recycle.setAdapter(new AppsDataBindingAdapter(context, R.layout.item_mana_car_detail_driver, BR.item_mana_car_detail_driver, s){

            @Override
            public void onBindViewHolder(AppViewHolder holder, int position, List<Object> payloads) {
                super.onBindViewHolder(holder, position, payloads);
                ItemManaCarDetailDriverBinding binding = (ItemManaCarDetailDriverBinding) holder.viewDataBinding;

                if(selecPos == position){
                    binding.ivCheck.setSelected(true);
                }else{
                    binding.ivCheck.setSelected(false);
                }
            }

            @Override
            public void onClick(View v) {
                super.onClick(v);
                selecPos = (int) v.getTag(R.id.position);
                notifyDataSetChanged();
            }
        });
    }

    public void initData(CarsResBean.ResultsBean data){

        bind.itemBrand.setMidTVTxt(StringUtil.getStr(data.getCarBrand()));
        bind.itemEmptyweight.setMidTVTxt(StringUtil.getStr(data.getEmptyWeight()));
        bind.itemMaxweight.setMidTVTxt(StringUtil.getStr(data.getMaxCapacity()));
        bind.itemIccard.setMidTVTxt(StringUtil.getStr(data.getIcCard()));
        bind.title.getMidTV().setText(StringUtil.getStr(data.getCarLicenseNo()));
    }
}
