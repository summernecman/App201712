package com.siweisoft.heavycenter.module.main.msg.sys;

//by summer on 2017-12-11.

import com.android.lib.base.fragment.BaseUIFrag;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class SysFrag extends AppFrag<SysUIOpe,SysDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().LoadListData(getP().getD().getData());
    }
}
