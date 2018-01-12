package com.siweisoft.heavycenter.base;

//by summer on 2017-12-08.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.module.main.MainAct;

public abstract class AppFrag<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseUIFrag<A,B> {

    @Override
    public void doThing() {
        super.doThing();
        if(getView().findViewById(R.id.ftv_back)!=null){
            getView().findViewById(R.id.ftv_back).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_back:
                getActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void OnBackPress() {
        if(getActivity() instanceof  MainAct){
            MainAct mainAct = (MainAct) getActivity();
            if(getIndex()==mainAct.getP().getD().getIndex()){
                FragManager2.getInstance().finish(activity,getIndex(),null);
            }
        }
    }
}
