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




    @Override
    public void initDA(Context context) {
        super.initDA(context);
    }

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

    public void getVideos(final OnFinishListener onFinishListener) {
        NetDataOpe.Video.getByContacts(getActivity(),Value.getUserInfo(), new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                onFinishListener.onFinish(o);
            }
        });
    }

    public void getUnUploadVideoNum(final OnFinishListener onFinishListener) {
        NetDataOpe.Video.getUnUploadVideoNum(getActivity(),Value.getUserInfo(), onFinishListener);
    }

    public void getArrayUsersInfoByPhone(ArrayList<ArrayList<UserBean>> data, OnFinishListener onFinishListener) {
        NetDataOpe.User.getArrayUsersInfoByPhone(getActivity(),data, onFinishListener);
    }
}
