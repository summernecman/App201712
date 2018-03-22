package com.siweisoft.service.ui.video.videocontainer;

//by summer on 2017-11-08.

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.constant.ValueConstant;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.collection.CollectionBean;
import com.siweisoft.service.data.netd.share.ShareBean;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.data.netd.video.VideoBean;
import com.siweisoft.service.data.netd.videodetail.VideoDetailBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.video.videoplay.VideoPlayFrag;

import java.util.ArrayList;

public class VideoContainerDAOpe extends BaseDAOpe {


    private int type = 0;

    VideoBean videoBean;


    UserBean userBean;



    CollectionBean collectionBean = new CollectionBean();


    public ArrayList<Fragment> getVideosPager(VideoBean videoBean, int type) {
        ArrayList<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < videoBean.getVideodetail().size(); i++) {
            VideoBean vto = videoBean.getSame();
            VideoPlayFrag playFrag = new VideoPlayFrag();
            playFrag.setArguments(new Bundle());
            playFrag.getArguments().putSerializable(ValueConstant.DATA_DATA, vto);
            playFrag.getArguments().putSerializable(ValueConstant.DATA_DATA2, vto.getVideodetail().get(i));
            playFrag.getArguments().putInt(ValueConstant.DATA_TYPE, type);
            fragments.add(playFrag);
        }
        return fragments;
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
        for (int i = 0; i < videoBean.getVideoCommentBeans().size(); i++) {
            if (videoDetailBean.getUserid() == videoBean.getVideoCommentBeans().get(i).getUserid()) {
                return videoBean.getVideoCommentBeans().get(i).getTxt();
            }
        }
        return "";
    }

    public void share(Context context,ShareBean shareBean, OnFinishListener onFinishListener) {
        NetDataOpe.Share.share(context,shareBean, onFinishListener);
    }

    public void isCollectedByVideoIdAndUserId(Context context,VideoBean videoBean, OnFinishListener onFinishListener) {
        CollectionBean collectionBean = new CollectionBean();
        collectionBean.setUserid(Value.getUserInfo().getId());
        collectionBean.setVideoid(videoBean.getId());
        NetDataOpe.Collection.isCollectedByVideoIdAndUserId(context,collectionBean, onFinishListener);
    }


    public VideoBean getVideoBean() {
        return videoBean;
    }

    public void setVideoBean(VideoBean videoBean) {
        this.videoBean = videoBean;
        getCollectionBean().setVideoid(getVideoBean().getId());
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
