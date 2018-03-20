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

    @Override
    public void initDA() {
        super.initDA();
        videoChatListener = new VideoChatListener(getActivity());
        emMsgListener = new EMMsgListener();
        chatConnectListener = new ChatConnectListener((MainAct) getActivity());
    }

    public ArrayList<Fragment> getFragment() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new Onefrag());
        fragments.add(new TwoFrag());
        fragments.add(new ThreeFrag());
        return fragments;
    }

    public void getLoginInfo(OnFinishListener onFinishListener) {
        NetDataOpe.User.getLoginInfo(getActivity(),Value.getUserInfo(), onFinishListener);
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
