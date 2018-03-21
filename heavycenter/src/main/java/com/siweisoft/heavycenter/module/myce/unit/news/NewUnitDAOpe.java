package com.siweisoft.heavycenter.module.myce.unit.news;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.unit.info.UnitInfoReqBean;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.data.netd.unit.news.NewReqBean;
import com.siweisoft.heavycenter.data.netd.unit.news.NewResBean;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchReqBean;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchResBean;
import com.siweisoft.heavycenter.data.netd.unit.update.UpdateUnitReq;
import com.siweisoft.heavycenter.data.netd.unit.update.UpdateUnitRes;
import com.siweisoft.heavycenter.data.netd.user.info.UserInfoReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.unbind.UnBindResBean;

public class NewUnitDAOpe extends AppDAOpe {

   UnitInfo unit = new UnitInfo();

    private UpdateUnitReq updateUnitReq = new UpdateUnitReq();


    public UnitInfo getUnit() {
        return unit;
    }

    public void setUnit(UnitInfo unit) {
        this.unit = unit;
    }

    public void createUnit(Context context,NewReqBean reqBean, NetI<NewResBean> adapter){
        NetDataOpe.Unit.createUnit(context,reqBean,adapter);
    }

    public void getInfo(Context context,int id,NetI<UnitInfo> adapter){
        UnitInfoReqBean unitInfoReqBean = new UnitInfoReqBean();
        unitInfoReqBean.setId(id==-1? LocalValue.get登录返回信息().getCompanyId():id);
        NetDataOpe.Unit.getInfo(context, unitInfoReqBean,adapter);
    }

    public void searchUnitInfo(Context context,String name, NetI<SearchResBean> adapter){
        SearchReqBean searchReqBean = new SearchReqBean();
        searchReqBean.setPageIndex(1);
        searchReqBean.setPageSize(1);
        searchReqBean.setKeyword(name);
        NetDataOpe.Unit.search(context,searchReqBean,adapter);
    }

    public void unBinUnit(Context context,NetI<UnBindResBean> adapter){
        UnBindReqBean unBindReqBean = new UnBindReqBean();
        unBindReqBean.setId(LocalValue.get登录返回信息().getUserId());
        unBindReqBean.setCompanyId(LocalValue.get登录返回信息().getCompanyId());
        NetDataOpe.User.unBinUnit(context,unBindReqBean,adapter);
    }

    public void getUserInfo(Context context,NetI<LoginResBean> adapter){
        UserInfoReqBean userInfoReqBean = new UserInfoReqBean();
        userInfoReqBean.setIsApp(1);
        userInfoReqBean.setId(LocalValue.get登录返回信息().getUserId());
        NetDataOpe.User.get用户信息(context, userInfoReqBean,adapter);
    }


    public void initUpdateInfo(UnitInfo o){
        getUpdateUnitReq().setAbbreviationName(o.getAbbreviationName());
        getUpdateUnitReq().setBelongArea(o.getBelongArea());
        getUpdateUnitReq().setCompanyAddress(o.getCompanyAddress());
        getUpdateUnitReq().setCompanyLat(StringUtil.getStr(o.getCompanyLat()));
        getUpdateUnitReq().setCompanyLng(StringUtil.getStr(o.getCompanyLng()));
        getUpdateUnitReq().setCompanyName(o.getCompanyName());
        getUpdateUnitReq().setContactName(o.getContactName());
        getUpdateUnitReq().setContactPhone(o.getContactPhone());
        getUpdateUnitReq().setAbbreviationName(o.getAbbreviationName());

    }

    public void updateUnit(Context context,UpdateUnitReq updateUnitReq, NetI<UpdateUnitRes> adapter){
        updateUnitReq.setId(LocalValue.get登录返回信息().getCompanyId());
        updateUnitReq.setEditer(LocalValue.get登录返回信息().getUserId());
        NetDataOpe.Unit.updateUnit(context,updateUnitReq,adapter);
    }

    public UpdateUnitReq getUpdateUnitReq() {
        return updateUnitReq;
    }
}
