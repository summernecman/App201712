package com.siweisoft.heavycenter.module.main.msg;

//by summer on 2017-12-11.

import android.content.Intent;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

public class MsgFrag extends AppFrag<MsgUIOpe,MsgDAOpe> {


    @Override
    public void initData() {
        super.initData();

    }

    @Override
    public void lazyInit() {
        getP().getU().initPages(fragment,getP().getD().getPages());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)activity).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right:
                if(getActivity() instanceof MainAct){
                    MainAct mainAct = (MainAct) getActivity();
                    Intent intent = new Intent(mainAct, CaptureActivity.class);
                    activity.startActivityForResult(intent, ValueConstant.CODE_REQUSET);
                }
                break;
        }
    }

}
