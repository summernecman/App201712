package com.siweisoft.heavycenter.module.mana.carmana;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.bean.TitleBean;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class CarFrag extends AppFrag<CarUIOpe,CarDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRefresh();
        getP().getU().initPages(fragment,getP().getD().getPages());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)(getActivity())).getP().getU().switchDrawer();
                break;
        }
    }

}
