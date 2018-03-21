package com.siweisoft.service.ui.main;

//by summer on 2017-07-03.

import android.content.Context;
import android.support.v4.app.Fragment;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.NetDataOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.home.frag.onfrag.Onefrag;
import com.siweisoft.service.ui.home.frag.threefrag.ThreeFrag;
import com.siweisoft.service.ui.home.frag.twofrag.TwoFrag;

import java.util.ArrayList;

public class MainDAOpe extends BaseDAOpe {


    private VideoChatListener videoChatListener;

    private EMMsgListener emMsgListener;

    private ChatConnectListener chatConnectListener;

    public void initListener(Context context){
        videoChatListener=new VideoChatListener(context);
        chatConnectListener =new ChatConnectListener((MainAct) context);
        emMsgListener= new EMMsgListener();
    }

    public ArrayList<Fragment> getFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Onefrag());
        fragments.add(new TwoFrag());
        fragments.add(new ThreeFrag());
        return fragments;
    }

    public void getLoginInfo(Context context,OnFinishListener onFinishListener) {
        NetDataOpe.User.getLoginInfo(context,Value.getUserInfo(), onFinishListener);
    }

    public VideoChatListener getVideoChatListener() {
        return videoChatListener;
    }

    public EMMsgListener getEmMsgListener() {
        return emMsgListener;
    }

    public ChatConnectListener getChatConnectListener() {
        return chatConnectListener;
    }
}
