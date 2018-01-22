package com.siweisoft.heavycenter.module.main.weigts;

//by summer on 2017-12-11.

import android.view.View;

import com.android.lib.util.system.HandleUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

import cn.jpush.android.api.JPushInterface;

public class WeigtsFrag extends AppFrag<WeigtsUIOpe,WeigtsDAOpe> {

    @Override
    public void initData() {
        super.initData();
        lazyInit();
        setInited();
    }

    @Override
    public void lazyInit() {
        getP().getU().initPages(this,getP().getD().getPages());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)activity).getP().getU().switchDrawer();
                break;
        }
    }
}
