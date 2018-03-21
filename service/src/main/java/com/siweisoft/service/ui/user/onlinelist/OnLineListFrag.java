package com.siweisoft.service.ui.user.onlinelist;

//by summer on 2017-07-04.

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.interf.view.OnAppItemClickListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.android.lib.view.dialog.list.DialogListFrag;
import com.hyphenate.chat.EMClient;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.bean.TitleBean;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;
import com.siweisoft.service.ui.dialog.select.SelectFrag;
import com.siweisoft.service.ui.user.userinfo.UserInfoFrag;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;
import butterknife.Optional;

import static com.siweisoft.service.netdb.video.VideoBean.type_video_record;

public class OnLineListFrag extends BaseServerFrag<OnLineListUIOpe, OnLineListDAOpe> implements OnRefreshListener{


    public static final String 获取好友列表 ="获取好友列表";

    @Override
    public void initdelay() {
        getP().getU().bind.title.findViewById(R.id.ftv_right2).setOnClickListener(this);
        getP().getU().initRefresh(this);
        getP().getU().initList(new ArrayList<UserBean>(), null);
        initData2();

    }


    public void initData2() {
        getP().getD().getUserContactsByUserIdAndType(getBaseAct(),Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                getP().getU().initList((ArrayList<UserBean>) o, OnLineListFrag.this);
                getP().getU().finishRefresh();
            }
        });

    }
    @Optional
    @OnClick({R.id.ftv_right})
    public void onClick(View v) {
        super.onClick(v);
        final UserBean userBean = (UserBean) v.getTag(R.id.data);
        v.setEnabled(false);
        switch (v.getId()) {
            case R.id.iv_head:
                UserInfoFrag userInfoFrag = new UserInfoFrag();
                userInfoFrag.setArguments(new Bundle());
                userInfoFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, userBean);
                FragmentUtil2.getInstance().add(getActivity(), Value.ROOTID_TWO, userInfoFrag);
                break;
            case R.id.iv_call:

                SelectFrag select = new SelectFrag();
                FragmentUtil2.getInstance().add(getActivity(), Value.FULLSCREEN, select);
                select.setOnFinishListener(new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        FragmentUtil2.getInstance().removeTopRightNow(getActivity(), Value.FULLSCREEN);
                        switch (((View) o).getId()) {
                            case R.id.tv_video:
                            case R.id.tv_voice:
                                VideoBean videoBean = new VideoBean();
                                videoBean.setToUser(userBean);
                                videoBean.setFromUser(Value.getUserInfo());
                                videoBean.setFromphone(Value.getUserInfo().getPhone());
                                videoBean.setTophone(userBean.getPhone());
                                if(((View) o).getId()==R.id.tv_voice){
                                    videoBean.setVideo(false);
                                }else{
                                    videoBean.setVideo(true);
                                }
                                switch (((View) o).getId()){
                                    case R.id.tv_video:
                                        videoBean.setType(VideoBean.type_video);
                                        videoBean.setType(type_video_record);
                                        break;
                                    case R.id.tv_voice:
                                        videoBean.setType(VideoBean.type_voice);
                                        break;
                                }
                                VideoChatFrag videoChatFrag = new VideoChatFrag();
                                videoChatFrag.setArguments(new Bundle());
                                videoChatFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, videoBean);
                                FragmentUtil2.getInstance().add(getActivity(), Value.FULLSCREEN, videoChatFrag);
                                break;
                        }
                    }
                });

                break;
//            case R.id.ftv_right:
//                List<String> strs = new ArrayList<>();
//                for(int i=0;i<10;i++){
//                    strs.add("fdfdsfsd"+i);
//                }
//                DialogListFrag frag = new DialogListFrag();
//                frag.init(strs);
//                FragmentUtil2.getInstance().add(fragment.activity, Value.ROOTID_TWO, frag);
//                frag.setOnAppItemsClickListener(new OnAppItemClickListener() {
//                    @Override
//                    public void onAppItemClick(View view, int position) {
//                        IntentIntegrator.forSupportFragment(OnLineListFrag.this).initiateScan();
//                    }
//                });
//                break;
        }
        v.setEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EMClient.getInstance().chatroomManager().removeChatRoomListener(getP().getD().getOnlineChangeListener());
        if (getP().getD().getEmChatRoom() == null) {
            return;
        }
        EMClient.getInstance().chatroomManager().leaveChatRoom(getP().getD().getEmChatRoom().getId());
    }

    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        if (event.isme && event.data instanceof Integer) {
            getP().getU().refresh();
        } else {
            initData2();
        }
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        initData2();
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if(result != null) {
//            LogUtil.E(result.getContents());
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//    }

    @Override
    protected boolean is注册事件总线() {
        return true;
    }
}
