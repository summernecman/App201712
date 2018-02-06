package com.siweisoft.heavycenter.module.main.store.detail;

//by summer on 2017-12-11.

import android.os.Bundle;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.network.newsf.UIFNetAdapter;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.Test;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;
import com.siweisoft.heavycenter.module.main.store.check.CheckFrag;

import butterknife.OnClick;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> {

    @Override
    public void initdelay() {
        super.initdelay();
        getP().getU().initRecycle();
        getP().getD().detail(getArguments().getInt(ValueConstant.DATA_DATA), new UINetAdapter<StoreDetail>(this) {
            @Override
            public void onSuccess(StoreDetail o) {
                //o = new Test().getStoreDetail();
                getP().getU().initUI(o);
            }
        });

    }

    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.FARG_REQ,1);
                FragManager2.getInstance().start(getBaseUIActivity(),getContainerName(),new CheckFrag(),bundle);
        }
    }

    @Override
    public void onRestart(int res, Bundle bundle) {
        super.onRestart(res, bundle);
        switch (res){
            case 1:
                if(bundle!=null&&bundle.getBoolean(ValueConstant.FRAG_KEY,false)){
                    getArguments().putBoolean(ValueConstant.FRAG_KEY,true);
                }
                break;
        }
    }
}
