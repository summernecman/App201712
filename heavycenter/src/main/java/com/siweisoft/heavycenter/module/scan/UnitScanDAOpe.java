package com.siweisoft.heavycenter.module.scan;

//by summer on 2018-01-25.

import android.content.Context;
import android.os.Bundle;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.baidu.location.BDLocation;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindResBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.map.MapFrag;
import com.siweisoft.heavycenter.module.main.order.news.NewOrderFrag;
import com.siweisoft.heavycenter.module.main.trans.TransFrag;
import com.siweisoft.heavycenter.module.myce.unit.info.InfoFrag;
import com.siweisoft.heavycenter.module.myce.unit.list.ListFrag;

public class UnitScanDAOpe extends BaseDAOpe {



    public UnitScanDAOpe(Context context) {
        super(context);
    }

    public void logic(final AppFrag appFrag,UnitInfo unit){

        if(appFrag.getClass().getName().equals(TransFrag.class.getName())){
            ToastUtil.getInstance().showShort(getActivity(),"按单位搜索运输单");
            TransFrag transFrag = (TransFrag) appFrag;
            transFrag.getP().getU().setUnit(StringUtil.getStr(unit.getCompanyName()));
            transFrag.getP().getU().autoRefresh();
            return;
        }


        if(appFrag.getClass().getName().equals(NewOrderFrag.class.getName()) &&( LocalValue.get登录返回信息().getUserType()==UserTypeReqBean.非驾驶员)){
            ToastUtil.getInstance().showShort(getActivity(),"新建订单选定单位");
            NewOrderFrag newOrderFrag = (NewOrderFrag) appFrag;
            newOrderFrag.setUnit(unit.getId());
            return;
        }

        if(appFrag.getClass().getName().equals(MapFrag.class.getName())&&( LocalValue.get登录返回信息().getUserType()==UserTypeReqBean.驾驶员)){
            ToastUtil.getInstance().showShort(getActivity(),"驾驶员扫码地图 地图中心改为单位所在位置");
            BDLocation bdLocation = new BDLocation();
            bdLocation.setLatitude(unit.getCompanyLat());
            bdLocation.setLongitude(unit.getCompanyLng());
            MapFrag mapFrag = (MapFrag) appFrag;
            mapFrag.local(bdLocation);
            return;
        }

        if(appFrag.getClass().getName().equals(ListFrag.class.getName())){
            ToastUtil.getInstance().showShort(getActivity(),"从单位列表中 选择一个单位");
            ListFrag listFrag = (ListFrag) appFrag;
            listFrag.selUnit(unit);
            return;
        }


        if(LocalValue.get登录返回信息().getBindCompanyState()!=LoginResBean.BIND_UNIT_STATE_BINDED){
            ToastUtil.getInstance().showShort(getActivity(),"绑定单位，通知所有管理人员");
            BindReqBean bindReqBean = new BindReqBean();
            bindReqBean.setId(LocalValue.get登录返回信息().getUserId());
            bindReqBean.setCompanyId(unit.getId());
            bindReqBean.setIsManager(BindReqBean.IS_MANAGER_NO);
            bindReqBean.setMangerId(0);
            NetDataOpe.User.binUnit(getActivity(), bindReqBean, new UINetAdapter<BindResBean>(getActivity()) {
                @Override
                public void onResult(boolean success, String msg, BindResBean o) {
                    super.onResult(success, msg, o);
                    if(success){
                        ((MainAct)getActivity()).netRestart();
                    }
                }
            });
            return;
        }


        if(LocalValue.get登录返回信息().getBindCompanyState()==LoginResBean.BIND_UNIT_STATE_BINDED){
            ToastUtil.getInstance().showShort(getActivity(),"查看单位信息（建设中）");

            Bundle bundle = new Bundle();
            bundle.putInt(ValueConstant.DATA_DATA,unit.getId());
            FragManager2.getInstance().start(getActivity(),getActivity().getMoudle(),new InfoFrag(),bundle);
            return;
        }
    }
}
