package com.siweisoft.heavycenter.module.mana.car.my;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.network.newsf.UIFNetAdapter;
import com.android.lib.util.fragment.two.FragManager2;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.status.StopCarResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;
import com.siweisoft.heavycenter.module.mana.car.detail.DetailFrag;

public class MyFrag extends AppFrag<MyUIOpe,MyDAOpe> implements ViewListener,OnRefreshListener,OnLoadmoreListener{

    public static final int TYPE_SEL  = 1;

    @Override
    public void initNow() {
        super.initNow();
        if(getArguments().getInt(ValueConstant.FARG_REQ,-1)==TYPE_SEL){
            getP().getU().bind.cartitle.setVisibility(View.VISIBLE);
        }else{
            getP().getU().bind.cartitle.setVisibility(View.GONE);
        }
        getP().getU().initRefresh(this,this);
    }

    @Override
    public void initdelay() {
        super.initdelay();
        onRefresh(null);
    }



    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()){
                    case R.id.menu:
                        final CarsResBean.CarInfoRes bean = (CarsResBean.CarInfoRes) v.getTag(R.id.data);
                        getP().getD().statusCar(bean.getVehicleId(), bean.getStatus(), new UIFNetAdapter<StopCarResBean>(this) {
                            @Override
                            public void onResult(boolean success, String msg, StopCarResBean o) {
                                super.onResult(success, msg, o);
                                if(success){
                                    bean.setStatus(bean.getStatus()== CarsResBean.CarInfoRes.STATUS_ON? CarsResBean.CarInfoRes.STATUS_OFF: CarsResBean.CarInfoRes.STATUS_ON);
                                    getP().getU().notifyDataSetChanged();
                                }
                            }
                        });
                        break;
                        default:
                            final CarsResBean.CarInfoRes bean1 = (CarsResBean.CarInfoRes) v.getTag(R.id.data);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable(ValueConstant.DATA_DATA,bean1);
                            bundle.putInt(ValueConstant.FARG_REQ,1);
                            if(getArguments().getInt(ValueConstant.FARG_REQ,-1)==MyFrag.TYPE_SEL){
                                getArguments().putAll(bundle);
                                getBaseUIAct().onBackPressed();
                            }else{
                                FragManager2.getInstance().start(getBaseUIAct(), MainValue.主界面,MainValue.主界面ID,new DetailFrag(),bundle);
                            }
                            break;
                }
                break;
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getU().finishLoadmore();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().Cars(getArguments().getString(ValueConstant.DATA_POSITION),new UINetAdapter<CarsResBean>(getActivity()) {
            @Override
            public void onResult(boolean success, String msg, CarsResBean o) {
                super.onResult(success, msg, o);
                getP().getU().LoadListData(o,getArguments().getString(ValueConstant.DATA_POSITION),MyFrag.this);
                getP().getU().finishRefresh();
            }
        });
    }

    @Override
    public void onResult(int res, Bundle bundle) {
        super.onResult(res, bundle);
        switch (res){
            case 1:
                if(bundle==null|| !bundle.getBoolean(ValueConstant.FARG_TYPE,false)){
                    return;
                }
                onRefresh(null);
                break;
        }
    }
}
