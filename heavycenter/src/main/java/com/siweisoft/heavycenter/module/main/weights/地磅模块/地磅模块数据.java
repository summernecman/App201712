package com.siweisoft.heavycenter.module.main.weights.地磅模块;

//by summer on 2017-12-11.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.network.news.NetI;
import com.android.lib.util.NullUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.data.netd.weight.list.WeightListReq;
import com.siweisoft.heavycenter.data.netd.weight.list.WeightListRes;
import com.siweisoft.heavycenter.data.netd.weight.save.SaveWeightReq;
import com.siweisoft.heavycenter.data.netd.weight.save.SaveWeightRes;
import com.siweisoft.heavycenter.module.main.weights.weight.WeigtFrag;

import java.util.ArrayList;

public class 地磅模块数据 extends AppDAOpe {


    private  WeightMsg weightMsg;

    private ArrayList<Fragment> pages = new ArrayList<>();

    private ArrayList<Fragment> bottomFrag = new ArrayList<>();


    public ArrayList<Fragment> get地磅们(BaseUIFrag frag){
        pages.clear();
        for(int i=0;i<5;i++){
            pages.add(WeigtFrag.getInstance(frag.get容器()));
        }
        return pages;
    }

    public ArrayList<Fragment> getPages(BaseUIFrag frag) {
        if(pages==null){
            get地磅们(frag);
        }
        return pages;
    }


    public static void get地磅列表(Context context, NetI<WeightListRes> adapter){
        WeightListReq weightListReq = new WeightListReq();
        weightListReq.setCompanyId(LocalValue.get登录返回信息().getCompanyId());
        weightListReq.setPageIndex(0);
        weightListReq.setPageSize(1000);
        NetDataOpe.Weight.listWeight(context,weightListReq,adapter);
    }

    public void saveWeight(Context context,WeightMsg weightMsg , NetI<SaveWeightRes> adapter){
        if(weightMsg==null||weightMsg.getMessage()==null){
            return;
        }
        SaveWeightReq weightReq = new SaveWeightReq();
        weightReq.setOrderId(weightMsg.getMessage().getOrder().getOrderId());
        weightReq.setTransportRecordId(weightMsg.getMessage().getOrder().getYsdId());
        weightReq.setState(weightMsg.getMessage().getState());
        if(NullUtil.isStrEmpty(weightMsg.getMessage().getState())){
            ToastUtil.getInstance().showShort(context,"没有返回状态码");
            return;
        }
        switch (weightMsg.getMessage().getState()){
            case "s0":
            case "sF":
            case "s3":
            case "s7":
            case "sD":
            case "s5":
            case "rF":
            case "r5":
            case "r7":
            case "rD":
                weightReq.setWeighLocation(SaveWeightReq.皮重);
                break;
            case "s1":
            case "r1":
            case "r3":
                weightReq.setWeighLocation(SaveWeightReq.毛重);
                break;
        }
        weightReq.setWeighing(weightMsg.getMessage().getWeigh());
        weightReq.setOrderId(weightMsg.getMessage().getOrder().getOrderId());
        weightReq.setDeductWeight(0);
        weightReq.setDriverId(weightMsg.getMessage().getOrder().getDriverId());
        NetDataOpe.Weight.saveWeight(context,weightReq,adapter);
    }

    public WeightMsg getWeightMsg() {
        return weightMsg;
    }

    public void setWeightMsg(WeightMsg weightMsg) {
        this.weightMsg = weightMsg;
    }
}
