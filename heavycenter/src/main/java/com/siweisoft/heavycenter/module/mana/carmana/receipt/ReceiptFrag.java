package com.siweisoft.heavycenter.module.mana.carmana.receipt;

//by summer on 2017-12-19.

import com.siweisoft.heavycenter.base.AppFrag;

public class ReceiptFrag extends AppFrag<ReceiptUIOpe,ReceiptDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().LoadListData(getP().getD().getData());
    }
}
