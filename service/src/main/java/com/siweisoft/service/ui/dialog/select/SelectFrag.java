package com.siweisoft.service.ui.dialog.select;

//by summer on 17-09-11.

import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;

import butterknife.OnClick;

public class SelectFrag extends BaseServerFrag<SelectUIOpe, SelectDAOpe> {

    OnFinishListener onFinishListener;

    @Override
    public void initdelay() {
        super.initdelay();
    }

    @OnClick({R.id.tv_video, R.id.ll_root,R.id.tv_voice})
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
