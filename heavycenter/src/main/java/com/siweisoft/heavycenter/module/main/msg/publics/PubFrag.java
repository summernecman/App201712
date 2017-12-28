package com.siweisoft.heavycenter.module.main.msg.publics;

//by summer on 2017-12-11.

import com.android.lib.base.fragment.BaseUIFrag;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class PubFrag extends AppFrag<PubUIOpe,PubDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();
        getP().getU().LoadListData(getP().getD().getData());
    }
}
