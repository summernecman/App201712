package com.siweisoft.heavycenter.module.myce.unit.newunit.area.prov;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.myce.unit.newunit.area.city.CityFrag;

public class ProvFrag extends AppFrag<ProvUIOpe,ProvDAOpe> implements ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData(),this);
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new CityFrag());
                break;
        }
    }
}
