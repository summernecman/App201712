package com.siweisoft.heavycenter.module.main.weigt;

//by summer on 2017-12-11.

import android.view.View;

import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

import cn.jpush.android.api.JPushInterface;

public class WeigtFrag extends AppFrag<WeigtUIOpe,WeigtDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();
        getP().getU().bind.title.getMidTV().setText(JPushInterface.getRegistrationID(activity)+"");
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
