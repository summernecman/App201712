package com.siweisoft.heavycenter.module.main.weights.地磅模块;

//by summer on 2017-12-11.

import android.view.View;

import butterknife.Optional;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.GsonUtil;
import com.google.zxing.integration.android.IntentIntegrator;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.data.netd.weight.list.WeightListRes;
import com.siweisoft.heavycenter.data.netd.weight.save.SaveWeightRes;
import com.siweisoft.heavycenter.module.main.main.MainAct;
import com.siweisoft.heavycenter.module.main.weights.weight.WeigtFrag;
import com.siweisoft.heavycenter.module.view.scan.ScanAct;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.OnClick;

public class 地磅模块控制 extends AppFrag<地磅模块界面,地磅模块数据> {


    @Override
    protected void on第一次显示延迟加载() {
        地磅模块数据.get地磅列表(getActivity(),new UINetAdapter<WeightListRes>(this) {
            @Override
            public void onSuccess(WeightListRes o) {
                super.onSuccess(o);
                getP().getU().init地磅仪表(getBaseUIFrag(),getP().getD().get地磅们(getBaseUIFrag()));
            }
        });
    }


    @Optional
    @OnClick({R.id.tv_save,R.id.tv_save_sel,R.id.tv_select,R.id.tv_select_sel})
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
                WeigtFrag weigtFrag = (WeigtFrag) getP().getD().getPages(getBaseUIFrag()).get(0);
                if(weigtFrag.getP().getD().getWeightMsg().getMessage().getWeigh()!=0&&getP().getD().getWeightMsg()!=null&&getP().getD().getWeightMsg().getMessage()!=null){
                    getP().getD().getWeightMsg().getMessage().setWeigh(weigtFrag.getP().getD().getWeightMsg().getMessage().getWeigh());
                }
                getP().getU().showBottomView(true);
                if(weigtFrag.getP().getD().getWeightMsg()!=null&&weigtFrag.getP().getD().getWeightMsg().getMessage()!=null){
                    getP().getD().saveWeight(getBaseAct(),getP().getD().getWeightMsg(), new UINetAdapter<SaveWeightRes>(this,true) {
                        @Override
                        public void onSuccess(SaveWeightRes o) {
                            super.onSuccess(o);
                            getP().getU().bind.bottom.tvSave.setVisibility(View.INVISIBLE);
                        }
                    });
                }
                break;
            case R.id.tv_select:
                getP().getU().showBottomView(true);
                break;
            case R.id.tv_select_sel:
                getP().getU().showBottomView(false);
                break;
            case R.id.tv_save_sel:
                getP().getU().showBottomView(false);
                break;

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void get推送消息(String str) {
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
        getP().getU().showBottomView(false);
        getP().getD().setWeightMsg(weightMsg);
        getP().getU().initUI(getP().getD().getWeightMsg());
        getP().getU().bind.bottom.tvSave.setVisibility(View.VISIBLE);
        go刷新地磅仪表(weightMsg);
    }

    public void go刷新地磅仪表(WeightMsg weightMsg){
           for(int i=0;i<getP().getD().getPages(getBaseUIFrag()).size();i++){
               WeigtFrag weigtFrag = (WeigtFrag) getP().getD().getPages(getBaseUIFrag()).get(i);
               weigtFrag.getP().getD().setWeightMsg(weightMsg);
                weigtFrag.refresh();
           }
    }

    @Override
    protected boolean is注册事件总线() {
        return true;
    }
}
