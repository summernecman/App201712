package com.siweisoft.heavycenter.module.myce.unit.area.city;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;

import java.io.Serializable;
import java.util.ArrayList;

public class CityFrag extends AppFrag<CityUIOpe,CityDAOpe> implements ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getD().setCitys((CityResBean.ProvinceListBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getD().setProindex(getArguments().getInt(ValueConstant.DATA_POSITION2));
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getCitys().getCityList(),this);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                int pos  = (int) v.getTag(R.id.position);

                if(getP().getD().getCitys().getCityList().get(pos).getCheckStatus()!=CityResBean.ProvinceListBean.CityListBean.CHECK_STATE_FULL){
                    getP().getD().getCitys().getCityList().get(pos).setCheckStatus(CityResBean.ProvinceListBean.CityListBean.CHECK_STATE_FULL);
                }else{
                    getP().getD().getCitys().getCityList().get(pos).setCheckStatus(CityResBean.ProvinceListBean.CityListBean.CHECK_STATE_NULL);
                }
                getP().getU().notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                getArguments().putSerializable(ValueConstant.DATA_DATA, getP().getD().getCitys());
                getArguments().putInt(ValueConstant.DATA_POSITION2,getP().getD().getProindex());
                FragManager2.getInstance().finish(getBaseUIActivity(),getContainerName());
                break;
        }
    }
}
