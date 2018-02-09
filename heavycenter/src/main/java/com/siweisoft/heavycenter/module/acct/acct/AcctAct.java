package com.siweisoft.heavycenter.module.acct.acct;

//by summer on 2017-12-14.

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.module.acct.login.LoginFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.test.SharedElementFragment1;

public class AcctAct extends AppAct<AcctUIOpe,AcctDAOpe> {


    public static final int 账号界面根布局 = R.id.act_acct;

    public static final String 账号 = "账号";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragManager2.getInstance().clear();
       FragManager2.getInstance().setAnim(false).start(getActivity(),账号, 账号界面根布局,new LoginFrag());

    }

    @Override
    public void onBackPressed() {
        if(!FragManager2.getInstance().finish(getActivity(),getMoudle(),true)){
            super.onBackPressed();
        }
    }
}
