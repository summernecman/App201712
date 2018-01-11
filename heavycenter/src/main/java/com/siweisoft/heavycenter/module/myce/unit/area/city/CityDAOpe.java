package com.siweisoft.heavycenter.module.myce.unit.area.city;

//by summer on 2017-12-19.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.netd.other.city.CityResBean;

import java.util.ArrayList;

public class CityDAOpe extends AppDAOpe {

    CityResBean.ProvinceListBean citys;

    int proindex=0;

    public CityDAOpe(Context context) {
        super(context);
    }


    public CityResBean.ProvinceListBean getCitys() {
        return citys;
    }

    public void setCitys(CityResBean.ProvinceListBean citys) {
        this.citys = citys;
    }

    public int getProindex() {
        return proindex;
    }

    public void setProindex(int proindex) {
        this.proindex = proindex;
    }
}
