package com.siweisoft.heavycenter.module.main.weigts;

//by summer on 2017-12-11.

import android.content.Intent;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.GsonUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.data.netd.weight.list.WeightListRes;
import com.siweisoft.heavycenter.data.netd.weight.save.SaveWeightRes;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.weigts.weight.WeigtFrag;
import com.siweisoft.heavycenter.module.view.scan.ScanAct;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.OnClick;

public class WeigtsFrag extends AppFrag<WeigtsUIOpe,WeigtsDAOpe> {


    @Override
    protected void onFristVisibleDelayInit() {
        getP().getU().initPages(this,getP().getD().initPages());
        getP().getD().listWeight(new UINetAdapter<WeightListRes>(this) {
            @Override
            public void onSuccess(WeightListRes o) {
                super.onSuccess(o);

            }
        });
    }


    @OnClick({R.id.tv_save})
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
            case R.id.tv_save:
                WeigtFrag weigtFrag = (WeigtFrag) getP().getD().getPages().get(0);
                if(weigtFrag.getP().getD().getWeightMsg().getMessage().getWeigh()!=0&&getP().getD().getWeightMsg()!=null&&getP().getD().getWeightMsg().getMessage()!=null){
                    getP().getD().getWeightMsg().getMessage().setWeigh(weigtFrag.getP().getD().getWeightMsg().getMessage().getWeigh());
                }
                getP().getU().bind.bottom.getRoot().setVisibility(View.GONE);
                if(weigtFrag.getP().getD().getWeightMsg()!=null&&weigtFrag.getP().getD().getWeightMsg().getMessage()!=null){
                    getP().getD().saveWeight(getP().getD().getWeightMsg(), new UINetAdapter<SaveWeightRes>(this,true) {
                        @Override
                        public void onSuccess(SaveWeightRes o) {
                            super.onSuccess(o);
                        }
                    });
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(String str) {
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(jsonObject==null){
            return ;
        }

        WeightMsg weightMsg = GsonUtil.getInstance().fromJson(str,WeightMsg.class);
        if(weightMsg==null|| weightMsg.getMessage()==null || weightMsg.getMessage().getOrder()==null){
            return;
        }
        getP().getU().bind.bottom.getRoot().setVisibility(View.VISIBLE);
        getP().getD().setWeightMsg(weightMsg);
        getP().getU().initUI(getP().getD().getWeightMsg());
        initPage(weightMsg);
    }

    public void initPage(WeightMsg weightMsg){
           for(int i=0;i<getP().getD().getPages().size();i++){
               WeigtFrag weigtFrag = (WeigtFrag) getP().getD().getPages().get(i);
               weigtFrag.getP().getD().setWeightMsg(weightMsg);
                weigtFrag.refresh();
           }
    }

    @Override
    protected boolean registerEventBus() {
        return true;
    }
}
