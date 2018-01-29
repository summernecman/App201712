package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.base.AppFrag;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

public class MainAct extends AppAct<MainUIOpe, MainDAOpe> implements OnAppItemSelectListener {

    public static final String 地磅 = "地磅";

    public static final String 运输单 = "运输单";

    public static final String 订单 = "订单";

    public static final String 地图 = "地图";

    public static final String 仓库 = "仓库";

    public static final String 消息 = "消息";

    public static final String 个人中心 = "个人中心";

    public static final String 主界面 = "主界面";

    public static final String 对话框 = "对话框";


    public static final int 地磅ID = 11111;

    public static final int 运输单ID = 11112;

    public static final int 订单ID = 11113;

    public static final int 仓库ID = 11114;

    public static final int 消息ID = 11115;

    public static final int 地图ID = 11116;

    public static final int 主界面ID = R.id.content_content;

    public static final int 对话框ID = R.id.act_main;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getP().getD().testData();
        if(!getP().getD().getPermissionUtil().isAllGranted(activity,getP().getD().getPermissions())){
            return;
        }
        dothing();
    }




    public void dothing(){
        getP().getU().setBottomMenuViewData(getP().getD().getMenudata());
        getP().getU().initDrawerMenu(getP().getD().getMyceFrag());
        getP().getU().initPages(getP().getD().getMenudata(),this);
        reStart();
    }

    public void reStart(){
        if(!getP().getD().isBindUnit()){
            getP().getU().nobind();
        }else{
            getP().getU().removenobind();
        }
        getP().getD().getMyceFrag().init();
    }


    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int position) {
        FragManager2.getInstance().clear((BaseUIActivity) activity,MainAct.主界面);
        getP().getU().setCurrentItem(position);
        setMoudle(getP().getD().getMenudata().get(position).getName());
        if(!getP().getD().getMenudata().get(position).getFragment().isInit()){
            getP().getD().getMenudata().get(position).getFragment().lazyInit();
            getP().getD().getMenudata().get(position).getFragment().setInited();
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
        if(!FragManager2.getInstance().finish((BaseUIActivity) activity,getMoudle(),!getMoudle().equals(MainAct.主界面))){
            super.onBackPressed();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ValueConstant.CODE_REQUSET) {
            if (null != data && data.getExtras()!=null) {
                Bundle bundle = data.getExtras();
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    ToastUtil.getInstance().showShort(activity,FragManager2.getInstance().getCurrentFrag(getMoudle()).getClass().getSimpleName());
                    getP().getD().getScanDAOpe().logic((AppFrag) FragManager2.getInstance().getCurrentFrag(getMoudle()),bundle.getString(CodeUtils.RESULT_STRING));
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.getInstance().showShort(this,"解析二维码失败");
                }
            }
        }
    }
}
