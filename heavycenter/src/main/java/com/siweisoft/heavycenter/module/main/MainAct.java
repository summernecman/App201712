package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;

import cn.jpush.android.api.JPushInterface;

public class MainAct extends AppAct<MainUIOpe, MainDAOpe> implements OnAppItemSelectListener {

    public static final int ID_ALL_ROOT = R.id.content_frame;

    public static final int ID_CONTENT = R.id.content_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().setBottomMenuViewData(getP().getD().getMenudata());
        if(!getP().getD().getPermissionUtil().isAllGranted(activity,getP().getD().getPermissions())){
            return;
        }
        dothing();

    }


    private void dothing(){
        getP().getU().initPages(getP().getD().getMenudata(),this);
        getP().getU().initDrawerMenu(getP().getD().getMyceFrag());
        if(!getP().getD().isRead()){
            getP().getU().nobind();
        }
    }

    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int position) {
        getP().getU().setCurrentItem(position);
        getP().getD().setIndex(position);
        if(getP().getD().isRead()){
            FragManager.getInstance().clearAll(getSupportFragmentManager(),getP().getU().getPos_content());
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

    @Override
    public void onBackPressed() {
        if(FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT)!=null&&FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT).size()>0){
            activity.getSupportFragmentManager().beginTransaction().remove(
                    FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT).get(
                            FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT).size()-1))
                    .commit();
            FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT).remove(FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT).size()-1);
        }else
        if(getP().getD().getIndex()==getP().getU().getPos_content()
                &&FragManager.getInstance().getFragMaps().get(getP().getU().getPos_content())!=null
                &&FragManager.getInstance().getFragMaps().get(getP().getU().getPos_content()).size()>0){
            FragManager.getInstance().finish(activity.getSupportFragmentManager(),getP().getD().getIndex());
        }else
        if(FragManager.getInstance().getFragMaps().get(getP().getD().getIndex())!=null&& FragManager.getInstance().getFragMaps().get(getP().getD().getIndex()).size()>1){
            FragManager.getInstance().finish(activity.getSupportFragmentManager(),getP().getD().getIndex());
        }else{
            finish();
        }

//        FragManager.getInstance().clearTopWith(activity.getSupportFragmentManager(), getP().getD().getIndex(), new OnFinishListener() {
//            @Override
//            public void onFinish(Object o) {
//                MainAct.super.onBackPressed();
//            }
//        });
    }
}
