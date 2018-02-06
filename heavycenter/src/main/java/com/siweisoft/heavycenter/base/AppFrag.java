package com.siweisoft.heavycenter.base;

//by summer on 2017-12-08.

import android.content.Intent;
import android.view.View;

import butterknife.OnClick;
import butterknife.Optional;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

public abstract class AppFrag<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseUIFrag<A,B> {



    @Override
    public void initdelay() {
        super.initdelay();

    }

    @Optional
    @OnClick({R.id.ftv_back,R.id.ftv_right})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_back:
               String str = getArguments().getString(ValueConstant.容器);
               if(str!=null){
                   if(!FragManager2.getInstance().finish(getBaseUIAct(),str,!str.equals(MainValue.主界面))){
                       getBaseUIAct().onBackPressed();
                   }
               }
                break;
            case R.id.ftv_right:
                if(getActivity() instanceof MainAct){
                    MainAct mainAct = (MainAct) getActivity();
                    Intent intent = new Intent(mainAct, CaptureActivity.class);
                    getBaseUIAct().startActivityForResult(intent, ValueConstant.CODE_REQUSET);
                }
                break;

        }
    }

}
