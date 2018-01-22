package com.siweisoft.heavycenter.module.acct.acct;

//by summer on 2017-12-14.

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.acct.logout.LogOutResBean;
import com.siweisoft.heavycenter.module.acct.login.LoginFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class AcctAct extends AppAct<AcctUIOpe,AcctDAOpe> {


    public static final int 账号ID = R.id.act_acct;

    public static final String 账号 = "账号";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(LocalValue.isAutoLogin()){
            getP().getD().getInfo(new UINetAdapter<LoginResBean>(this) {
                @Override
                public void onResult(boolean success, String msg, LoginResBean o) {
                    super.onResult(success, msg, o);
                    if(success){
                        LocalValue.saveLoginInfo(o);
                        IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                        finish();
                    }else{
                        FragManager.getInstance().clear();
                        getP().getD().setIndex(FragManager.getInstance().addId(账号ID));
                        //getP().getU().initPages(activity,getP().getD().getFrags());

                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.add(账号ID,new LoginFrag());
                        transaction.commitNowAllowingStateLoss();
                        //FragManager2.getInstance().start((BaseUIActivity) activity,账号,账号ID,new LoginFrag());
                       // FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getP().getD().getIndex(),new LoginFrag());
                    }
                }
            });
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(账号ID,new LoginFrag());
        transaction.commitNowAllowingStateLoss();
    }

    public void showAndHidden(Class c){
        getP().getU().showAndHidden(activity, getP().getD().getFrags(), c);
    }

    @Override
    public void onBackPressed() {
        if(!FragManager2.getInstance().finish((BaseUIActivity) activity,getMoudle())){
            super.onBackPressed();
        }
    }
}
