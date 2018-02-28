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
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.activity.ActivityUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.view.bottommenu.MessageEvent;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.module.welc.welc.WelcAct;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainAct extends AppAct<MainUIOpe, MainDAOpe> implements OnAppItemSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityUtil.被后台清理了就重启(getActivity(),savedInstanceState,WelcAct.class);
        if(!getP().getD().getPermissionUtil().is所有的权限都允许(getActivity(),getP().getD().getPermissions())){
            return;
        }
        初始化界面();
    }


    public void 初始化界面(){
        getP().getU().setBottomMenuViewData(getP().getD().initBottomdata());
        getP().getU().initDrawerMenu(getP().getD().getMyceFrag());
        getP().getU().initPages(getP().getD().getBottomdata(),this);
        if(getP().getD().getMyceFrag().getP().getU()!=null){
            getP().getD().getMyceFrag().init();
        }
    }

    public void go判断是否绑定单位处理(){
        getP().getU().initPages(getP().getD().initBottomdata(),this);
        if(getP().getD().getMyceFrag().getP().getU()!=null){
            getP().getD().getMyceFrag().init();
        }
    }

    public void go网络获取用户信息重新加载(){
        getP().getD().get用户信息(new UINetAdapter<LoginResBean>(this) {
            @Override
            public void onSuccess(LoginResBean o) {
                super.onSuccess(o);
                LocalValue.save登录返回信息(o);
                go判断是否绑定单位处理();
            }
        });
    }


    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int position) {
        FragManager2.getInstance().clear((BaseUIActivity) getActivity(),MainValue.主界面);
        getP().getU().setCurrentItem(position);
        setMoudle(getP().getD().getBottomdata().get(position).getName());
        getP().getD().getBottomdata().get(position).getFragment().onFristVisible();
//        if(position==getP().getD().getMenudata().size()-1){
//            if(!getP().getD().is绑定了单位()){
//                getP().getU().hideshowunbind(false);
//            }
//        }else{
//            if(!getP().getD().is绑定了单位()){
//                getP().getU().hideshowunbind(true);
//            }
//        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(!getP().getD().getPermissionUtil().onRequestPermissionsResult(getActivity(),requestCode,grantResults)){
            return;
        }
        初始化界面();
    }



    @Override
    public void onBackPressed() {

        if(!FragManager2.getInstance().finish(getActivity(),getMoudle(),!getMoudle().equals(MainValue.主界面))){
            super.onBackPressed();
        }
        if(FragManager2.getInstance().getMoudleFragSize(MainValue.主界面)==0){
            onAppItemSelect(null,null,getP().getU().bind.bottommenu.getIndex());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        if (null != data && data.getExtras()!=null) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if(result != null&&result.getContents() != null) {
                ToastUtil.getInstance().showShort(getActivity(),FragManager2.getInstance().getCurrentFrag(getMoudle()).getClass().getSimpleName());
                getP().getD().getScanDAOpe().logic((AppFrag) FragManager2.getInstance().getCurrentFrag(getMoudle()),result.getContents());
            } else {
                ToastUtil.getInstance().showShort(this,"解析二维码失败");
            }
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(MessageEvent m) {
        if("net".equals(m.sender)){
            LocalValue.set自动登录(false);
            startActivity(new Intent(this,WelcAct.class));
            this.finish();
        }
    }

    @Override
    protected boolean registerEventBus() {
        return true;
    }
}
