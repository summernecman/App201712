package com.siweisoft.heavycenter.module.acct.repwd;

//by summer on 2017-12-14.

import android.content.Context;

import com.siweisoft.heavycenter.GlideApp;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragRepwdBinding;

public class RepwdUIOpe extends AppUIOpe<FragRepwdBinding> {

    public RepwdUIOpe(Context context) {
        super(context);
    }

    public void initBg(String url){
       // GlideApp.with(context).asBitmap().load(url).centerCrop().into(bind.image);
    }

}
