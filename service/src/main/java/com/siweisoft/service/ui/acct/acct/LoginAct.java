package com.siweisoft.service.ui.acct.acct;

//by summer on 17-08-24.

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.service.main.AppService;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.service.R;
import com.siweisoft.service.ui.acct.login.LoginFrag;

public class LoginAct extends BaseUIActivity<BaseUIOpe, LoginDA> {

    public static final int 根布局 = R.id.act_base_root;

    public static final String 登录模块 = "登录模块";

    @Override
    protected void initNow() {
        super.initNow();
        setMoudle(LoginAct.登录模块);
        if(getP().getD().getPermissionUtil().is所有的权限都允许(this,getP().getD().getPermissionsDA().getPermissions())){
            dothing();
            return;
        }
    }

    private void dothing() {
        FragManager2.getInstance().setAnim(false).start(getActivity(),getMoudle(),LoginAct.根布局,new LoginFrag());
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(!getP().getD().getPermissionUtil().onRequestPermissionsResult(this,requestCode,grantResults)){
            return;
        }
        dothing();
    }


    @Override
    public void onBackPressed() {

        BaseUIFrag baseUIFrag = FragManager2.getInstance().getCurrentFrag(getMoudle());
        if(baseUIFrag!=null&&baseUIFrag.getFragM()!=null){
            FragManager2 fragManager2 = baseUIFrag.getFragM();
            if(!fragManager2.finish(getActivity(),getMoudle(),!getMoudle().equals(LoginAct.登录模块))){//清除当前页面
                super.onBackPressed();//当前模块没有可清除界面
            }
        }else{
            super.onBackPressed();
        }
    }

}
