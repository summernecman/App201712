package com.siweisoft.heavycenter.module.main.msg.sys;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.msg.deal.MsgDealReqBean;
import com.siweisoft.heavycenter.data.netd.msg.deal.MsgDealResBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsReqBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;

import java.util.ArrayList;

public class SysDAOpe extends BaseDAOpe {

    private int moudle = -1;

    public SysDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public void getMsgSys(NetI<MsgsResBean> adapter){
        MsgsReqBean msgsReqBean = new MsgsReqBean();
        msgsReqBean.setUserId(LocalValue.getLoginInfo().getUserId());
        msgsReqBean.setPageIndex(0);
        msgsReqBean.setPageSize(100);
        msgsReqBean.setMessageCate(getMoudle());
        NetDataOpe.Msg.list(getActivity(),msgsReqBean,adapter);
    }

    public void dealMss(int messageId, String auditStatus, NetI<MsgDealResBean> adapter){
        MsgDealReqBean msgDealReqBean = new MsgDealReqBean();
        msgDealReqBean.setUserId(LocalValue.getLoginInfo().getUserId());
        msgDealReqBean.setMessageId(messageId);
        msgDealReqBean.setAuditStatus(auditStatus);
        NetDataOpe.Msg.deal(getActivity(),msgDealReqBean,adapter);

    }

    public int getMoudle() {
        return moudle;
    }

    public void setMoudle(int moudle) {
        this.moudle = moudle;
    }
}
