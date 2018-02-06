package com.siweisoft.heavycenter.module.myce.unit.area.prov;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.util.NullUtil;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;

import java.util.List;

public class ProvDAOpe extends AppDAOpe {

    private List<CityResBean.ProvinceListBean> pro ;

    private String state = ProvFrag.选择多个城市;




    public List<CityResBean.ProvinceListBean> getPro() {
        if(pro==null){
            pro = LocalValue.get省市排序列表();
        }
        return pro;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        if(NullUtil.isStrEmpty(state)){
            return;
        }
        this.state = state;
    }
}
