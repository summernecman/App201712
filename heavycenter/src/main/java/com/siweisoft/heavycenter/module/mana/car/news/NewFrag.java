package com.siweisoft.heavycenter.module.mana.car.news;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.network.newsf.UIFNetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewResBean;
import com.siweisoft.heavycenter.data.netd.unit.dirvers.DriverRes;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {


    @Override
    public void doThing() {
        super.doThing();

    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                if(getP().getU().canGo()){
                    getP().getD().newCar(getP().getU().getCarNewReqBean(getP().getD().getCarNewReqBean()), new UIFNetAdapter<CarNewResBean>(this) {
                        @Override
                        public void onResult(boolean success, String msg, CarNewResBean o) {
                            super.onResult(success, msg, o);
                            if(success){
                                getArguments().putBoolean(ValueConstant.FARG_TYPE,true);
                                getBaseUIActivity().onBackPressed();
                            }
                        }
                    });
                }
                break;
        }
    }
}
