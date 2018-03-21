package com.siweisoft.service.ui.setting.setting.videochat;

//by summer on 2018-03-21.

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.databinding.FragSettingVideochatBinding;

public class VideoChatSettingUIOpe extends BaseUIOpe<FragSettingVideochatBinding> {

    public void initSpiner(AdapterView.OnItemSelectedListener listener){
        bind.spiner.setOnItemSelectedListener(listener);
    }
}
