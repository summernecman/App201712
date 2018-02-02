package com.siweisoft.heavycenter.module.main.trans.detail;

//by summer on 2017-12-18.

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.Test;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.trans.detail.TransDetailRes;
import com.siweisoft.heavycenter.module.main.MainAct;

public class TransDetailFrag extends AppFrag<TransDetailUIOpe,TransDetailDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        getP().getD().setTransid(getArguments().getInt(ValueConstant.DATA_DATA,-1));
        getP().getD().detailTrans(getP().getD().getTransid(), new UINetAdapter<TransDetailRes>(getActivity()) {
            @Override
            public void onSuccess(TransDetailRes o) {
                super.onSuccess(o);
                getP().getU().initUI(o);
            }
        });
    }
}
