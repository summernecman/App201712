package com.siweisoft.heavycenter.module.myce.unit.newunit.area.city;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.siweisoft.heavycenter.base.AppFrag;

public class CityFrag extends AppFrag<CityUIOpe,CityDAOpe> implements ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData(),this);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:

                break;
        }
    }
}
