package com.siweisoft.heavycenter.module.main.weigts.detail;

//by summer on 2017-12-11.

import android.content.Intent;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.util.GsonUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.uuzuche.lib_zxing.activity.CaptureActivity;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailFrag extends AppFrag<DetailUIOpe,DetailDAOpe> {

    @Override
    public void initNow() {
        super.initNow();
        onFristVisibleInit();
        setInited();
    }

    @Override
    public void onFristVisibleInit() {
        getP().getU().initRecycle();
        getP().getU().LoadListData(getP().getD().getWeightMsgs());
    }

    @Override
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
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void XXX(String str) {
        WeightMsg weightMsg = GsonUtil.getInstance().fromJson(str,WeightMsg.class);
      getP().getD().getWeightMsgs().add(str);
      getP().getU().notifyDataSetChanged();

    }

}
