package com.siweisoft.heavycenter.module.main.order.doing;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragKey;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.order.detail.DetailFrag;

public class DoingFrag extends AppFrag<DoingUIOpe,DoingDAOpe> implements ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();
        getP().getU().LoadListData(getP().getD().getData(),this);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.订单,new DetailFrag());
                break;
        }
    }
}
