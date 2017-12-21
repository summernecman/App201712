package com.siweisoft.heavycenter.module.acct.role;

//by summer on 2017-12-18.

import android.view.View;

import com.android.lib.util.IntentUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

import butterknife.OnClick;

public class RoleFrag extends AppFrag<RoleUIOpe,RoleDAOpe>{

    @OnClick({R.id.lll})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.lll:
                IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                break;
        }
    }

}
