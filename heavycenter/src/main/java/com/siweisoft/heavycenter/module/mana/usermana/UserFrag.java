package com.siweisoft.heavycenter.module.mana.usermana;

//by summer on 2017-12-14.

import android.view.View;

import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class UserFrag extends AppFrag<UserUIOpe,UserDAOpe> {

    @Override
    public void initData() {
        super.initData();
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
