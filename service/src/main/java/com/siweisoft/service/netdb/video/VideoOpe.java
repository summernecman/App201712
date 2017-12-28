package com.siweisoft.service.netdb.video;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.network.netadapter.DelayUINetAdapter;
import com.android.lib.network.netadapter.UINetAdapter;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.bean.FileBean;
import com.android.lib.bean.FilesBean;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.google.gson.reflect.TypeToken;
import com.siweisoft.service.bean.ContactBean;
import com.siweisoft.service.bean.HistoryBean;
import com.siweisoft.service.netdb.NetValue;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.user.UserBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class VideoOpe extends BaseDAOpe implements VideoI {


    public VideoOpe(Context context) {
        super(context);
    }

    @Override
    public void getVideos(UserBean userBean, OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getVideos"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                //UserBean res =GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()),UserBean.class);
                //onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void addVideo(VideoBean videoBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/addVideo"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                VideoBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), VideoBean.class);
                onFinishListener.onFinish(res);
            }
        });
    }

    @Override
    public void getHistoryVideos(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getHistoryVideos"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o.getData()));
                List<VideoBean> retList = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<List<VideoBean>>() {
                }.getType());
                onFinishListener.onFinish(retList);
            }
        });
    }

    @Override
    public void getVideosByContacts(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getVideosByContacts"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o.getData()));
                ArrayList<ArrayList<VideoBean>> retList = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<ArrayList<VideoBean>>>() {
                }.getType());
                onFinishListener.onFinish(retList);
            }
        });
    }

    @Override
    public void getVideosByContacts2(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getVideosByContacts2"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o.getData()));
                ArrayList<ArrayList<VideoBean>> retList = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<ArrayList<VideoBean>>>() {
                }.getType());
                onFinishListener.onFinish(retList);
            }
        });
    }

    @Override
    public void commentVideo(CommentBean commentBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(commentBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/commentVideos"), baseReqBean, new UINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o.getData()));
                onFinishListener.onFinish(o);
            }
        });
    }

    @Override
    public void updateVideo(VideoBean videoBean, final OnFinishListener onFinishListener, final OnFinishListener onFinishListener2) {


        FilesBean filesBean = new FilesBean();
        ArrayList<FileBean> fileBeen = new ArrayList<>();
        fileBeen.add(new FileBean(new File(videoBean.getFile())));
        filesBean.setData(fileBeen);

        NetWork.doHttpRequsetWithFileProgress(context, NetValue.获取地址("/server/uploadvideo"), filesBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean success, BaseResBean o) {
                LogUtil.E(o);
                ArrayList<String> files = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<String>>() {
                }.getType());
                onFinishListener.onFinish(files);
            }

            @Override
            public void onNetWorkProgress(long total, long current) {
                super.onNetWorkProgress(total, current);
                onFinishListener2.onFinish(current * 100 / total);
            }
        });
    }

    @Override
    public void updateVideoById(VideoBean videoBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/updateVideoById"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o.getData()));
                onFinishListener.onFinish(o);
            }
        });
    }

    @Override
    public void isVideoUploaded(VideoBean videoBean, OnFinishListener onFinishListener) {

    }

    @Override
    public void setVideoUploaded(VideoBean videoBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/setVideoUploaded"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o.getData()));
                onFinishListener.onFinish(o);
            }
        });
    }

    @Override
    public void getMaxVideoId(final OnFinishListener onFinishListener) {
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getMaxVideoId"), new BaseReqBean(), new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                onFinishListener.onFinish(o.getData());
            }
        });
    }

    @Override
    public void insert_and_getid_fromvieo(final VideoBean videoBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/insert_and_getid_fromvieo"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                LogUtil.E(GsonUtil.getInstance().toJson(o.getData()));
                VideoBean videoBean1 = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), VideoBean.class);
                onFinishListener.onFinish(videoBean1);
            }
        });
    }

    @Override
    public void getVideosByBothUserId(ContactBean contactBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(contactBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getVideosByBothUserId"), baseReqBean, new UINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<VideoBean> videos = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<VideoBean>>() {
                }.getType());
                onFinishListener.onFinish(videos);
            }
        });
    }

    @Override
    public void getVideosByBothUserIdWithLimit(ContactBean contactBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(contactBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getVideosByBothUserIdWithLimit"), baseReqBean, new DelayUINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<VideoBean> videos = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<VideoBean>>() {
                }.getType());
                onFinishListener.onFinish(videos);
            }
        });
    }

    @Override
    public void getVideosByBothUserIdWithLimitAndSeach(ContactBean contactBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(contactBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getVideosByBothUserIdWithLimitAndSeach"), baseReqBean, new DelayUINetAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<VideoBean> videos = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<VideoBean>>() {
                }.getType());
                onFinishListener.onFinish(videos);
            }
        });
    }

    @Override
    public void getByContacts(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getMyContactsById"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                ArrayList<HistoryBean> videos = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<HistoryBean>>() {
                }.getType());
                onFinishListener.onFinish(videos);
            }
        });
    }

    @Override
    public void getUnUploadVideoNum(UserBean userBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getUnUploadVideoNum"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                double d = (double) o.getData();
                int num = (int) d;
                onFinishListener.onFinish(num);
            }
        });
    }

    @Override
    public void updateCallState(VideoBean videoBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/updateCallState"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                if (onFinishListener != null) {
                    onFinishListener.onFinish(o);
                }
            }
        });
    }

    @Override
    public void updateVideoCallTimeNum(VideoBean videoBean, final OnFinishListener onFinishListener) {
        BaseReqBean baseReqBean = new BaseReqBean();
        baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
        NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/updateVideoCallTimeNum"), baseReqBean, new OnNetWorkReqAdapter(context) {
            @Override
            public void onNetWorkResult(boolean b, BaseResBean o) {
                if (onFinishListener != null) {
                    onFinishListener.onFinish(o);
                }
            }
        });
    }
}
