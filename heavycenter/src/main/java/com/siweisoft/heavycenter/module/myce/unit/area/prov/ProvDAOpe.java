package com.siweisoft.heavycenter.module.myce.unit.area.prov;

//by summer on 2017-12-19.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProvDAOpe extends AppDAOpe {

    private List<CityResBean.ProvinceListBean> pro ;

    public ProvDAOpe(Context context) {
        super(context);
    }



    public List<CityResBean.ProvinceListBean> getPro() {
        if(pro==null){
            pro = LocalValue.getProlList();
        }
        return pro;
    }
}
