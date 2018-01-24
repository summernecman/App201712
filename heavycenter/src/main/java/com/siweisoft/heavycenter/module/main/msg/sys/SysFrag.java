package com.siweisoft.heavycenter.module.main.msg.sys;

//by summer on 2017-12-11.

import android.view.View;

import com.android.lib.base.listener.ViewListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.network.newsf.UIFNetAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.msg.deal.MsgDealReqBean;
import com.siweisoft.heavycenter.data.netd.msg.deal.MsgDealResBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;

import java.util.ArrayList;

public class SysFrag extends AppFrag<SysUIOpe,SysDAOpe> implements OnRefreshListener,OnLoadmoreListener ,ViewListener{

    @Override
    public void initData() {
        super.initData();
        getP().getD().setMoudle(getArguments().getString(ValueConstant.DATA_POSITION));
        getP().getU().initRefresh(this,this);
        getP().getU().autoRefresh();

    }


    @Override
    public void onLoadmore(RefreshLayout refreshlayout) {
        getP().getD().setPageindex(getP().getD().getPageindex()+1);
        getP().getD().getMsgSys(new UIFNetAdapter<MsgsResBean>(this) {
            @Override
            public void onResult(boolean success, String msg, MsgsResBean o) {
                super.onResult(success, msg, o);
                getP().getU().finishLoadmore();
                getP().getD().addData(o);
                getP().getU().notifyDataSetChanged();

            }
        });
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getP().getD().setPageindex(0);
        getP().getD().getMsgsResBean().setResults(new ArrayList<MsgsResBean.ResultsBean>());
        getP().getD().getMsgSys(new UIFNetAdapter<MsgsResBean>(this) {
            @Override
            public void onResult(boolean success, String msg, MsgsResBean o) {
                super.onResult(success, msg, o);
                getP().getD().addData(o);
                getP().getU().finishRefresh();
                getP().getU().LoadListData(getP().getD().getMsgsResBean(),SysFrag.this);
            }
        });
    }

    @Override
    public void onInterupt(int type, View v) {
        final MsgsResBean.ResultsBean data = (MsgsResBean.ResultsBean) v.getTag(R.id.data);
        String status = MsgDealReqBean.AUDII_STATUS_YES;
        int auditstate = MsgsResBean.ResultsBean.AUDITOR_STATE_AGREEED;
        switch (type){
            case ViewListener.TYPE_ONCLICK:
                switch (v.getId()) {
                    case R.id.bt_agree:
                        status = MsgDealReqBean.AUDII_STATUS_YES;
                        auditstate = MsgsResBean.ResultsBean.AUDITOR_STATE_AGREEED;
                        break;
                    case R.id.bt_reject:
                        status = MsgDealReqBean.AUDII_STATUS_NO;
                        auditstate = MsgsResBean.ResultsBean.AUDITOR_STATE_REJECT;
                        break;
                    case R.id.bt_mana:
                        status = MsgDealReqBean.AUDII_STATUS_YES;
                        auditstate = MsgsResBean.ResultsBean.AUDITOR_STATE_AGREEED;
                        break;
                }
                final int finalAuditstate = auditstate;
                getP().getD().dealMss(data.getMessageId(), status, new UINetAdapter<MsgDealResBean>(getContext()) {
                    @Override
                    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                        stopLoading();
                        if("200".equals(baseResBean.getCode())){
                            data.setAuditState(finalAuditstate);
                            getP().getU().notifyDataSetChanged();
                        }
                    }
                });
                break;
        }
    }
}
