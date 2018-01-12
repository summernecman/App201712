package com.siweisoft.heavycenter.module.main.msg.publics;

//by summer on 2017-12-11.

import android.content.Context;

import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.news.NetI;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsReqBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;

import java.util.ArrayList;

public class PubDAOpe extends BaseDAOpe {

    public PubDAOpe(Context context) {
        super(context);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        for(int i=0;i<100;i++){
            data.add(""+i);
        }
        return data;
    }


    public void getMsgPub(NetI<MsgsResBean> adapter){
        MsgsReqBean msgsReqBean = new MsgsReqBean();
        msgsReqBean.setUserId(LocalValue.getLoginInfo().getUserId());
        msgsReqBean.setPageIndex(0);
        msgsReqBean.setPageSize(100);
        msgsReqBean.setMessageCate(MsgsReqBean.MESSAGE_CATE_PUB);
        NetDataOpe.Msg.list(getActivity(),msgsReqBean,adapter);
    }

}
