package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.app.Activity;
import android.content.Context;
import android.text.Editable;

import com.android.lib.base.listener.BaseTextWather;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;
import com.jaeger.library.StatusBarUtil;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.databinding.FragLoginBinding;
import com.siweisoft.service.netdb.NetValue;
import com.siweisoft.service.ui.Constant.Value;

public class LoginUIOpe extends BaseUIOpe<FragLoginBinding> {


    public LoginUIOpe(Context context) {
        super(context);
        //StatusBarUtil.setTranslucentForImageView((Activity) context, null);
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
        //GlideApp.with(context).asBitmap().centerCrop().load("content://com.android.providers.media.documents/document/image%3A115755").into(bind.ivTop);
        GlideApp.with(context).asBitmap().load(url).centerCrop().into(bind.ivTop);
        // bind.ivTop
    }

    public void showErrorMsg(final String msg) {
        LogUtil.E(msg);
    }
}
