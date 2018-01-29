package com.siweisoft.heavycenter.module.myce.unit.addr;

//by summer on 2017-12-19.

import android.content.Context;

import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.poi.PoiAddrInfo;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.module.view.map.MapUtil;

import java.util.ArrayList;
import java.util.List;

public class AddrDAOpe extends AppDAOpe {

    private List<PoiInfo> addrs = new ArrayList<>();

    public AddrDAOpe(Context context) {
        super(context);
    }




    public MapUtil getMapUtil() {
        return MapUtil.getInstance();
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

    public List<PoiInfo> getAddrs() {
        return addrs;
    }

    public void setAddrs(List<PoiInfo> addrs) {
        if(addrs==null){
            return;
        }
        this.addrs.clear();
        this.addrs.addAll(addrs);
    }
}
