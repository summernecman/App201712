package com.siweisoft.heavycenter.module.mana.carmana.my;

//by summer on 2017-12-19.

import com.siweisoft.heavycenter.base.AppFrag;

public class MyFrag extends AppFrag<MyUIOpe,MyDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().LoadListData(getP().getD().getData());
    }
}
