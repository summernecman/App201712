package com.siweisoft.heavycenter.module.acct.repwd;

//by summer on 2017-12-14.

import android.view.View;

import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;
import com.siweisoft.heavycenter.module.acct.login.LoginFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

import butterknife.OnClick;

public class RepwdFrag extends AppFrag<RepwdUIOpe,RepwdDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initBg(getP().getD().getImageUrl());
    }


    @OnClick({R.id.save,R.id.ftv_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.save:
                break;
                case R.id.ftv_back:
                    ((AcctAct)getActivity()).showAndHidden(LoginFrag.class);
                    break;
        }
    }

}
