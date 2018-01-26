package com.siweisoft.heavycenter.module.myce.unit.list;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.android.lib.util.NullUtil;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.unit.info.UnitInfoReqBean;
import com.siweisoft.heavycenter.data.netd.unit.list.ListReqBean;
import com.siweisoft.heavycenter.data.netd.unit.list.ListResBean;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchReqBean;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchResBean;
import com.siweisoft.heavycenter.data.netd.user.info.UserInfoReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindResBean;

public class ListDAOpe extends AppDAOpe {


    public static final int BIND_UNIT = 0;

    public static final int UP_UNIT = 1;

    public static final int SEL_UNIT = 2;

    ListResBean netUnits = new ListResBean();


    ListResBean selUnits = new ListResBean();

    public ListDAOpe(Context context) {
        super(context);
    }

    public void getData( NetI<ListResBean> adapter){
        ListReqBean listReqBean = new ListReqBean();
        listReqBean.setIsAPP(1);
        NetDataOpe.unitList(getActivity(), listReqBean,adapter);
    }

    public void searchUnit(SearchReqBean reqBean,NetI<SearchResBean> adapter){
        reqBean.setPageSize(100);
        reqBean.setPageIndex(0);
        NetDataOpe.Unit.search(getActivity(),reqBean,adapter);
    }

    public void bindUnit(int companyId, boolean ismanager,NetI<BindResBean> adapter){
        BindReqBean bindReqBean = new BindReqBean();
        bindReqBean.setId(LocalValue.get登录返回信息().getUserId());
        bindReqBean.setCompanyId(companyId);
        bindReqBean.setBindOperateType(BindReqBean.BIND_OPERATE_TYPE_SEARCH);
        bindReqBean.setIsManager(ismanager?BindReqBean.IS_MANAGER_YES:BindReqBean.IS_MANAGER_NO);
        if(ismanager){
            bindReqBean.setMangerId(LocalValue.get登录返回信息().getUserId());
        }
        NetDataOpe.User.binUnit(getActivity(),bindReqBean,adapter);
    }

    public void getInfo(NetI<LoginResBean> adapter){
        UserInfoReqBean userInfoReqBean = new UserInfoReqBean();
        userInfoReqBean.setIsApp(1);
        userInfoReqBean.setId(LocalValue.get登录返回信息().getUserId());
        NetDataOpe.User.get用户信息(getActivity(), userInfoReqBean,adapter);
    }


    public void getUnitInfo(int id,NetI<UnitInfo> adapter){
        UnitInfoReqBean unitInfoReqBean = new UnitInfoReqBean();
        unitInfoReqBean.setId(id);
        NetDataOpe.Unit.getInfo(getActivity(), unitInfoReqBean,adapter);
    }

    public ListResBean getNetUnits() {
        return netUnits;
    }

    public void setNetUnits(ListResBean netUnits) {
        this.netUnits = netUnits;
    }

    public ListResBean getSelUnits(String key) {
        if(NullUtil.isStrEmpty(key)){
            return netUnits;
        }
        selUnits.getResults().clear();
        for(int i=0;i<getNetUnits().getResults().size();i++){
            if((getNetUnits().getResults().get(i).getAbbreviationName()+getNetUnits().getResults().get(i).getCompanyAddress()+""+
                    getNetUnits().getResults().get(i).getCompanyName()+""+getNetUnits().getResults().get(i).getContactName()
                    +getNetUnits().getResults().get(i).getContactPhone()).contains(key)){
                selUnits.getResults().add(getNetUnits().getResults().get(i));
            }
        }
        return selUnits;
    }

    public void setSelUnits(ListResBean selUnits) {
        this.selUnits = selUnits;
    }
}
