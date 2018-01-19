package com.siweisoft.heavycenter.module.main.trans;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransReq;
import com.siweisoft.heavycenter.data.netd.trans.trans.TransRes;
import com.siweisoft.heavycenter.module.main.trans.search.SearchFrag;
import com.siweisoft.heavycenter.module.view.map.MapUtil;

import java.util.ArrayList;

public class TransDAOpe extends BaseDAOpe {

    private TransReq transReq = new TransReq();

    private TransRes transRes = new TransRes();

    private int pageIndex = 0;


    public TransDAOpe(Context context) {
        super(context);
    }

    public MapUtil getMapUtil() {
       return MapUtil.getInstance();
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<5;i++){
            data.add("");
        }
        return data;
    }

    public void  stopMap(){
        if(getMapUtil().getLocationClient()==null){
            return;
        }
        getMapUtil().getLocationClient().stop();
    }

    public void startMap(){
        if(getMapUtil().getLocationClient()==null){
            return;
        }
        getMapUtil().getLocationClient().start();
    }

    public TransReq getTransReq() {
        transReq.setIsApp(1);
        transReq.setCompanyId(LocalValue.getLoginInfo().getCompanyId());
        transReq.setPageIndex(0);
        transReq.setPageSize(200);
        return transReq;
    }

    public void transs(TransReq transReq, NetI<TransRes> adapter){
        NetDataOpe.Trans.transs(getActivity(),transReq,adapter);
    }

    public TransRes getTransRes() {
        return transRes;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }
}
