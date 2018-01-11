package com.siweisoft.heavycenter.module.myce.unit.bind;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetAdapter;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.unit.list.ListReqBean;
import com.siweisoft.heavycenter.data.netd.unit.list.ListResBean;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchReqBean;
import com.siweisoft.heavycenter.data.netd.unit.search.SearchResBean;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindReqBean;
import com.siweisoft.heavycenter.data.netd.user.unit.bind.BindResBean;

public class BindDAOpe extends AppDAOpe {


    public static final int BIND_UNIT = 0;

    public static final int UP_UNIT = 1;

    public BindDAOpe(Context context) {
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

    public void bindUnit(int companyId, NetI<BindResBean> adapter){
        BindReqBean bindReqBean = new BindReqBean();
        bindReqBean.setId(LocalValue.getLoginInfo().getUserId());
        bindReqBean.setCompanyId(companyId);
        bindReqBean.setBindOperateType(BindReqBean.BIND_OPERATE_TYPE_SEARCH);
        bindReqBean.setIsManager(BindReqBean.IS_MANAGER_NO);
        NetDataOpe.User.binUnit(getActivity(),bindReqBean,adapter);
    }
}
