package com.siweisoft.service.ui.video.videoplay;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.NullUtil;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.collection.CollectionBean;
import com.siweisoft.service.data.netd.share.ShareBean;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.data.netd.video.VideoBean;
import com.siweisoft.service.data.netd.videodetail.VideoDetailBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

import java.io.File;

public class VideoPlayDAOpe extends BaseDAOpe {

    VideoBean videoBean;

    UserInfoDAOpe userInfoDAOpe;


    UserBean userBean;



    private int type = 0;

    private VideoDetailBean videoDetailBean;

    CollectionBean collectionBean = new CollectionBean();


    @Override
    public void initDA() {
        super.initDA();
        userInfoDAOpe = new UserInfoDAOpe();
        userBean = new UserBean();
    }

    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
    }

    public UserInfoDAOpe getUserInfoDAOpe() {
        return userInfoDAOpe;
    }

    public void getComment(Context context,VideoBean videoBean, OnFinishListener onFinishListener) {
        videoBean.setToUser(Value.getUserInfo());
        NetDataOpe.Comment.getVideoCommentByVideoIdAndCommentId(context,videoBean, onFinishListener);
    }

    public void isCollectedByVideoIdAndUserId(Context context,VideoBean videoBean, OnFinishListener onFinishListener) {
        CollectionBean collectionBean = new CollectionBean();
        collectionBean.setUserid(Value.getUserInfo().getId());
        collectionBean.setVideoid(videoBean.getId());
        NetDataOpe.Collection.isCollectedByVideoIdAndUserId(context,collectionBean, onFinishListener);
    }

    public void collect(Context context,CollectionBean collectionBean, OnFinishListener onFinishListener) {
        if (Value.getUserInfo().getId() == videoBean.getFromUser().getId()) {
            collectionBean.setUserid(videoBean.getFromUser().getId());
        } else {
            collectionBean.setUserid(videoBean.getToUser().getId());
        }
        NetDataOpe.Collection.collect(context,collectionBean, onFinishListener);
    }

    public void disCollect(Context context,CollectionBean collectionBean, OnFinishListener onFinishListener) {
        if (Value.getUserInfo().getId() == videoBean.getFromUser().getId()) {
            collectionBean.setUserid(videoBean.getFromUser().getId());
        } else {
            collectionBean.setUserid(videoBean.getToUser().getId());
        }
        NetDataOpe.Collection.disCollect(context,collectionBean, onFinishListener);
    }

    public String getVideoComment(VideoDetailBean videoDetailBean, VideoBean videoBean) {
        for (int i = 0; videoBean.getVideoCommentBeans() != null && i < videoBean.getVideoCommentBeans().size(); i++) {
            if (videoDetailBean.getUserid() == videoBean.getVideoCommentBeans().get(i).getUserid()) {
                return videoBean.getVideoCommentBeans().get(i).getTxt();
            }
        }
        return "";
    }


    public void uploadVideo(final Context context, final VideoBean videoBean, final VideoDetailBean vv, final OnFinishListener onFinishListener, final OnFinishListener onFinishListener2) {
        final String f = videoBean.getFile();
        if (NullUtil.isStrEmpty(videoBean.getFile())) {
            return;
        }
        final String[] ss = videoBean.getFile().split("/");
        File file = new File(Value.getCacheFile(), ss[ss.length - 1]);
        videoBean.setFile(file.getPath());
        if (!file.exists()) {
            BaseResBean o = new BaseResBean();
            o.setException(true);
            o.setErrorMessage("文件不存在");
            onFinishListener.onFinish(o);
            return;
        }
        NetDataOpe.Video.updateVideo(context,videoBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                videoBean.setFile(f);
                NetDataOpe.VideoDetail.updateUpload(context,vv, onFinishListener);
            }
        }, onFinishListener2);
    }


    public void isCommentToCustomer(Context context,VideoDetailBean vv, final OnFinishListener onFinishListener) {
        NetDataOpe.VideoDetail.getCommentToType(context,vv, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                UserBean u = (UserBean) o;
                if (u != null && u.getUsertype() == UserBean.CUSTOME) {
                    onFinishListener.onFinish(true);
                } else {
                    onFinishListener.onFinish(false);
                }
            }
        });
    }

    public void share(Context context,ShareBean shareBean, OnFinishListener onFinishListener) {
        NetDataOpe.Share.share(context,shareBean, onFinishListener);
    }


    public void downloadFile(VideoDetailBean videoDetailBean, NetWork.MyFileDownloadCallBack callBack) {

        NetWork.download(videoDetailBean.getUrl(), VideoPlayDAOpe.getLoadFile(videoDetailBean), callBack);

    }

    public static String getLoadFile(VideoDetailBean videoDetailBean) {
        String[] ss = videoDetailBean.getUrl().split("/");
        if (ss.length == 0) {
            return "";
        }
        File file = new File(Value.getCacheFile(), ss[ss.length - 1]);
        return file.getPath();
    }


    public VideoDetailBean getVideoDetailBean() {
        return videoDetailBean;
    }

    public void setVideoDetailBean(VideoDetailBean videoDetailBean) {
        this.videoDetailBean = videoDetailBean;
    }

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CollectionBean getCollectionBean() {
        return collectionBean;
    }


}
