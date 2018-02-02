package com.siweisoft.heavycenter.module.main.weigts;

//by summer on 2017-12-11.

import android.content.Intent;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.system.HandleUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.jpush.SimpleWeightMsg;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.data.netd.jpush.WeightRes;
import com.siweisoft.heavycenter.data.netd.weight.list.WeightListRes;
import com.siweisoft.heavycenter.data.netd.weight.save.SaveWeightRes;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.weigts.weight.WeigtFrag;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;

public class WeigtsFrag extends AppFrag<WeigtsUIOpe,WeigtsDAOpe> {

    @Override
    public void initData() {
        super.initData();
        lazyInit();
        setInited();
        getP().getD().listWeight(new UINetAdapter<WeightListRes>(getActivity()) {
            @Override
            public void onSuccess(WeightListRes o) {
                super.onSuccess(o);

            }
        });
    }

    @Override
    public void lazyInit() {
        getP().getU().initPages(this,getP().getD().getPages());
    }

    @OnClick({R.id.tv_save})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ftv_back:
                ((MainAct)activity).getP().getU().switchDrawer();
                break;
            case R.id.ftv_right:
                if(getActivity() instanceof MainAct){
                    MainAct mainAct = (MainAct) getActivity();
                    Intent intent = new Intent(mainAct, CaptureActivity.class);
                    activity.startActivityForResult(intent, ValueConstant.CODE_REQUSET);
                }
                break;
            case R.id.tv_save:
                getP().getD().saveWeight(getP().getD().getWeightMsg(), new UINetAdapter<SaveWeightRes>(getActivity()) {
                    @Override
                    public void onSuccess(SaveWeightRes o) {
                        super.onSuccess(o);
                    }
                });
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
        getP().getD().setWeightMsg(weightMsg);
        getP().getU().initUI(getP().getD().getWeightMsg());

    }

    public void initPage(WeightMsg weightMsg){
        for(int i=0;i<getP().getD().getPages().size();i++){
            WeigtFrag weigtFrag = (WeigtFrag) getP().getD().getPages().get(i);
            WeightRes weightRes = new WeightRes();
            weightRes.setWeight(weightMsg.getMessage().getWeigh());

            LoginResBean loginResBean = new LoginResBean();
            //loginResBean.setTrueName(weightMsg.getMessage().getOrder());
           // weigtFrag.getP().getU().init();
        }
    }

}
