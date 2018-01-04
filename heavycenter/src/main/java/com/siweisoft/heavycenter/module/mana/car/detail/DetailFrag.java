package com.siweisoft.heavycenter.module.mana.car.detail;

//by summer on 2017-12-19.

import com.siweisoft.heavycenter.base.AppFrag;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> {


    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData());
    }
}
