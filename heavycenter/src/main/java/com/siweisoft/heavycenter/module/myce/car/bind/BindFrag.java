package com.siweisoft.heavycenter.module.myce.car.bind;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.car.bind.BindCarRes;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.module.main.MainAct;

public class BindFrag extends AppFrag<BindUIOpe,BindDAOpe> implements ViewListener {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
        getP().getD().Cars(new UINetAdapter<CarsResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, CarsResBean o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o,BindFrag.this);
            }
        });

    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                CarsResBean.ResultsBean data = (CarsResBean.ResultsBean) v.getTag(R.id.data);
                getP().getD().bindCar(data.getVehicleId(), new UINetAdapter<BindCarRes>(getActivity()) {
                    @Override
                    public void onResult(boolean success, String msg, BindCarRes o) {
                        super.onResult(success, msg, o);
                        if(success){
                            ((MainAct)getBaseUIActivity()).getP().getD().getMyceFrag().initUINET();
                            getBaseUIActivity().onBackPressed();
                        }
                    }
                });
                break;
        }
    }
}
