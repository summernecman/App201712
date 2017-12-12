package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.interf.view.OnAppItemSelectListener;
import com.android.lib.util.FragmentUtil2;
import com.jaeger.library.StatusBarUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;

public class MainAct extends AppAct<MainUIOpe, MainDAOpe> implements OnAppItemSelectListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setColorForDrawerLayout(this,getP().getU().bind.drawerLayout, getResources().getColor(R.color.red));
        getP().getU().setBottomMenuViewData(getP().getD().getBottomMenuViewData());
        getP().getD().post();
        getP().getD().initPages();
        getP().getU().initPages(activity,getP().getD().getPages());
        getP().getU().bind.bottommenu.setOnAppItemClickListener(this);
    }

    @Override
    public void onAppItemSelect(ViewGroup viewGroup, View view, int position) {
        FragmentUtil2.getInstance().showAndHidden(activity,getP().getD().getPages(),position);
    }
}
