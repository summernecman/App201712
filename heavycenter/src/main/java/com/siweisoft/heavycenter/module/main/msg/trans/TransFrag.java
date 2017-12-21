package com.siweisoft.heavycenter.module.main.msg.trans;

//by summer on 2017-12-11.


import com.siweisoft.heavycenter.base.AppFrag;

public class TransFrag extends AppFrag<TransUIOpe,TransDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().LoadListData(getP().getD().getData());
    }
}
