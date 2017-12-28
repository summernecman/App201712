package com.siweisoft.heavycenter.module.main.msg.all;

//by summer on 2017-12-11.

import com.android.lib.base.fragment.BaseUIFrag;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class AllFrag extends AppFrag<AllUIOpe,AllDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();
        getP().getU().LoadListData(getP().getD().getData());
    }


}
