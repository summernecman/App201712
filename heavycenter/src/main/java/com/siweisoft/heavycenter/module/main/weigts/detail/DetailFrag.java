package com.siweisoft.heavycenter.module.main.weigts.detail;

//by summer on 2017-12-11.

import android.view.View;

import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> {

    @Override
    public void initData() {
        super.initData();
        lazyInit();
        setInited();
    }

    @Override
    public void lazyInit() {
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getData());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)activity).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right:
                if(getActivity() instanceof MainAct){
                    MainAct mainAct = (MainAct) getActivity();
                    mainAct.dealScan(this);
                }
                break;
        }
    }



}
