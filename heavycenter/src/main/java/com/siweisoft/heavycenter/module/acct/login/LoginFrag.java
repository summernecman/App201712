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
                if(getP().getU().is输入完全()){
                    getP().getD().go登录(getP().getU().getLoginReqBean(), new UINetAdapter<LoginResBean>(activity) {
                        @Override
                        public void onSuccess(LoginResBean loginResBean) {
                            LocalValue.save登录参数(getP().getU().getLoginReqBean());
                            LocalValue.save登录返回信息(loginResBean);
                            if(loginResBean.is选择了角色()){
                                LocalValue.set自动登录(true);
                                IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                            }else{
                                FragManager2.getInstance().start((BaseUIActivity) activity, AcctAct.账号,AcctAct.账号界面根布局,new RoleFrag());
                            }
                        }
                    });
                }
                break;
            case R.id.regist:
                FragManager2.getInstance().start((BaseUIActivity) activity, AcctAct.账号,AcctAct.账号界面根布局,new RegistFrag());
                break;
            case R.id.repwd:
                FragManager2.getInstance().start((BaseUIActivity) activity, AcctAct.账号,AcctAct.账号界面根布局,new RepwdFrag());
                break;
        }
    }

}
