package com.siweisoft.service.ui.acct.login;

//by summer on 2017-07-03.

import android.text.Editable;
import android.view.View;

import com.android.lib.base.listener.BaseTextWather;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.GlideApp;
import com.siweisoft.service.BR;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.databinding.FragLoginBinding;
import com.siweisoft.service.data.netd.NetValue;

public class LoginUIOpe extends BaseUIOpe<FragLoginBinding> {

    @Override
    public void initUI() {
        super.initUI();
        initIp();
    }

    public void initInput(UserBean userBean){
        bind.setVariable(BR.login,userBean);
    }

    public void initIp() {
        bind.etServer.addTextChangedListener(new BaseTextWather() {
            @Override
            public void afterTextChanged(Editable s) {
                super.afterTextChanged(s);
                NetValue.保存域名到文件(context,s.toString());
            }
        });
        bind.etServer.setText(NetValue.获取域名从文件(context));
    }

    public void initImage(String url) {
        GlideApp.with(context).asBitmap().load(url).centerCrop().into(bind.ivTop);
    }

    public void showErrorMsg(final String msg) {
        LogUtil.E(msg);
    }
}
