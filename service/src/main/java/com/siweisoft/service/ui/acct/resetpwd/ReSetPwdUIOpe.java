package com.siweisoft.service.ui.acct.resetpwd;

//by summer on 2017-07-10.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.NullUtil;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.databinding.FragResetBinding;

public class ReSetPwdUIOpe extends BaseUIOpe<FragResetBinding> {


    @Override
    public void initUI() {
        super.initUI();
        bind.tophead.setTitle2(new TitleBean("返回", "重置密码", ""));
    }

    public boolean vevify() {
        return !NullUtil.isStrEmpty(bind.etAccount.getText().toString()) && !NullUtil.isStrEmpty(bind.etPwd.getText().toString()) && !NullUtil.isStrEmpty(bind.etCode.getText().toString());
    }

    public boolean vevifyPhone() {
        return !NullUtil.isStrEmpty(bind.etAccount.getText().toString());
    }
}
