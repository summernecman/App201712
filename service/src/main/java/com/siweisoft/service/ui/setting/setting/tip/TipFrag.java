package com.siweisoft.service.ui.setting.setting.tip;

//by summer on 17-09-11.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;

import butterknife.OnClick;

public class TipFrag extends BaseServerFrag<TipUIOpe, TipDAOpe> {

    OnFinishListener onFinishListener;

    @Override
    public void doThing() {
        super.doThing();
    }

    @OnClick({R.id.tv_yes, R.id.ll_root,R.id.tv_no})
    public void onClickEvent(View v) {
        if (onFinishListener != null) {
            onFinishListener.onFinish(v);
        }
    }

    @Override
    public void onClick(View v) {

    }

    public void setOnFinishListener(OnFinishListener onFinishListener) {
        this.onFinishListener = onFinishListener;
    }
}
