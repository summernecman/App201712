package com.siweisoft.heavycenter.module.myce.unit.area.prov;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.bean.databean.Value;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;
import com.siweisoft.heavycenter.module.myce.unit.area.city.CityFrag;

import java.io.Serializable;
import java.util.ArrayList;

public class ProvFrag extends AppFrag<ProvUIOpe,ProvDAOpe> implements ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getPro(),this);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                Bundle bundle = new Bundle();
                bundle.putSerializable(ValueConstant.DATA_POSITION2, (int) v.getTag(R.id.position));
                bundle.putSerializable(ValueConstant.DATA_DATA, (Serializable) v.getTag(R.id.data));
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new CityFrag(),bundle);
                break;
        }
    }


    @Override
    public void onRestart(int res, Bundle bundle) {
        int proindex = bundle.getInt(ValueConstant.DATA_POSITION2,-1);
        if(proindex==-1){
            return;
        }
        CityResBean.ProvinceListBean citys  = (CityResBean.ProvinceListBean) bundle.getSerializable(ValueConstant.DATA_DATA);
        int num=0;
        for(int i=0;i<citys.getCityList().size();i++) {
            if (citys.getCityList().get(i).getCheckStatus() == CityResBean.ProvinceListBean.CityListBean.CHECK_STATE_FULL) {
                ++num;
            }
        }
        if(num==citys.getCityList().size()){
            getP().getD().getPro().get(proindex).setCheckStatus(CityResBean.ProvinceListBean.CHECK_STATE_FULL);
        }else if(num==0){
            getP().getD().getPro().get(proindex).setCheckStatus(CityResBean.ProvinceListBean.CHECK_STATE_NULL);
        }else{
            getP().getD().getPro().get(proindex).setCheckStatus(CityResBean.ProvinceListBean.CHECK_STATE_HALF);
        }
        getP().getU().notifyDataSetChanged();

        String s= "";
        for(int i=0;i<getP().getD().getPro().size();i++){
            if(getP().getD().getPro().get(i).getCheckStatus()== CityResBean.ProvinceListBean.CHECK_STATE_NULL){
                continue;
            }
            if(getP().getD().getPro().get(i).getCheckStatus()== CityResBean.ProvinceListBean.CHECK_STATE_FULL){
                s+=getP().getD().getPro().get(i).getValue()+",";
                continue;
            }
            if(getP().getD().getPro().get(i).getCityList()==null){
                continue;
            }
            for(int j=0;j<getP().getD().getPro().get(i).getCityList().size();j++){
                if(getP().getD().getPro().get(i).getCityList().get(j).getCheckStatus()== CityResBean.ProvinceListBean.CityListBean.CHECK_STATE_FULL){
                    s+=getP().getD().getPro().get(i).getCityList().get(j).getValue()+",";
                }
            }
        }
        if(s.endsWith(",")){
            s= s.substring(s.length()-1,s.length());
        }
        getArguments().putString(ValueConstant.DATA_RES,s);
    }
}
