package com.siweisoft.heavycenter.module.mana.carmana;

//by summer on 2017-12-14.

import android.view.View;

import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;

import butterknife.OnClick;

public class CarManaAct extends AppAct<CarManaUIOpe,CarManaDAOpe> {

    @OnClick({R.id.ftv_back})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.login:
                break;
                case R.id.ftv_back:
                    finish();
                break;
        }
    }
}
