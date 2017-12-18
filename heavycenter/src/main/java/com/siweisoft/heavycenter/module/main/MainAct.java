package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import com.android.lib.base.adapter.AppsDataBindingAdapter;
import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.menu.popup.PopupUtil;
import com.android.lib.view.ItemDecoration.MyItemDecoration;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.module.myce.MyceAct;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class MainAct extends AppAct<MainUIOpe, MainDAOpe> implements OnAppItemSelectListener {

    public static final int ID_ROOT = R.id.content_frame;

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
                //IntentUtil.startActivity(activity, MyceAct.class,null);
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

    public void soo(){

    }
}
