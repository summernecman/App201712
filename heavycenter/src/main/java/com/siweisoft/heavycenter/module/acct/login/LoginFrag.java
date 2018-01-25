package com.siweisoft.heavycenter.module.acct.login;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;
import com.siweisoft.heavycenter.module.acct.regist.RegistFrag;
import com.siweisoft.heavycenter.module.acct.repwd.RepwdFrag;
import com.siweisoft.heavycenter.module.acct.role.RoleFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

import butterknife.OnClick;

public class LoginFrag extends AppFrag<LoginUIOpe,LoginDAOpe> {

    @Override
    public void initData() {
        super.initData();

    }

    @OnClick({R.id.login,R.id.regist,R.id.repwd})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                if(getP().getU().go()){
                    getP().getD().login(getP().getU().getLoginReqBean(), new UINetAdapter<LoginResBean>(activity) {
                        @Override
                        public void onResult(boolean success, String msg, LoginResBean loginResBean) {
                            if(success){
                                LocalValue.saveLoginReq(getP().getU().getLoginReqBean());
                                LocalValue.saveLoginInfo(loginResBean);
                                if(loginResBean.getUserType()== UserTypeReqBean.驾驶员 || loginResBean.getUserType()== UserTypeReqBean.非驾驶员){
                                    LocalValue.setAutoLogin(true);
                                    IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                                }else{
                                    FragManager2.getInstance().start((BaseUIActivity) activity, AcctAct.账号,AcctAct.账号ID,new RoleFrag());
                                }
                            }
                        }
                    });
                }
                break;
            case R.id.regist:
                FragManager2.getInstance().start((BaseUIActivity) activity, AcctAct.账号,AcctAct.账号ID,new RegistFrag());
                //FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getIndex(),new RegistFrag());
                break;
            case R.id.repwd:
                FragManager2.getInstance().start((BaseUIActivity) activity, AcctAct.账号,AcctAct.账号ID,new RepwdFrag());
                //FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getIndex(),new RepwdFrag());
                break;
        }
    }

}
