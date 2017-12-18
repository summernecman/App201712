package com.siweisoft.heavycenter.module.acct.login;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.IntentUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
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
                FragmentUtil2.getInstance().addNoAnim(activity,AcctAct.ROOT_ID,new RoleFrag());
                //IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                break;
            case R.id.regist:
                ((AcctAct)getActivity()).showAndHidden(RegistFrag.class);
                break;
            case R.id.repwd:
                ((AcctAct)getActivity()).showAndHidden(RepwdFrag.class);
                break;
        }
    }
}
