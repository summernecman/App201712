package com.siweisoft.heavycenter.module.main.order.doing;

//by summer on 2017-12-19.

import com.siweisoft.heavycenter.base.AppFrag;

public class DoingFrag extends AppFrag<DoingUIOpe,DoingDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();
        getP().getU().LoadListData(getP().getD().getData());
    }

}
