package com.siweisoft.heavycenter.module.myce.unit.area.prov;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;
import com.siweisoft.heavycenter.module.myce.unit.area.city.CityFrag;

import java.io.Serializable;

import butterknife.OnClick;

public class ProvFrag extends AppFrag<ProvUIOpe,ProvDAOpe> implements ViewListener{

    public static final String 选择一个城市 = "选择一个城市";

    public static final String 选择多个城市 = "选择多个城市";

    public static final String 选择省 = "选择省";


    public static ProvFrag getInstance(String state,String title){
        ProvFrag provFrag = new ProvFrag();
        provFrag.setArguments(new Bundle());
        provFrag.getArguments().putString(ValueConstant.DATA_DATA,state);
        provFrag.getArguments().putString(ValueConstant.DATA_TYPE,title);
        provFrag.getP().getD().setState(state);
        return provFrag;
    }

    @Override
    public void initNow() {
        super.initNow();
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getPro(),this);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()){
                    case R.id.iv_state:
                        break;
                    default:
                        FragManager2.getInstance().start(getBaseUIAct(), get容器(),
                                CityFrag.getInstance("选择城市",getP().getD().getState(), (CityResBean.ProvinceListBean) v.getTag(R.id.data),(int) v.getTag(R.id.position)));
                        break;
                }
                break;
        }
    }


    @Override
    public void onResult(int res, Bundle bundle) {
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
        String s2= "";
        for(int i=0;i<getP().getD().getPro().size();i++){
            if(getP().getD().getPro().get(i).getCheckStatus()== CityResBean.ProvinceListBean.CHECK_STATE_NULL){
                continue;
            }
            if(getP().getD().getPro().get(i).getCheckStatus()== CityResBean.ProvinceListBean.CHECK_STATE_FULL){
                s+=getP().getD().getPro().get(i).getValue()+",";
                s2+=getP().getD().getPro().get(i).getName()+",";
                continue;
            }
            if(getP().getD().getPro().get(i).getCityList()==null){
                continue;
            }
            for(int j=0;j<getP().getD().getPro().get(i).getCityList().size();j++){
                if(getP().getD().getPro().get(i).getCityList().get(j).getCheckStatus()== CityResBean.ProvinceListBean.CityListBean.CHECK_STATE_FULL){
                    s+=getP().getD().getPro().get(i).getCityList().get(j).getValue()+",";
                    s2+=getP().getD().getPro().get(i).getCityList().get(j).getName()+",";
                }
            }
        }
        if(s.endsWith(",")){
            s= s.substring(0,s.length()-1);
            s2= s2.substring(0,s2.length()-1);
        }
        getArguments().putString(ValueConstant.DATA_RES,s);
        getArguments().putString(ValueConstant.DATA_RES2,s2);
    }


    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.ftv_right2:
                getArguments().putString(ValueConstant.DATA_RES, getArguments().getString(ValueConstant.DATA_RES));
                getArguments().putString(ValueConstant.DATA_RES2, getArguments().getString(ValueConstant.DATA_RES2));
                getBaseUIAct().onBackPressed();
                break;
        }
    }
}
