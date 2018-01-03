package com.siweisoft.heavycenter.module.main.store.check;

//by summer on 2017-12-19.

import com.siweisoft.heavycenter.base.AppFrag;

public class CheckFrag extends AppFrag<CheckUIOpe,CheckDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData());
    }
}
