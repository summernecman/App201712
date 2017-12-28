package com.siweisoft.service.ui.setting.setting;

//by summer on 17-08-28.

import android.view.View;

import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.R;
import com.siweisoft.service.ServieApp;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.ui.Constant.Value;

import butterknife.OnClick;

public class SettingFrag extends BaseServerFrag<SettingUIOpe, SettingDAOpe> {

    @Override
    public void doThing() {
        super.doThing();
        setTitleBean(new TitleBean("返回", "设置", ""));
    }
    @OnClick({R.id.ll_set})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.ll_set:
                EMClient.getInstance().logout(true, new EMCallBack() {
                    @Override
                    public void onSuccess() {
                        SPUtil.getInstance().saveBoolean(Value.autologin,false);
                        ((ServieApp) activity.getApplication()).exit();
                    }

                    @Override
                    public void onError(int code, String error) {
                        SPUtil.getInstance().saveBoolean(Value.autologin,false);
                        ((ServieApp) activity.getApplication()).exit();
                    }

                    @Override
                    public void onProgress(int progress, String status) {

                    }
                });
                break;
        }

    }
}
