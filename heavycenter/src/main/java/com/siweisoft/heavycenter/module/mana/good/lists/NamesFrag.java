package com.siweisoft.heavycenter.module.mana.good.lists;

//by summer on 2018-01-17.

import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.good.names.NamesReq;
import com.siweisoft.heavycenter.data.netd.mana.good.names.NamesRes;

public class NamesFrag extends AppFrag<NamesUIOpe,NamesDAOpe> implements ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getD().NamesGood(new UINetAdapter<NamesRes>(activity) {
            @Override
            public void onResult(boolean success, String msg, NamesRes o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o, NamesFrag.this);
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.type:
                NamesRes.ResultsBean data = (NamesRes.ResultsBean) v.getTag(R.id.data);
                getArguments().putSerializable(ValueConstant.DATA_DATA2,data);
                getBaseUIActivity().onBackPressed();
                break;
        }
    }
}
