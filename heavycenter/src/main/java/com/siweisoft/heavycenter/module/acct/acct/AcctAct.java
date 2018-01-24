package com.siweisoft.heavycenter.module.acct.acct;

//by summer on 2017-12-14.

import android.os.Bundle;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.module.acct.login.LoginFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.test.TestAct;

public class AcctAct extends AppAct<AcctUIOpe,AcctDAOpe> {


    public static final int 账号ID = R.id.act_acct;

    public static final String 账号 = "账号";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragManager2.getInstance().clear();
        FragManager2.getInstance().setAnim(false).start((BaseUIActivity) activity,账号,账号ID,new LoginFrag());
    }

    public void showAndHidden(Class c){
        getP().getU().showAndHidden(activity, getP().getD().getFrags(), c);
    }

    @Override
    public void onBackPressed() {
        if(!FragManager2.getInstance().finish((BaseUIActivity) activity,getMoudle(),true)){
            super.onBackPressed();
        }
    }
}
