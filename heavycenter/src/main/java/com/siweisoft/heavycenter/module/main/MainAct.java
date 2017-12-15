package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.IntentUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.module.myce.MyceAct;

import butterknife.OnClick;

public class MainAct extends AppAct<MainUIOpe, MainDAOpe> implements OnAppItemSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getD().post();
        getP().getU().setBottomMenuViewData(getP().getD().getMenudata());
        if(!getP().getD().getPermissionUtil().isAllGranted(activity,getP().getD().getPermissions())){
            return;
        }
        dothing();
    }


    private void dothing(){
        getP().getU().initPages(activity,getP().getD().getPages(),this);
    }

    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int position) {
        getP().getU().setCurrentItem(getP().getD().getPages(),position);
    }

    @OnClick({R.id.menu})
    public void onClick(View v){
        switch (v.getId()){
            case R.id.menu:
                IntentUtil.startActivity(activity, MyceAct.class,null);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(!getP().getD().getPermissionUtil().onRequestPermissionsResult(activity,requestCode,grantResults)){
            return;
        }
        dothing();
    }
}
