package com.siweisoft.heavycenter.module.acct.role;

//by summer on 2017-12-18.

import android.view.View;

import com.android.lib.util.IntentUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;

import butterknife.OnClick;

public class RoleFrag extends AppFrag<RoleUIOpe,RoleDAOpe>{

    @OnClick({R.id.tv_notdriver,R.id.tv_driver})
    public void onClick(View v){
        super.onClick(v);
        switch (v.getId()){
            case R.id.tv_notdriver:
                getP().getU().showTip(getIndex(),new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case R.id.tv_sure:
                                IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                                break;
                        }
                    }
                });
                break;
            case R.id.tv_driver:
                getP().getU().showTip(getIndex(),new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()){
                            case R.id.tv_sure:
                                IntentUtil.startActivityWithFinish(activity, MainAct.class,null);
                                break;
                        }
                    }
                });
                break;
        }
    }

}
