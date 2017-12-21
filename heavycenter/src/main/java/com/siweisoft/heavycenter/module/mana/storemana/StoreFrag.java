package com.siweisoft.heavycenter.module.mana.storemana;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.bean.TitleBean;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppAct;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.storemana.news.NewFrag;

import butterknife.OnClick;

public class StoreFrag extends AppFrag<StoreUIOpe,StoreDAOpe> {
    @Override
    public void initData() {
        super.initData();
    }


    @OnClick({R.id.ftv_right})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)(getActivity())).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(),getIndex(),new NewFrag());
                break;
        }
    }
}
