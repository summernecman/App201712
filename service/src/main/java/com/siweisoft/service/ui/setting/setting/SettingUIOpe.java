package com.siweisoft.service.ui.setting.setting;

//by summer on 17-08-28.

import android.content.Context;

import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.FragSettingBinding;

public class SettingUIOpe extends BaseUIOpe<FragSettingBinding> {


    public SettingUIOpe(Context context) {
        super(context);
    }

    public void setVersion(String verison){
        bind.tvVersion.setText(verison);
    }
}
