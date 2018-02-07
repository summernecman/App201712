package com.siweisoft.heavycenter.module.mana.car;

//by summer on 2017-12-14.

import android.os.Bundle;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;
import com.siweisoft.heavycenter.module.mana.car.detail.CarDetailValue;
import com.siweisoft.heavycenter.module.mana.car.detail.DetailFrag;
import com.siweisoft.heavycenter.module.mana.car.my.MyFrag;

import butterknife.OnClick;

public class CarFrag extends AppFrag<CarUIOpe,CarDAOpe> {

    @Override
    public void initdelay() {
        super.initdelay();
        getP().getD().initPages();
        getP().getU().initPages(getFrag(),getP().getD().getPages());

    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.FARG_REQ,1);
                bundle.putString(ValueConstant.DATA_DATA2, CarDetailValue.新建车辆);
                FragManager2.getInstance().start(getBaseUIAct(), MainValue.主界面,MainValue.主界面ID,new DetailFrag(),bundle);
                break;
        }

    }

    @Override
    public void onResult(int res, Bundle bundle) {
        super.onResult(res, bundle);
        switch (res){
            case 1:
                if(bundle==null || !bundle.getBoolean(ValueConstant.FARG_TYPE,false)){
                    return;
                }
                if(bundle.getBoolean(ValueConstant.FARG_TYPE,false)){
                    MyFrag myFrag = (MyFrag) getP().getD().getPages().get(1);
                    myFrag. onRefresh(null);;
                }
                break;
        }
    }
}
