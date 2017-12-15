package com.siweisoft.heavycenter.module.acct.acct;

//by summer on 2017-12-14.

import android.os.Bundle;

import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.module.acct.login.LoginFrag;

public class AcctAct extends AppAct<AcctUIOpe,AcctDAOpe> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initPages(activity,getP().getD().getFrags());
    }

    public void showAndHidden(Class c){
        getP().getU().showAndHidden(activity, getP().getD().getFrags(), c);
    }

    @Override
    public void onBackPressed() {
        if(getP().getD().getShowFrag().getClass().getName().equals(LoginFrag.class.getName())){
            super.onBackPressed();
        }else{
            showAndHidden(LoginFrag.class);
        }
    }
}
