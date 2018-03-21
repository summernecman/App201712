package com.siweisoft.service.ui.setting.setting.videochat;

//by summer on 2018-03-21.

import android.view.View;
import android.widget.AdapterView;

import com.android.lib.base.fragment.BaseUIFrag;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;

public class VideoChatSettingFrag extends BaseServerFrag<VideoChatSettingUIOpe,VideoChatSettingDAOpe> implements AdapterView.OnItemSelectedListener {

    @Override
    public void initNow() {
        super.initNow();
        setTitleBean(new TitleBean("返回", "通话设置", ""));
        getP().getU().initSpiner(this);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        EMClient.getInstance().callManager().getCallOptions().setVideoResolution(VideoChatSettingDAOpe.videos[position][0],VideoChatSettingDAOpe.videos[position][1]);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        EMClient.getInstance().callManager().getCallOptions().setVideoResolution(VideoChatSettingDAOpe.videos[3][0],VideoChatSettingDAOpe.videos[3][1]);
    }
}
