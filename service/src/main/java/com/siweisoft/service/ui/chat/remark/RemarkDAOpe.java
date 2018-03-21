package com.siweisoft.service.ui.chat.remark;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.UrlConstant;
import com.android.lib.util.LogUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.netdb.NetDataOpe;
import com.siweisoft.service.netdb.NetValue;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.netdb.videocomment.VideoCommentBean;
import com.siweisoft.service.netdb.videodetail.VideoDetailBean;
import com.siweisoft.service.netdb.videotip.VideoTipBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

import java.io.File;
import java.util.ArrayList;

public class RemarkDAOpe extends BaseDAOpe {

    UserInfoDAOpe userInfoDAOpe= new UserInfoDAOpe();

    VideoBean videoBean;


    private float ratingbar = 5f;

    TipsBean tipsBean;


    private VideoTipBean videoTipBean;



    public UserInfoDAOpe getUserInfoDAOpe() {
        return userInfoDAOpe;
    }

    public void setUserInfoDAOpe(UserInfoDAOpe userInfoDAOpe) {
        this.userInfoDAOpe = userInfoDAOpe;
    }

    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }

    public TipsBean getTipsBean() {
        return tipsBean;
    }

    public void setTipsBean(TipsBean tipsBean) {
        this.tipsBean = tipsBean;
    }

    public void getChatUserInfo(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        NetDataOpe.User.getUserInfoByPhone(context,userBean, onFinishListener);
    }

    public void getTips(Context context,OnFinishListener onFinishListener) {
        NetDataOpe.Tip.getTips(context,onFinishListener);
    }

    //    public void updateVideo(final VideoBean videoBean, final OnFinishListener onFinishListener) {
//        if (videoI == null) {
//            videoI = new VideoOpe(context.getApplicationContext());
//        }
//        final String ff = videoBean.getFile();
//        File file = new File(ff);
//        String[] ss = file.getName().split("_");
//        LogUtil.E("file.getName()" + file.getName());
//        final String s = UrlConstant.fileUrl + "/" + ss[1] + "/" + file.getName();
//        videoBean.setFile(s);
//        videoBean.setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
//        videoI.addVideo(videoBean, new OnFinishListener() {
//            @Override
//            public void onFinish(Object o) {
//                videoBean.setFile(ff);
//                onFinishListener.onFinish(o);
//                EMChatOpe.sendCmdMsg(videoBean.getOtherUser().getPhone(), s);
//                videoI.updateVideo(videoBean, new OnFinishListener() {
//                    @Override
//                    public void onFinish(Object o) {
//                        ArrayList<String> strs = (ArrayList<String>) o;
//                        if (strs != null && strs.size() > 0) {
//                            VideoBean v = new VideoBean();
//                            v.setFile(s);
//                            videoI.setVideoUploaded(v, new OnFinishListener() {
//                                @Override
//                                public void onFinish(Object o) {
//
//                                }
//                            });
//                        }
//                    }
//                });
//            }
//        });
//    }


    public float getRatingbar() {
        return ratingbar;
    }

    public void setRatingbar(float ratingbar) {
        this.ratingbar = ratingbar;
    }

    public static void renameFile(VideoBean videoBean) {
        File file = new File(videoBean.getFile());
        if (file.exists()) {
            String s = (System.currentTimeMillis() + "");
            File f = new File(file.getParent(), DateFormatUtil.getNowStr(DateFormatUtil.YYYY__MM__DD__HH__MM__SS) + s.substring(s.length() - 3, s.length()) + "_" + videoBean.getFromUser().getId() + "to" + videoBean.getToUser().getId() + ".mov");
            file.renameTo(f);
            videoBean.setFile(f.getPath());
        }
    }

    public void updateVideoCallTimeNum(Context context,VideoBean videoBean) {
        NetDataOpe.Video.updateVideoCallTimeNum(context,videoBean, null);
    }

    public void insetVideo(Context context,final VideoBean videoBean, final OnFinishListener onFinishListener) {
        final String ff = videoBean.getFile();
        final VideoDetailBean v = new VideoDetailBean();
        v.setCallid(videoBean.getId());
        v.setUrl(getUrlFromLocal(videoBean.getFile()));
        v.setUploaded(0);
        v.setUserid(Value.getUserInfo().getId());
        v.setTime(videoBean.getTimenum());
        if (Value.getUserInfo().getPhone().equals(videoBean.getFromUser().getPhone())) {
            videoBean.setIsfrom(true);
        } else {
            videoBean.setIsfrom(false);
        }

        NetDataOpe.VideoDetail.insertVideo(context,v, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                videoBean.setFile(ff);
                onFinishListener.onFinish(v);
            }
        });
    }

    public void addVideoComment(Context context,VideoCommentBean v, OnFinishListener listener) {
        NetDataOpe.VideoComment.addVideoComment(context,v, listener);
    }

    public boolean isRecord() {
        return !NullUtil.isStrEmpty(getVideoBean().getFile());
    }



    public String getUrlFromLocal(String ff) {
        File file = new File(ff);
        String ss = file.getName().substring(0, "20170101".length());
        LogUtil.E("file.getName()" + file.getName());
        final String s = NetValue.获取文件路径("/" + ss + "/" + file.getName());
        return s;
    }

    public void updateVideo(Context context,final VideoBean videoBean, final OnFinishListener onFinishListener) {
        final String ff = videoBean.getFile();
        final String s = getUrlFromLocal(ff);
        videoBean.setFile(s);
        videoBean.setCreated(DateFormatUtil.getNowStr(DateFormatUtil.YYYY_MM_DD_HH_MM_SS));
        if (Value.getUserInfo().getPhone().equals(videoBean.getFromUser().getPhone())) {
            videoBean.setIsfrom(true);
        } else {
            videoBean.setIsfrom(false);
        }
        NetDataOpe.Video.updateVideoById(context,videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                videoBean.setFile(ff);
                onFinishListener.onFinish(videoBean);
            }
        });
    }

    public void uploadVideo(final Context context, VideoBean videoBean, final VideoDetailBean vv) {

        final String ff = videoBean.getFile();

        NetDataOpe.Video.updateVideo(context,videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<String> strs = (ArrayList<String>) o;
                if (strs != null && strs.size() > 0) {
                    NetDataOpe.VideoDetail.updateUpload(context,vv, null);

                }
            }
        }, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {

            }
        });
    }

    public UserBean getOtherUser(VideoBean videoBean) {
        if (Value.getUserInfo().getPhone().equals(videoBean.getFromUser().getPhone())) {
            return videoBean.getToUser();
        }
        return videoBean.getFromUser();
    }

    public VideoTipBean getVideoTipBean() {
        return videoTipBean;
    }

    public void setVideoTipBean(VideoTipBean videoTipBean) {
        this.videoTipBean = videoTipBean;
    }
}
