package com.siweisoft.heavycenter.module.main.order.detail;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.newsf.UIFNetAdapter;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.Test;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;
import com.siweisoft.heavycenter.data.netd.order.addcar.AddCarReq;
import com.siweisoft.heavycenter.data.netd.order.addcar.AddCarRes;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;
import com.siweisoft.heavycenter.module.mana.car.my.MyFrag;

import butterknife.OnClick;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> implements ViewListener{

    @Override
    public void initdelay() {
        super.initdelay();
        getP().getU().initUI(getArguments().getString(ValueConstant.TYPE));
        getP().getU().initRecycle();
        getP().getD().detail(getArguments().getInt(ValueConstant.DATA_DATA), new UIFNetAdapter<OrdersRes.ResultsBean>(this) {
            @Override
            public void onResult(boolean success, String msg, OrdersRes.ResultsBean o) {
                super.onResult(success, msg, o);
                o = new Test().getOrdersRes().getResults().get(0);
                if(success){
                    getP().getD().setData(o);
                    getP().getU().initUI(getArguments().getString(ValueConstant.TYPE),getP().getD().getData());
                    getP().getU().initdata(getP().getD().getData().getVehicleList(),DetailFrag.this);
                }
            }
        });
    }

    @OnClick({R.id.iv_local})
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_local:
                Bundle bundle = new Bundle();
                bundle.putInt(ValueConstant.FARG_REQ,MyFrag.TYPE_SEL);
                FragManager2.getInstance().start(getBaseUIAct(), MainValue.订单,new MyFrag(),bundle);
                break;
        }
    }

    @Override
    public void onResult(int res, Bundle bundle) {
        super.onResult(res, bundle);
        switch (res){
            case MyFrag.TYPE_SEL:
                if(bundle==null|| bundle.getSerializable(ValueConstant.DATA_DATA)==null){
                    return;
                }
                final CarsResBean.CarInfoRes bean = (CarsResBean.CarInfoRes) bundle.getSerializable(ValueConstant.DATA_DATA);
                getP().getD().addCar(bean.getVehicleId(), AddCarReq.添加,new NetAdapter<AddCarRes>(getContext()){
                    @Override
                    public void onResult(boolean success, String msg, AddCarRes o) {
                        super.onResult(success, msg, o);
                        if(success){
                            getP().getD().getData().getVehicleList().add(bean);
                            getP().getU().initdata(getP().getD().getData().getVehicleList(),DetailFrag.this);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public void onInterupt(int type, View v) {
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()){
                    case R.id.menu:
                        final CarsResBean.CarInfoRes bean = (CarsResBean.CarInfoRes) v.getTag(R.id.data);
                        getP().getD().addCar(bean.getVehicleId(), AddCarReq.移除,new NetAdapter<AddCarRes>(getContext()){
                            @Override
                            public void onResult(boolean success, String msg, AddCarRes o) {
                                super.onResult(success, msg, o);
                                if(success){
                                    getP().getD().getData().getVehicleList().remove(bean);
                                    getP().getU().initdata(getP().getD().getData().getVehicleList(),DetailFrag.this);
                                }
                            }
                        });
                        break;
                }
                break;
        }
    }
}
