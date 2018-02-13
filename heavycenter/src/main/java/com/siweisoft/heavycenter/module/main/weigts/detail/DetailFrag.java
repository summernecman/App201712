package com.siweisoft.heavycenter.module.main.weigts.detail;

//by summer on 2017-12-11.

import android.content.Intent;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.view.scan.ScanAct;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Date;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> {

    public DetailFrag() {
        LogUtil.E(343);
    }

    @Override
    public void initNow() {
        super.initNow();
        onFristVisibleInit();
    }

    @Override
    public void onFristVisibleInit() {
        getP().getU().LoadListData(getP().getD().getWeightMsgs());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)getBaseUIAct()).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right:
                if(getActivity() instanceof MainAct){
                    new IntentIntegrator(getBaseAct()).setCaptureActivity(ScanAct.class).initiateScan();
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(String str) {
        WeightMsg.MessageBean m = GsonUtil.getInstance().fromJson(str,WeightMsg.class).getMessage();
        if(m==null){
            return ;
        }
        getP().getU().initTopUI(m);
        if(NullUtil.isStrEmpty(m.getTime())){
            m.setTime(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY__MM__DD__HH__MM__SS,new Date()));
        }
        if(NullUtil.isStrEmpty(m.getMessageType())||NullUtil.isStrEmpty(m.getState())){
            return;
        }
        getP().getD().getWeightMsgs().add(m);
      getP().getU().notifyDataSetChanged();



    }

    @Override
    protected boolean registerEventBus() {
        return  true;
    }
}
