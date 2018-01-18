package com.siweisoft.heavycenter.module.main.msg;

//by summer on 2017-12-11.

import android.view.View;

import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class MsgFrag extends AppFrag<MsgUIOpe,MsgDAOpe> {


    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void lazyInit() {
        getP().getU().initPages(fragment,getP().getD().getPages());
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
