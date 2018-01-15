package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;

public class MainAct extends AppAct<MainUIOpe, MainDAOpe> implements OnAppItemSelectListener {

    public static final int ID_ALL_ROOT = R.id.act_main;

    public static final int ID_CONTENT = R.id.content_content;

    public static final String 地磅 = "地磅";

    public static final String 运输单 = "运输单";

    public static final String 订单 = "订单";

    public static final String 仓库 = "仓库";

    public static final String 消息 = "消息";

    public static final String 个人中心 = "个人中心";

    public static final String 主界面 = "主界面";

    public static final String 对话框 = "对话框";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getP().getD().testData();
        getP().getU().setBottomMenuViewData(getP().getD().getMenudata());
        if(!getP().getD().getPermissionUtil().isAllGranted(activity,getP().getD().getPermissions())){
            return;
        }
        dothing();

    }


    public void dothing(){
        FragManager.getInstance().clear();
        getP().getU().initDrawerMenu(getP().getD().getMyceFrag());
        getP().getU().initPages(getP().getD().getMenudata(),this);
        ddd();
    }

    public void ddd(){
        if(!getP().getD().isBindUnit()){
            getP().getU().nobind();
        }else{
            getP().getU().removenobind();
        }
    }


    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int position) {
        FragManager2.getInstance().clear((BaseUIActivity) activity,MainAct.主界面);
        getP().getU().setCurrentItem(position);
        getP().getD().setIndex(getP().getD().getMenudata().get(position).getContainerView().getId());
        setMoudle(getP().getD().getMenudata().get(position).getName());
        if(getP().getD().isBindUnit()){
            //FragManager.getInstance().clearAll(getSupportFragmentManager(),getP().getU().getPos_content());
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

//    @Override
//    public void onBackPressed() {

//        if(FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT)!=null&&FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT).size()>0){
//            activity.getSupportFragmentManager().beginTransaction().remove(
//                    FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT).get(
//                            FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT).size()-1))
//                    .commit();
//            FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT).remove(FragManager.getInstance().getFragMaps().get(ID_ALL_ROOT).size()-1);
//        }else
//        if(getP().getD().getIndex()==getP().getU().getPos_content()
//                &&FragManager.getInstance().getFragMaps().get(getP().getU().getPos_content())!=null
//                &&FragManager.getInstance().getFragMaps().get(getP().getU().getPos_content()).size()>0){
//            FragManager.getInstance().finish(activity.getSupportFragmentManager(),getP().getD().getIndex());
//        }else
//        if(FragManager.getInstance().getFragMaps().get(getP().getD().getIndex())!=null&& FragManager.getInstance().getFragMaps().get(getP().getD().getIndex()).size()>1){
//            FragManager.getInstance().finish(activity.getSupportFragmentManager(),getP().getD().getIndex());
//        }else{
//            finish();
//        }

//        FragManager.getInstance().clearTopWith(activity.getSupportFragmentManager(), getP().getD().getIndex(), new OnFinishListener() {
//            @Override
//            public void onFinish(Object o) {
//                MainAct.super.onBackPressed();
//            }
//        });


//    }


    @Override
    public void onBackPressed() {
        if(!FragManager2.getInstance().finish((BaseUIActivity) activity,getMoudle())){
            super.onBackPressed();
        }
    }
}
