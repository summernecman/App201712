package com.siweisoft.heavycenter.module.myce.unit.addr;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.siweisoft.heavycenter.base.AppFrag;

public class AddrFrag extends AppFrag<AddrUIOpe,AddrDAOpe> implements ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().LoadListData(getP().getD().getData(),this);
    }

    @Override
    public void onInterupt(int type, View v) {

    }
}
