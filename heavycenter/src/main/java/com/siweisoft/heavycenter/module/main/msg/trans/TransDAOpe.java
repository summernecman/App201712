package com.siweisoft.heavycenter.module.main.msg.trans;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsReqBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;

import java.util.ArrayList;

public class TransDAOpe extends BaseDAOpe {

    public TransDAOpe(Context context) {
        super(context);
    }


    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }

    public void getMsgTrans(NetI<MsgsResBean> adapter){
        MsgsReqBean msgsReqBean = new MsgsReqBean();
        msgsReqBean.setUserId(LocalValue.getLoginInfo().getUserId());
        msgsReqBean.setPageIndex(0);
        msgsReqBean.setPageSize(100);
        msgsReqBean.setMessageCate(MsgsReqBean.MESSAGE_CATE_TRANS);
        NetDataOpe.Msg.list(getActivity(),msgsReqBean,adapter);
    }

}
