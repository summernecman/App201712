package com.siweisoft.heavycenter.module.main.order.news;

//by summer on 2018-01-17.

import android.content.Context;
import android.view.View;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.order.news.NewOrderRes;
import com.siweisoft.heavycenter.data.netd.order.news.NewsOrderReqBean;

import java.util.ArrayList;

public class NewOrderDAOpe extends BaseDAOpe {

    private NewsOrderReqBean newsOrderReqBean = new NewsOrderReqBean();

    public NewOrderDAOpe(Context context) {
        super(context);
    }


    public NewsOrderReqBean getNewsOrderReqBean() {
        newsOrderReqBean.setCreater(LocalValue.getLoginInfo().getUserId());
        return newsOrderReqBean;
    }

    public void newOrder(NewsOrderReqBean newsOrderReqBean, NetI<NewOrderRes> adapter){
        NetDataOpe.Order.newOrder(getActivity(),newsOrderReqBean,adapter);
    }
}
