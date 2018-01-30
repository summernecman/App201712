package com.siweisoft.heavycenter.module.main.weigts;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.data.netd.scan.weight.WeightRes;
import com.siweisoft.heavycenter.data.netd.weight.list.WeightListReq;
import com.siweisoft.heavycenter.data.netd.weight.list.WeightListRes;
import com.siweisoft.heavycenter.data.netd.weight.save.SaveWeightReq;
import com.siweisoft.heavycenter.data.netd.weight.save.SaveWeightRes;
import com.siweisoft.heavycenter.module.main.weigts.weight.WeigtFrag;

import java.util.ArrayList;

public class WeigtsDAOpe extends AppDAOpe {


    private  WeightMsg weightMsg;

    public WeigtsDAOpe(Context context) {
        super(context);
    }

    public ArrayList<Fragment> getPages(){
        ArrayList<Fragment> pages = new ArrayList<>();
        for(int i=0;i<5;i++){
            pages.add(new WeigtFrag());
        }
        return pages;
    }


    public void listWeight(NetI<WeightListRes> adapter){
        WeightListReq weightListReq = new WeightListReq();
        weightListReq.setCompanyId(LocalValue.get登录返回信息().getCompanyId());
        weightListReq.setPageIndex(0);
        weightListReq.setPageSize(1000);
        NetDataOpe.Weight.listWeight(getActivity(),weightListReq,adapter);
    }

    public void saveWeight(WeightMsg weightMsg , NetI<SaveWeightRes> adapter){
        SaveWeightReq weightReq = new SaveWeightReq();
        weightReq.setOrderId(weightMsg.getMessage().getOrder().getOrderId());
        weightReq.setTransportRecordId(weightMsg.getMessage().getOrder().getYsdId());
        weightReq.setWeighLocation(SaveWeightReq.皮重);
        weightReq.setState(weightMsg.getMessage().getState());
        switch (weightMsg.getMessage().getState()){
            case "s0":
                weightReq.setWeighing(10);
                break;
        }
        weightReq.setOrderId(weightMsg.getMessage().getOrder().getOrderId());
        weightReq.setDeductWeight(0);
        NetDataOpe.Weight.saveWeight(getActivity(),weightReq,adapter);
    }

    public WeightMsg getWeightMsg() {
        return weightMsg;
    }

    public void setWeightMsg(WeightMsg weightMsg) {
        this.weightMsg = weightMsg;
    }
}
