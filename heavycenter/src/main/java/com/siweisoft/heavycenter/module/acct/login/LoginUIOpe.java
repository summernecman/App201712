package com.siweisoft.heavycenter.module.acct.login;

//by summer on 2017-12-14.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragLoginBinding;

public class LoginUIOpe extends AppUIOpe<FragLoginBinding> {

    public LoginUIOpe(Context context) {
        super(context);
    }

    public void initBg(String url){
       // GlideApp.with(context).asBitmap().load(url).centerCrop().into(bind.image);
    }

}
