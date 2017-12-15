package com.siweisoft.heavycenter.module.acct.regist;

//by summer on 2017-12-14.

import android.content.Context;

import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.databinding.FragRegistBinding;

public class RegistUIOpe extends AppUIOpe<FragRegistBinding> {

    public RegistUIOpe(Context context) {
        super(context);
    }

    public void initBg(String url){
        //GlideApp.with(context).asBitmap().load(url).centerCrop().into(bind.image);
    }

}
