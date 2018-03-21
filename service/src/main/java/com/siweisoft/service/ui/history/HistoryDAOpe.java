package com.siweisoft.service.ui.history;

//by summer on 17-08-25.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.siweisoft.service.netdb.NetDataOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.video.VideoBean;
import com.siweisoft.service.ui.Constant.Value;

import java.util.ArrayList;

public class HistoryDAOpe extends BaseDAOpe {




    public ArrayList<VideoBean> getData() {
        ArrayList<VideoBean> data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            VideoBean videoBean = new VideoBean();
            videoBean.setFromphone("张三");
            videoBean.setTophone("3次");
            videoBean.setCreated("2014:4:23");
            data.add(videoBean);
        }
        return data;
    }

    public void getVideos(Context context,final OnFinishListener onFinishListener) {
        NetDataOpe.Video.getByContacts(context,Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                onFinishListener.onFinish(o);
            }
        });
    }

    public void getUnUploadVideoNum(Context context,final OnFinishListener onFinishListener) {
        NetDataOpe.Video.getUnUploadVideoNum(context,Value.getUserInfo(), onFinishListener);
    }

    public void getArrayUsersInfoByPhone(Context context,ArrayList<ArrayList<UserBean>> data, OnFinishListener onFinishListener) {
        NetDataOpe.User.getArrayUsersInfoByPhone(context,data, onFinishListener);
    }
}
