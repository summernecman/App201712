package com.siweisoft.heavycenter.module.acct.login;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.UINetAdapter;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
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
        getP().getU().initBg(getP().getD().getImageUrl());
    }

    @OnClick({R.id.login,R.id.regist,R.id.repwd})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                if(getP().getU().go()){
                    getP().getD().login(getP().getU().getLoginReqBean(), new UINetAdapter(getActivity()) {
                        @Override
                        public void onNetWorkResult(boolean success, BaseResBean o) {
                            LoginResBean loginResBean = GsonUtil.getInstance().fromJson(o.getData().toString(),LoginResBean.class);
                            if(loginResBean.getCode().equals("200")){
                                FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getIndex(),new RoleFrag());
                            }else{
                                ToastUtil.getInstance().showLong(getActivity(),loginResBean.getMessage());
                            }
                        }
                    });
                }
                break;
            case R.id.regist:
                FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getIndex(),new RegistFrag());
                break;
            case R.id.repwd:
                FragManager.getInstance().startFragment(activity.getSupportFragmentManager(),getIndex(),new RepwdFrag());
                break;
        }
    }

}
