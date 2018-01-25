package com.siweisoft.heavycenter.module.scan;

//by summer on 2018-01-25.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindResBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.map.MapFrag;
import com.siweisoft.heavycenter.module.main.order.news.NewOrderFrag;
import com.siweisoft.heavycenter.module.main.trans.TransFrag;
import com.siweisoft.heavycenter.module.mana.car.detail.DetailFrag;
import com.siweisoft.heavycenter.module.myce.unit.list.ListFrag;

public class UserScanDAOpe extends BaseDAOpe {



    public UserScanDAOpe(Context context) {
        super(context);
    }

    public void logic(final AppFrag appFrag,LoginResBean scaned){

        if(appFrag.getClass().getName().equals(TransFrag.class.getName())){
            ToastUtil.getInstance().showShort(getActivity(),"按单位搜索运输单");
            TransFrag transFrag = (TransFrag) appFrag;
            transFrag.getP().getU().setUnit(StringUtil.getStr(scaned.getCompanyName()));
            return;
        }


        if(appFrag.getClass().getName().equals(NewOrderFrag.class.getName()) &&( LocalValue.getLoginInfo().getUserType()==UserTypeReqBean.非驾驶员)){
            ToastUtil.getInstance().showShort(getActivity(),"新建订单选定单位");
            NewOrderFrag newOrderFrag = (NewOrderFrag) appFrag;
            return;
        }

        if(appFrag.getClass().getName().equals(MapFrag.class.getName())&&( LocalValue.getLoginInfo().getUserType()==UserTypeReqBean.驾驶员)){
            ToastUtil.getInstance().showShort(getActivity(),"驾驶员扫码地图 地图中心改为单位所在位置");
            MapFrag mapFrag = (MapFrag) appFrag;
            return;
        }

        if(appFrag.getClass().getName().equals(ListFrag.class.getName())){
            ToastUtil.getInstance().showShort(getActivity(),"从单位列表中 选择一个单位");
            ListFrag listFrag = (ListFrag) appFrag;
            return;
        }



        if(appFrag.getClass().getName().equals(DetailFrag.class.getName())&&( LocalValue.getLoginInfo().getUserType()==UserTypeReqBean.非驾驶员)
                &&( scaned.getUserType()==UserTypeReqBean.驾驶员)){
            ToastUtil.getInstance().showShort(getActivity(),"作为选定的当前驾驶员");
            DetailFrag detailFrag = (DetailFrag) appFrag;
            return;
        }


        if((scaned.getBindCompanyState()!=LoginResBean.BIND_UNIT_STATE_BINDED)&&(
                (LocalValue.getLoginInfo().getUserRole()==LoginResBean.USER_ROLE_ADMIN)
                        ||(LocalValue.getLoginInfo().getUserRole()==LoginResBean.USER_ROLE_SUPER_ADMIN))){
            ToastUtil.getInstance().showShort(getActivity(),"管理员发送邀请");
            return;
        }

        if(LocalValue.getLoginInfo().getBindCompanyState()!=LoginResBean.BIND_UNIT_STATE_BINDED){
            ToastUtil.getInstance().showShort(getActivity(),"绑定单位，通知所有管理人员");

        }

        if((LocalValue.getLoginInfo().getBindCompanyState()!=LoginResBean.BIND_UNIT_STATE_BINDED)&&(
                (scaned.getUserRole()==LoginResBean.USER_ROLE_ADMIN)
                        ||(scaned.getUserRole()==LoginResBean.USER_ROLE_SUPER_ADMIN))){
            ToastUtil.getInstance().showShort(getActivity(),"绑定单位，通知被扫用户");
            BindReqBean bindReqBean = new BindReqBean();
            bindReqBean.setId(LocalValue.getLoginInfo().getUserId());
            bindReqBean.setCompanyId(scaned.getCompanyId());
            bindReqBean.setIsManager(BindReqBean.IS_MANAGER_NO);
            bindReqBean.setMangerId(0);
            NetDataOpe.User.binUnit(getActivity(), bindReqBean, new UINetAdapter<BindResBean>(getActivity()) {
                @Override
                public void onResult(boolean success, String msg, BindResBean o) {
                    super.onResult(success, msg, o);
                    if(success){
                        ((MainAct)getActivity()).reStart();
                    }
                }
            });
            return;
        }

        if((LocalValue.getLoginInfo().getBindCompanyState()!=LoginResBean.BIND_UNIT_STATE_BINDED)&&(scaned.getUserRole()==LoginResBean.USER_ROLE_GENERAL)){
            ToastUtil.getInstance().showShort(getActivity(),"绑定单位，通知所有管理人员");
            BindReqBean bindReqBean = new BindReqBean();
            bindReqBean.setId(LocalValue.getLoginInfo().getUserId());
            bindReqBean.setCompanyId(scaned.getCompanyId());
            bindReqBean.setIsManager(BindReqBean.IS_MANAGER_NO);
            bindReqBean.setMangerId(0);
            NetDataOpe.User.binUnit(getActivity(), bindReqBean, new UINetAdapter<BindResBean>(getActivity()) {
                @Override
                public void onResult(boolean success, String msg, BindResBean o) {
                    super.onResult(success, msg, o);
                    if(success){
                        ((MainAct)getActivity()).reStart();
                    }
                }
            });
            return;
        }


        ToastUtil.getInstance().showShort(getActivity(),"查看用户详情");
    }
}
