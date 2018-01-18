package com.siweisoft.heavycenter.module.mana.car.news;

//by summer on 2017-12-19.

import android.content.Context;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.bean.AppViewHolder;
import com.android.lib.util.NullUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.BR;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewReqBean;
import com.siweisoft.heavycenter.databinding.FragManaCarDetailBinding;
import com.siweisoft.heavycenter.databinding.FragManaCarNewBinding;
import com.siweisoft.heavycenter.databinding.ItemManaCarDetailDriverBinding;

import java.util.List;

public class NewUIOpe extends AppUIOpe<FragManaCarDetailBinding>{


    public NewUIOpe(Context context) {
        super(context);
    }

    public CarNewReqBean getCarNewReqBean(CarNewReqBean carNewReqBean) {
        carNewReqBean.setCarLicenseNo(bind.itemCarlicenseno.getMidET().getText().toString());
        carNewReqBean.setCarBrand(bind.itemBrand.getMidET().getText().toString());
        carNewReqBean.setMaxCapacity(Integer.parseInt(bind.itemMaxweight.getMidET().getText().toString()));
        carNewReqBean.setEmptyWeight(Integer.parseInt(bind.itemEmptyweight.getMidET().getText().toString()));
        carNewReqBean.setIcCard(bind.itemIccard.getMidET().getText().toString());
        return carNewReqBean;
    }

    public boolean canGo(){
        if(NullUtil.isStrEmpty(bind.itemCarlicenseno.getMidET().getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入车牌号");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemBrand.getMidET().getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入车牌号");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemMaxweight.getMidET().getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入车牌号");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemEmptyweight.getMidET().getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入车牌号");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.itemIccard.getMidET().getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入车牌号");
            return false;
        }
        return true;
    }

    public void initRecycle(){
        bind.recycle.setLayoutManager(new LinearLayoutManager(context));
    }

    @Override
    public void initUI(BaseUIFrag baseUIFrag) {
        super.initUI(baseUIFrag);
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
}
