package com.siweisoft.heavycenter.module.mana.good;

//by summer on 2017-12-14.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.good.list.GoodListRes;
import com.siweisoft.heavycenter.module.mana.good.news.NewFrag;

import butterknife.OnClick;

public class GoodFrag extends AppFrag<GoodUIOpe,GoodDAOpe> implements ViewListener,OnRefreshListener{
    @Override
    public void initNow() {
        super.initNow();
        getP().getU().initRefresh(this);
        getP().getU().initRecycle();
        onRefresh(null);
    }


    @OnClick({R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_right2:
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.FARG_REQ,1);
                FragManager2.getInstance().start(getBaseUIActivity(),getContainerName(),new NewFrag(),bundle);
                break;
        }
    }


    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                GoodListRes.ResultsBean resultsBean = (GoodListRes.ResultsBean) v.getTag(R.id.data);
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.FARG_REQ,1);
                bundle.putInt(ValueConstant.DATA_DATA,resultsBean.getProductInfoId());
                FragManager2.getInstance().start(getBaseUIActivity(),getContainerName(),new NewFrag(),bundle);
                break;
        }
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().listGood(new UINetAdapter<GoodListRes>(activity) {
            @Override
            public void onResult(boolean success, String msg, GoodListRes o) {
                //o= new Test().getGoodListRes();
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o,GoodFrag.this);
                getP().getU().finishRefresh();
            }
        });
    }

    @Override
    public void onRestart(int res, Bundle bundle) {
        super.onRestart(res, bundle);
        switch (res){
            case 1:
                if(bundle==null || !bundle.getBoolean(ValueConstant.FARG_TYPE,false)){
                    return;
                }
                if(bundle.getBoolean(ValueConstant.FARG_TYPE,false)){
                    getP().getU().autoRefresh(600);
                }
                break;
        }
    }
}
