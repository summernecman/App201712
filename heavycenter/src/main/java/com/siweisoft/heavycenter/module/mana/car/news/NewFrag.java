package com.siweisoft.heavycenter.module.mana.car.news;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.car.news.CarNewResBean;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {


    @Override
    public void initdelay() {
        super.initdelay();

    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                if(getP().getU().canGo()){
                    getP().getD().newCar(getP().getU().getCarNewReqBean(getP().getD().getCarNewReqBean()), new UINetAdapter<CarNewResBean>(this,true) {
                        @Override
                        public void onSuccess(CarNewResBean o) {
                            getArguments().putBoolean(ValueConstant.FARG_TYPE,true);
                            getBaseUIAct().onBackPressed();
                        }
                    });
                }
                break;
        }
    }
}
