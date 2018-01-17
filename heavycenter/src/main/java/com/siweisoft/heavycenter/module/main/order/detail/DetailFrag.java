package com.siweisoft.heavycenter.module.main.order.detail;

//by summer on 2017-12-19.

import com.siweisoft.heavycenter.base.AppFrag;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
    }
}
