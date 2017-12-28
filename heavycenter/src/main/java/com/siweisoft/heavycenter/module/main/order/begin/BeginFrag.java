package com.siweisoft.heavycenter.module.main.order.begin;

//by summer on 2017-12-19.

import com.siweisoft.heavycenter.base.AppFrag;

public class BeginFrag extends AppFrag<BeginUIOpe,BeginDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();
        getP().getU().LoadListData(getP().getD().getData());
    }
}
