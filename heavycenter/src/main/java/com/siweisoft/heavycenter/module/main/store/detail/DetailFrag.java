package com.siweisoft.heavycenter.module.main.store.detail;

//by summer on 2017-12-11.

import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.newsf.UIFNetAdapter;
import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.store.check.CheckFrag;

import butterknife.OnClick;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> {

    @Override
    public void initData() {
        super.initData();
        getP().getU().initRecycle();
        getP().getD().detail(getArguments().getInt(ValueConstant.DATA_DATA), new UIFNetAdapter<StoreDetail>(this) {
            @Override
            public void onResult(boolean success, String msg, StoreDetail o) {
                super.onResult(success, msg, o);
              if(success){
                  getP().getU().initRefresh();
                  getP().getU().initUI(o);
              }
                //getP().getU().LoadListData(getP().getD().getData());
            }
        });

    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                FragManager2.getInstance().start(getBaseUIActivity(),getContainerName(),new CheckFrag());
        }
    }
}
