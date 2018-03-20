package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-14.

import android.content.Context;
import android.view.View;

import com.android.lib.base.ope.BaseUIOpe;
import com.siweisoft.service.GlideApp;
import com.siweisoft.service.R;
import com.siweisoft.service.databinding.FragVideochatBinding;
import com.siweisoft.service.netdb.NetValue;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;

public class VideoChatUIOpe extends BaseUIOpe<FragVideochatBinding> {



    public void setCallInfo(UserBean userBean) {
        bind.tvCallinfo.setVisibility(View.VISIBLE);
        bind.tvCallinfo.setText("正在向 " + userBean.getPhone() + "(" + userBean.getName() + ") 发起通话");
    }

    public void hideCallInfo() {
        bind.tvCallinfo.setVisibility(View.GONE);
    }

    public void isVideo(boolean is){
        if(!is){
            bind.btnSwitchvideo.setVisibility(View.GONE);
            bind.btnCamera.setVisibility(View.GONE);
            bind.surfaceviewremove.setVisibility(View.GONE);
            bind.removeview.setVisibility(View.GONE);
            bind.surfaceviewlocal.setVisibility(View.GONE);
            bind.ivHead.setVisibility(View.VISIBLE);
            GlideApp.with(context).asBitmap().centerCrop().placeholder(R.drawable.icon_head1).load(NetValue.获取文件路径("/" + Value.getUserInfo().getHeadurl())).into(bind.ivHead);

        }else{
            bind.btnSwitchvideo.setVisibility(View.VISIBLE);
            bind.btnCamera.setVisibility(View.VISIBLE);
            bind.removeview.setVisibility(View.VISIBLE);
            bind.surfaceviewremove.setVisibility(View.VISIBLE);
            bind.surfaceviewlocal.setVisibility(View.VISIBLE);
            bind.ivHead.setVisibility(View.GONE);
        }
    }

}
