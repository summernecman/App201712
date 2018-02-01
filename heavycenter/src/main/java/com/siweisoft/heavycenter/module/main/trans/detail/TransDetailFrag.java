package com.siweisoft.heavycenter.module.main.trans.detail;

//by summer on 2017-12-18.

import com.siweisoft.heavycenter.Test;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class TransDetailFrag extends AppFrag<TransDetailUIOpe,TransDetailDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        getP().getU().initUI(new Test().getTransRes().getResults().get(0));
    }
}
