package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
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
        getP().getU().setBottomMenuViewData(getP().getD().getMenudata());
        if(!getP().getD().getPermissionUtil().isAllGranted(activity,getP().getD().getPermissions())){
            return;
        }
        dothing();
    }




    public void dothing(){
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
        getP().getD().setIndex(getP().getD().getMenudata().get(position).getContainerView().getId());
        setMoudle(getP().getD().getMenudata().get(position).getName());
        if(getP().getD().isBindUnit()){
            //FragManager.getInstance().clearAll(getSupportFragmentManager(),getP().getU().getPos_content());
        }
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
        switch (getMoudle()){
            case MainAct.主界面:
                if(!FragManager2.getInstance().finish((BaseUIActivity) activity,getMoudle(),false)){
                    super.onBackPressed();
                }
                break;
                default:
                    if(!FragManager2.getInstance().finish((BaseUIActivity) activity,getMoudle(),true)){
                        super.onBackPressed();
                    }
                    break;
        }
    }

    AppFrag appFrag;

    public void dealScan(AppFrag appFrag){
        this.appFrag = appFrag;
        ToastUtil.getInstance().showShort(activity,appFrag.getClass().getSimpleName());
        Intent intent = new Intent(this, CaptureActivity.class);
        startActivityForResult(intent, 1);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    getP().getD().getScanDAOpe().logic(this.appFrag,result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.getInstance().showShort(this,"解析二维码失败");
                }
            }
        }
    }
}
