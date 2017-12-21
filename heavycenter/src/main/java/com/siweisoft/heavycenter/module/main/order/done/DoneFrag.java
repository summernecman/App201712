package com.siweisoft.heavycenter.module.main.order.done;

//by summer on 2017-12-19.

import com.siweisoft.heavycenter.base.AppFrag;

public class DoneFrag extends AppFrag<DoneUIOpe,DoneDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().LoadListData(getP().getD().getData());
    }

}
