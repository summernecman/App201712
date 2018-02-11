package com.siweisoft.heavycenter.module.mana.user.news;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.user.add.AddUserResBean;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {


    @OnClick({R.id.enter})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.enter:
                if(getP().getU().canGo()){
                    getP().getD().addUser(getP().getU().getUser(getP().getD().getReqBean()), new UINetAdapter<AddUserResBean>(this) {
                        @Override
                        public void onSuccess(AddUserResBean o) {
                            getArguments().putBoolean(ValueConstant.FARG_TYPE,true);
                            getBaseUIAct().onBackPressed();
                        }
                    });
                }
                break;
        }
    }
}
