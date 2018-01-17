package com.siweisoft.heavycenter.module.mana.good.news;

//by summer on 2017-12-19.

import android.content.Context;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.mana.good.news.NewsGoodReq;
import com.siweisoft.heavycenter.data.netd.mana.good.news.NewsGoodRes;

public class NewDAOpe extends AppDAOpe {

    private NewsGoodReq newsGoodReq = new NewsGoodReq();

    public NewDAOpe(Context context) {
        super(context);
    }

    public NewsGoodReq getNewsGoodReq() {
        newsGoodReq.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        return newsGoodReq;
    }

    public void NewsGood(NewsGoodReq newsGoodReq, NetI<NewsGoodRes> adapter){
        NetDataOpe.Mana.Good.NewsGood(getActivity(),newsGoodReq,adapter);
    }
}
