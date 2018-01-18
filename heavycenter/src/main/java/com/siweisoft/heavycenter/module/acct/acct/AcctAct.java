package com.siweisoft.heavycenter.module.acct.acct;

//by summer on 2017-12-14.

import android.os.Bundle;

import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.acct.logout.LogOutResBean;
import com.siweisoft.heavycenter.module.acct.login.LoginFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

public class AcctAct extends AppAct<AcctUIOpe,AcctDAOpe> {


    public static final int ROOT_ID = R.id.act_acct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(LocalValue.isAutoLogin()){
            getP().getD().getInfo(new UINetAdapter<LoginResBean>(this) {
                @Override
                public void onResult(boolean success, String msg, LoginResBean o) {
                    super.onResult(success, msg, o);
                    if(success){
                        IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                        finish();
                    }else{
                        FragManager.getInstance().clear();
                        getP().getD().setIndex(FragManager.getInstance().addId(ROOT_ID));
                        //getP().getU().initPages(activity,getP().getD().getFrags());
                        FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getP().getD().getIndex(),new LoginFrag());
                    }
                }
            });
            return;
        }
        FragManager.getInstance().clear();
        getP().getD().setIndex(FragManager.getInstance().addId(ROOT_ID));
        //getP().getU().initPages(activity,getP().getD().getFrags());
        FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getP().getD().getIndex(),new LoginFrag());
    }

    public void showAndHidden(Class c){
        getP().getU().showAndHidden(activity, getP().getD().getFrags(), c);
    }

    @Override
    public void onBackPressed() {
        if(FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID)!=null&&FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID).size()>0){
            activity.getSupportFragmentManager().beginTransaction().remove(
                    FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID).get(
                            FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID).size()-1))
                    .commit();
            FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID).remove(FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID).size()-1);
        }else
        if(FragManager.getInstance().getFragMaps().get(getP().getD().getIndex())!=null&& FragManager.getInstance().getFragMaps().get(getP().getD().getIndex()).size()>1){
            FragManager.getInstance().finish(activity.getSupportFragmentManager(),getP().getD().getIndex());
        }else{
            getP().getD().logOut(new NetAdapter<LogOutResBean>(this));
            super.onBackPressed();
        }
    }
}
