package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.siweisoft.heavycenter.base.AppAct;

public class MainAct extends AppAct<MainUIOpe, MainDAOpe> implements OnAppItemSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getD().post();
        getP().getU().setBottomMenuViewData(getP().getD().getMenudata(),this);
        getP().getU().initPages(activity,getP().getD().getPages());
    }

    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int position) {
        getP().getU().setCurrentItem(getP().getD().getPages(),position);
    }
}
