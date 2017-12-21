package com.siweisoft.heavycenter.module.mana.carmana.send;

//by summer on 2017-12-19.

import com.siweisoft.heavycenter.base.AppFrag;

public class SendFrag extends AppFrag<SendUIOpe,SendDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().LoadListData(getP().getD().getData());
    }
}
