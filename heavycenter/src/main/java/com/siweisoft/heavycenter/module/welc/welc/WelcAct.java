package com.siweisoft.heavycenter.module.welc.welc;

//by summer on 2017-12-14.

import android.os.Bundle;
import android.view.View;

import com.android.lib.util.IntentUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;

import butterknife.OnClick;

public class WelcAct extends AppAct<WelcUIOpe,WelcDAOpe> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initBg(getP().getD().getImageUrl());
    }

    @OnClick({R.id.image})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.image:
                IntentUtil.startActivityWithFinish(this, AcctAct.class,null);
                break;
        }
    }
}
