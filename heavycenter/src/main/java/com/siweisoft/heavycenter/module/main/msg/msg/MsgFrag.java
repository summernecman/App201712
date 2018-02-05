package com.siweisoft.heavycenter.module.main.msg.msg;

//by summer on 2017-12-11.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.StringUtil;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.Test;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.msg.deal.MsgDealReqBean;
import com.siweisoft.heavycenter.data.netd.msg.deal.MsgDealResBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsReqBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;
import com.siweisoft.heavycenter.module.main.MainAct;

public class MsgFrag extends AppFrag<MsgUIOpe,MsgDAOpe> implements OnRefreshListener,OnLoadmoreListener ,ViewListener{


    @Override
    protected void onFristVisibleInit() {
        getP().getU().initRefresh(this,this);
        if(StringUtil.equals(MsgsReqBean.MESSAGE_CATE_ALL,getArguments().getString(ValueConstant.DATA_INDEX))){
            getP().getU().autoRefresh();
        }else{
            onRefresh(getP().getU().bind.refresh);
        }
    }

    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getD().setPageindex(getP().getD().getPageindex()+1);
        getP().getD().getMsgSys(getArguments().getString(ValueConstant.DATA_INDEX),new UINetAdapter<MsgsResBean>(this) {
            @Override
            public void onSuccess(MsgsResBean o) {
                o= new Test().getMsgsResBean();
                getP().getD().addData(o);
                getP().getU().notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().setPageindex(NetValue.PAGE_INDEX_START);
        getP().getD().getMsgsResBean().getResults().clear();
        getP().getD().getMsgSys(getArguments().getString(ValueConstant.DATA_INDEX),new UINetAdapter<MsgsResBean>(this) {
            @Override
            public void onSuccess(MsgsResBean o) {
                o= new Test().getMsgsResBean();
                getP().getD().addData(o);
                getP().getU().LoadListData(getP().getD().getMsgsResBean(),MsgFrag.this);
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
        final MsgsResBean.ResultsBean data = (MsgsResBean.ResultsBean) v.getTag(R.id.data);
        final String[] status = {MsgDealReqBean.AUDII_STATUS_YES};
        int auditstate = MsgsResBean.ResultsBean.AUDITOR_STATE_AGREEED;
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()) {
                    case R.id.bt_agree:
                        status[0] = MsgDealReqBean.AUDII_STATUS_YES;
                        auditstate = MsgsResBean.ResultsBean.AUDITOR_STATE_AGREEED;
                        break;
                    case R.id.bt_reject:
                        status[0] = MsgDealReqBean.AUDII_STATUS_NO;
                        auditstate = MsgsResBean.ResultsBean.AUDITOR_STATE_REJECT;
                        break;
                    case R.id.bt_mana:
                        status[0] = MsgDealReqBean.AUDII_STATUS_YES;
                        auditstate = MsgsResBean.ResultsBean.AUDITOR_STATE_AGREEED;
                        break;
                }
                if(v.getId()!=R.id.bt_agree&&v.getId()!=R.id.bt_reject&&v.getId()!=R.id.bt_mana){
                    return;
                }
                final int finalAuditstate = auditstate;
                getP().getD().dealMss(data.getMessageId(), status[0], new UINetAdapter<MsgDealResBean>(getContext()) {
                    @Override
                    public void onSuccess(MsgDealResBean o) {
                        data.setAuditState(finalAuditstate);
                        getP().getU().notifyDataSetChanged();
                        if((status[0] == MsgDealReqBean.AUDII_STATUS_YES)){
                            ((MainAct)getBaseUIActivity()).netRestart();
                        }
                    }
                });
                break;
        }
    }
}
