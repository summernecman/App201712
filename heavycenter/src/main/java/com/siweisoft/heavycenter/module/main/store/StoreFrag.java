package com.siweisoft.heavycenter.module.main.store;

//by summer on 2017-12-11.

import android.view.View;

import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class StoreFrag extends AppFrag<StoreUIOpe,StoreDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)getActivity()).getP().getU().switchDrawer();
                break;
        }
    }
}
