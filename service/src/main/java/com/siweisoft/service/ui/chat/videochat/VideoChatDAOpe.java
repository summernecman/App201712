package com.siweisoft.service.ui.chat.videochat;

//by summer on 17-09-14.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.android.lib.util.thread.ThreadUtil;
import com.siweisoft.service.netdb.NetDataOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;

public class VideoChatDAOpe extends BaseDAOpe {

    private VideoBean videoBean;

    private double start;

    private double end;


    private boolean accept = false;

    private ThreadUtil threadUtil = new ThreadUtil();

    private String path = "";

    private String voicestr = "";

    private String videostr = "";

    public int sw = 0;


    private boolean isRecordVideo = false;


    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }


    public static boolean isLocalSendVideo(UserBean local, UserBean remove) {
        switch (local.getUsertype()) {
            case UserBean.SERVER:
                return false;
            case UserBean.CUSTOME:
                return true;
            case UserBean.ENGINEER:
                if (remove.getUsertype() == UserBean.SERVER) {
                    return true;
                } else {
                    return false;
                }
        }
        return true;
    }

    public double getStart() {
        return start;
    }

    public void setStart(double start) {
        this.start = start;
    }

    public double getEnd() {
        return end;
    }

    public void setEnd(double end) {
        this.end = end;
    }

    public int getMinute() {
        LogUtil.E(end + "---" + start);
        return (int) ((end - start) / 1000);
    }

    public void updateVideo(final Context context, final VideoBean videoBean, final OnFinishListener onFinishListener) {
        NetDataOpe.Video.getMaxVideoId(context,new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                NetDataOpe.Video.addVideo(context,videoBean, new OnFinishListener() {
                    @Override
                    public void onFinish(Object o) {
                        onFinishListener.onFinish(o);
                    }
                });
            }
        });
    }

    public void insert_and_getid_fromvieo(Context context,final VideoBean videoBean, final OnFinishListener onFinishListener) {
        getVideoBean().setFile("");
        getVideoBean().setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        getVideoBean().setTimenum(getMinute());
        NetDataOpe.Video.insert_and_getid_fromvieo(context,videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                onFinishListener.onFinish(o);
            }
        });
    }


    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public ThreadUtil getThreadUtil() {
        return threadUtil;
    }

    public void setThreadUtil(ThreadUtil threadUtil) {
        this.threadUtil = threadUtil;
    }


    public void updateCallState(Context context,VideoBean v, int state) {
        v.setCallstate(state);
        NetDataOpe.Video.updateCallState(context,v, null);
    }

    public static boolean isFromUser(VideoBean videoBean) {
        if (Value.getUserInfo().getPhone().equals(videoBean.getFromUser().getPhone())) {
            return true;
        }
        return false;
    }


    /**
     * 发送视频信号方 非录制视频方   目前非发送视频信号方 将录制视频
     */
    public static boolean isSendVideo(VideoBean videoBean) {
        boolean isfrom = isFromUser(videoBean);
        if (isfrom) {
            switch (videoBean.getFromUser().getUsertype()) {
                case UserBean.SERVER:
                    return false;
                case UserBean.CUSTOME:
                    return true;
                case UserBean.ENGINEER:
                    if (videoBean.getToUser().getUsertype() == UserBean.SERVER) {
                        return true;
                    } else {
                        return false;
                    }
            }
        } else {
            switch (videoBean.getToUser().getUsertype()) {
                case UserBean.SERVER:
                    return false;
                case UserBean.CUSTOME:
                    return true;
                case UserBean.ENGINEER:
                    if (videoBean.getFromUser().getUsertype() == UserBean.SERVER) {
                        return true;
                    } else {
                        return false;
                    }
            }
        }
        return true;
    }

    public boolean isRecordVideo() {
        return true;
    }

    public void setRecordVideo(boolean recordVideo) {
        isRecordVideo = recordVideo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getVoicestr() {
        return voicestr;
    }

    public void setVoicestr(String voicestr) {
        this.voicestr = voicestr;
    }

    public String getVideostr() {
        return videostr;
    }

    public void setVideostr(String videostr) {
        this.videostr = videostr;
    }



}
