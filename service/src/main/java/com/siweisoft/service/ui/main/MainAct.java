package com.siweisoft.service.ui.main;

//by summer on 2017-07-03.

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.util.system.AudioUtil;
import com.android.lib.util.system.UUUIDUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.EMNoActiveCallException;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.siweisoft.service.R;
import com.siweisoft.service.ServieApp;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.crash.CrashBean;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.chat.recept.ReceiptFrag;
import com.siweisoft.service.ui.chat.videochat.VideoChatFrag;

import butterknife.OnClick;
import butterknife.Optional;

public class MainAct extends BaseUIActivity<MainUIOpe, MainDAOpe> implements OnFinishListener {

    LoginInfoBroadCast loginInfoBroadCast;


    OnTitleClick onTitleClick;

    CallReceiver callReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getP().getU().initViewPager(getSupportFragmentManager(), getP().getD().getFragment());
        loginInfoBroadCast = new LoginInfoBroadCast();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(getPackageName() + ValueConstant.ACITON_GLOB_CAST);
        //registerReceiver(loginInfoBroadCast, intentFilter);
        //CrashHander.getInstance().init(this, this);

        IntentFilter callFilter = new IntentFilter(EMClient.getInstance().callManager().getIncomingCallBroadcastAction());
        callReceiver = new CallReceiver();
        registerReceiver(callReceiver, callFilter);

        getP().getD().initListener(this);
        EMClient.getInstance().callManager().addCallStateChangeListener(getP().getD().getVideoChatListener());
        EMClient.getInstance().chatManager().addMessageListener(getP().getD().getEmMsgListener());
        EMClient.getInstance().addConnectionListener(getP().getD().getChatConnectListener());

        SPUtil.getInstance().saveBoolean(Value.autologin, true);
    }


    @Override
    public void onBackPressed() {
        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }


        if (FragmentUtil2.getInstance().getFragMap().get(Value.FULLSCREEN) != null
                && FragmentUtil2.getInstance().getFragMap().get(Value.FULLSCREEN).size() >= 1
                && FragmentUtil2.getInstance().getFragMap().get(Value.FULLSCREEN).get(FragmentUtil2.getInstance().getFragMap().get(Value.FULLSCREEN).size() - 1).getClass().getName().equals(ReceiptFrag.class.getName())) {
            FragmentUtil2.getInstance().removeTopRightNow(getActivity(), Value.FULLSCREEN);
            try {
                EMClient.getInstance().callManager().rejectCall();
            } catch (EMNoActiveCallException e) {
                e.printStackTrace();
            }
            FragmentUtil2.getInstance().removeTopRightNow(getActivity(), Value.FULLSCREEN);

            return;
        }

        if (FragmentUtil2.getInstance().getFragMap().get(Value.FULLSCREEN) != null
                && FragmentUtil2.getInstance().getFragMap().get(Value.FULLSCREEN).size() >= 1
                && FragmentUtil2.getInstance().getFragMap().get(Value.FULLSCREEN).get(FragmentUtil2.getInstance().getFragMap().get(Value.FULLSCREEN).size() - 1).getClass().getName().equals(VideoChatFrag.class.getName())) {
            try {
                EMClient.getInstance().callManager().endCall();
            } catch (EMNoActiveCallException e) {
                e.printStackTrace();
            }

            return;
        }


        if (FragmentUtil2.getInstance().getFragMap().get(Value.FULLSCREEN) != null && FragmentUtil2.getInstance().getFragMap().get(Value.FULLSCREEN).size() == 1) {
            FragmentUtil2.getInstance().removeTopRightNow(getActivity(), Value.FULLSCREEN);
            return;
        }

        if (FragmentUtil2.getInstance().getFragMap().get(Value.getNowRoot()).size() == 1) {
            FragmentUtil2.getInstance().clear();
            //ToastUtil.getInstance().showShort(activity, "请从设置里退出");
            this.finish();
        } else {
            FragmentUtil2.getInstance().removeTopRightNow(getActivity(), Value.getNowRoot());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unregisterReceiver(loginInfoBroadCast);
        unregisterReceiver(callReceiver);
        if (Value.getRoom() == null) {
            return;
        }
        EMClient.getInstance().callManager().removeCallStateChangeListener(getP().getD().getVideoChatListener());
        EMClient.getInstance().chatManager().removeMessageListener(getP().getD().getEmMsgListener());
        EMClient.getInstance().removeConnectionListener(getP().getD().getChatConnectListener());
        EMClient.getInstance().chatroomManager().leaveChatRoom(Value.getRoom().getId());
    }


    @Optional
    @OnClick({R.id.ftv_back, R.id.ftv_title, R.id.ftv_right, R.id.ftv_right2})
    public void onClickEventListener(View v) {
        if (onTitleClick != null) {
            boolean b = onTitleClick.onTitleClick(v);
            if (b) {
                return;
            }
        }
        switch (v.getId()) {
            case R.id.ftv_right:
                break;
            case R.id.ftv_title:
                break;
            case R.id.ftv_back:
                FragmentUtil2.getInstance().removeTopRightNow(getActivity(), Value.getNowRoot());
                break;
            case R.id.ftv_right2:
                break;
        }
    }

    @Override
    public void onFinish(Object o) {
        if (Value.getRoom() != null) {
            EMClient.getInstance().chatroomManager().leaveChatRoom(Value.getRoom().getId());
            EMClient.getInstance().logout(true);
        }

        final CrashBean crashBean = new CrashBean();
        crashBean.setError((String) o);
        crashBean.setCreatedtime(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        crashBean.setUserBean(Value.getUserInfo());
        NetDataOpe.Crash.sendCrash(this,crashBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ((ServieApp) getActivity().getApplication()).exit();
            }
        });
    }

    public void setOnTitleClick(MainAct.OnTitleClick onTitleClick) {
        this.onTitleClick = onTitleClick;
    }

    class LoginInfoBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            getP().getD().getLoginInfo(MainAct.this,new OnFinishListener() {
                @Override
                public void onFinish(Object o) {
                    UserBean userBean = (UserBean) o;
                    if (!UUUIDUtil.getInstance().getUUUId(getActivity()).equals(userBean.getUuuid())) {
                        getActivity().finish();
                    }

                }
            });
        }
    }


    public interface OnTitleClick {
        public boolean onTitleClick(View v);
    }

    @SuppressLint("NewApi")
    private void requestReadExternalPermission() {
        if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Log.d("TAG", "READ permission IS NOT granted...");
            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                Log.d("TAG", "11111111111111");
            } else {
                // 0 是自己定义的请求coude
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                Log.d("TAG", "222222222222");
            }
        } else {
            Log.d("TAG", "READ permission is granted...");
        }

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
        if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.RECORD_AUDIO}, 2);
        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        Log.d("TAG", "requestCode=" + requestCode + "; --->" + permissions.toString() + "; grantResult=" + grantResults.toString());
        switch (requestCode) {
            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted
                    // request successfully, handle you transactions
                } else {
                    // permission denied
                    // request failed
                }
                return;
            }
            default:
                break;

        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        final CrashBean crashBean = new CrashBean();
        crashBean.setError("onLowMemory:" + ":" + FragmentUtil2.getInstance().print());
        crashBean.setCreatedtime(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        crashBean.setUserBean(Value.getUserInfo());
        NetDataOpe.Crash.sendCrash(this,crashBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
//                EMClient.getInstance().logout(true);
//                if (Value.getRoom() != null) {
//                    EMClient.getInstance().chatroomManager().leaveChatRoom(Value.getRoom().getId());
//                }
//                FragmentUtil2.getInstance().clear();
//                ((ServieApp) activity.getApplication()).exit();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        LogUtil.E("finish");
    }

    @Override
    public void onStart() {
        super.onStart();
        refix();
    }

    public void refix() {
        if (!EMClient.getInstance().isConnected()) {
            reconnect();
        }
    }

    final static String tag = "em";

    private void reconnect() {
        //LoadUtil.getInstance().onStartLoading(this, url);
        EMClient.getInstance().login(Value.getUserInfo().getPhone(), "111111", new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                //LoadUtil.getInstance().onStopLoading(url);
            }

            @Override
            public void onError(int code, String error) {
                //LoadUtil.getInstance().onStopLoading(url);
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {

            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                AudioUtil.setAudioJia(getActivity());
                return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                AudioUtil.setAudioJian(getActivity());
                return true;
                default:
                    break;
        }
        return super.onKeyDown(keyCode,event);
    }
}

