package com.siweisoft.heavycenter.module.mana.car.detail;

//by summer on 2017-12-19.

import com.android.lib.constant.ValueConstant;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> {


    @Override
    public void initData() {
        super.initData();
        getP().getD().setCarinfo((CarsResBean.ResultsBean) getArguments().getSerializable(ValueConstant.DATA_DATA));
        getP().getU().initData(getP().getD().getCarinfo());
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData());
    }
}
