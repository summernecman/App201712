package com.siweisoft.heavycenter.module.mana.car.my;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.module.mana.car.detail.DetailFrag;

public class MyFrag extends AppFrag<MyUIOpe,MyDAOpe> implements ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();

        getP().getD().Cars(new UINetAdapter<CarsResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, CarsResBean o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o.getResults(),MyFrag.this);
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(),getIndex(),new DetailFrag());
                break;
        }
    }
}
