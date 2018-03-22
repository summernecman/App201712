package com.siweisoft.service.data.netd;

//by summer on 2018-03-20.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.bean.FileBean;
import com.android.lib.bean.FilesBean;
import com.android.lib.network.NetWork;
import com.android.lib.network.bean.req.BaseReqBean;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.netadapter.DelayUINetAdapter;
import com.android.lib.network.netadapter.OnNetWorkReqAdapter;
import com.android.lib.network.netadapter.UINetAdapter;
import com.android.lib.network.news.NetGet;
import com.android.lib.network.news.NetI;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LogUtil;
import com.google.gson.reflect.TypeToken;
import com.siweisoft.service.bean.AllUserBean;
import com.siweisoft.service.bean.ContactBean;
import com.siweisoft.service.bean.HistoryBean;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.data.netd.agree.AgreeBean;
import com.siweisoft.service.data.netd.agree.AgreeNumBean;
import com.siweisoft.service.data.netd.app.AppBean;
import com.siweisoft.service.data.netd.collection.CollectionBean;
import com.siweisoft.service.data.netd.comment.CommentBean;
import com.siweisoft.service.data.netd.crash.CrashBean;
import com.siweisoft.service.data.netd.feedback.FeedBackBean;
import com.siweisoft.service.data.netd.share.ShareBean;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.data.netd.video.VideoBean;
import com.siweisoft.service.data.netd.video.VideoTimeBean;
import com.siweisoft.service.data.netd.videocomment.VideoCommentBean;
import com.siweisoft.service.data.netd.videodetail.VideoDetailBean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NetDataOpe {

    
    public static class Agree{
        public static void addAgree(Context context, AgreeBean agree, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(agree));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/agree/addAgree"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    AgreeBean bean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), AgreeBean.class);
                    onFinishListener.onFinish(bean);
                }
            });
        }

        public static void cancleAgree(Context context,AgreeBean agree, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(agree));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/agree/cancleAgree"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    AgreeBean bean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), AgreeBean.class);
                    onFinishListener.onFinish(bean);
                }
            });
        }

        public static void getAgreeNum(Context context,AgreeBean agree, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(agree));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/agree/getAgreeNum"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    long l = (long) o.getData();
                    int res = (int) l;
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void isAgreeNum(Context context,AgreeBean agree, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(agree));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/agree/isAgreeNum"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    onFinishListener.onFinish((boolean) o.getData());
                }
            });
        }

        public static void clickAgree(Context context,AgreeBean agree, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(agree));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/agree/clickAgree"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    AgreeNumBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), AgreeNumBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }
    }
    
    public static class App{
        
        public static void CheckVersion(Context context,final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/app/getAppVersion"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    AppBean bean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), AppBean.class);
                    onFinishListener.onFinish(bean);
                }
            });
        }
    }
    
    
    public static class Collection{
        
        
        public static void getCollectionVideosByUserId(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/collection/getCollectionVideosByUserId"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<VideoBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<VideoBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getCollectionVideosByUserIdWithLimit(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/collection/getCollectionVideosByUserIdWithLimit"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<VideoBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<VideoBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void isCollectedByVideoIdAndUserId(Context context,final CollectionBean collectionBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(collectionBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/collection/isCollectedByVideoIdAndUserId"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    if (o.getData() == null) {
                        onFinishListener.onFinish(o.getData());
                    } else {
                        CollectionBean collectionBean1 = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), CollectionBean.class);
                        onFinishListener.onFinish(collectionBean1);
                    }


                }
            });
        }

        public static void getCollectionNumByUserId(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/collection/getCollectionNumByUserId"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    double res = (double) o.getData();
                    int r = (int) res;
                    onFinishListener.onFinish(r + "");
                }
            });
        }


        public static void collect(Context context,CollectionBean collectionBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(collectionBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/collection/collect"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    if (!b) {
                        onFinishListener.onFinish(b);
                        return;
                    }
                    CollectionBean collectionBean1 = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), CollectionBean.class);
                    onFinishListener.onFinish(collectionBean1);
                }
            });
        }

        public static void disCollect(Context context,CollectionBean collectionBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(collectionBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/collection/discollect"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    onFinishListener.onFinish(b);
                }
            });
        }
    }
    
    public static class Comment{
        public static void addComment(Context context,CommentBean commentBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(commentBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/commentVideos"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    CommentBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), CommentBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getCommentByUserPhone(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getCommentByUserPhone"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<CommentBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<CommentBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getCommentByUserIdWithLimit(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getCommentByUserIdWithLimit"), baseReqBean, new UINetAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<CommentBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<CommentBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getOtherCommentByUserIdWithLimit(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getOtherCommentByUserIdWithLimit"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<CommentBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<CommentBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getCommentByUserIdWithMyOptionWithLimit(Context context,CommentBean commentBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(commentBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getCommentByUserIdWithMyOptionWithLimit"), baseReqBean, new UINetAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<CommentBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<CommentBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getCommentByUserNameWithMyOption(Context context,CommentBean commentBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(commentBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getCommentByUserNameWithMyOption"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<CommentBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<CommentBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getCommentNumByUserName(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getCommentNumByUserName"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    double res = (double) o.getData();
                    int r = (int) res;
                    onFinishListener.onFinish(r + "");
                }
            });
        }

        public static void getUserTips(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getUserTips"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    HashMap<Integer, TipBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<HashMap<Integer, TipBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });

        }

        public static void getVideoCommentByVideoName(Context context,VideoBean videoBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getVideoCommentByVideoName"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<CommentBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<CommentBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getVideoCommentByVideoNameAndFrom(Context context,VideoBean videoBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getVideoCommentByVideoNameAndFrom"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<CommentBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<CommentBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getVideoRateCommentByUseId(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getVideoRateCommentByUseId"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    double l = (double) o.getData();
                    float res = (float) l;
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getVideoRateCommentByVideoid(Context context,VideoBean videoBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getVideoRateCommentByVideoid"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    double l = (double) o.getData();
                    float res = (float) l;
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getVideoCommentByVideoIdAndFrom(Context context,VideoBean videoBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getVideoCommentByVideoIdAndFrom"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<CommentBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<CommentBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getVideoCommentByVideoIdAndCommentId(Context context,VideoBean videoBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(videoBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/comment/getVideoCommentByVideoIdAndCommentId"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<CommentBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<CommentBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }
    }
    
    
    public static class Crash{
        public static void sendCrash(Context context,CrashBean crashBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(crashBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/crash/sendCrash"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    CrashBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), CrashBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }
    }
    
    public static class FeedBack{
        
        public static void sendFeedBack(Context context,FeedBackBean feedBackBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(feedBackBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/feedback/sendfeedback"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    FeedBackBean videos = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), FeedBackBean.class);
                    onFinishListener.onFinish(videos);
                }
            });
        }
    }
    
    public static class Share{
        public static void share(Context context,final ShareBean shareBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(shareBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/share/share"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ShareBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), ShareBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getShareNumByUserPhone(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/share/getShareNumByUserPhone"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    double r = (double) o.getData();
                    int res = (int) r;
                    onFinishListener.onFinish(res + "");
                }
            });
        }

        public static void getSharesByReceipt(Context context,ShareBean shareBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(shareBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/share/getSharesByReceipt"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<VideoBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<VideoBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getSharesByReceiptWithLimit(Context context,ShareBean shareBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(shareBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/share/getSharesByReceiptWithLimit"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<VideoBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<VideoBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }
    }
    
    public static class Tip{
        public static void getTips(Context context,final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/tip/gettips"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<TipBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<TipBean>>() {
                    }.getType());
                    TipsBean tipsBean = new TipsBean();
                    tipsBean.setTipBeen(res);
                    onFinishListener.onFinish(tipsBean);
                }
            });
        }
    }
    
    public static class User{

        public static void login(Context context, UserBean userBean, NetI<UserBean> adapter) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetGet.postData(context,NetValue.获取地址("/server/login"),baseReqBean,adapter);
        }
        
        
        public static void registed(Context context,String username, OnFinishListener onFinishListener) {

        }

        public static void regist(Context context,final UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/registEMAccount"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    if (!o.isException()) {
                        UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                        onFinishListener.onFinish(b);
                    } else {
                        onFinishListener.onFinish(o.getErrorMessage());
                    }
                }
            });
        }

        public static void resetPwd(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/resetPwd"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    if (!o.isException()) {
                        UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                        onFinishListener.onFinish(b);
                    } else {
                        onFinishListener.onFinish(o.getErrorMessage());
                    }
                }
            });
        }


        public static void getUserList(Context context,final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(new UserBean()));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/getUserList"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getUnTypeUserList(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/getUnTypeUserList"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getUserListWithOutMe(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/getUserListWithOutMe"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }


        public static void getLoginInfo(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getLoginInfo"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void loginOut(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/loginOut"), baseReqBean, new UINetAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void setHeadUrl(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/setHeadurl"), baseReqBean, new UINetAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void setName(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/setUserName"), baseReqBean, new UINetAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getUserCallInfo(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/getusercallinfo"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    VideoTimeBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), VideoTimeBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getUserInfoByPhone(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/getUserInfoByPhone"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getUserInfoById(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/getUserInfoById"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getUsersInfoByPhone(Context context,ArrayList<UserBean> userBeen, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBeen));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/getUsersInfoByPhone"), baseReqBean, new UINetAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getArrayUsersInfoByPhone(Context context,ArrayList<ArrayList<UserBean>> userBeen, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBeen));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/getArrayUsersInfoByPhone"), baseReqBean, new UINetAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<ArrayList<UserBean>> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<ArrayList<UserBean>>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getOtherUsersInfoByPhone(Context context,AllUserBean allUserBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(allUserBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/getOtherUsersInfoByPhone"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {
                    }.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void getUserContactsByUserIdAndType(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/getUserContactsByUserIdAndType"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    ArrayList<UserBean> res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), new TypeToken<ArrayList<UserBean>>() {}.getType());
                    onFinishListener.onFinish(res);
                }
            });
        }

        public static void updateUnit(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(userBean));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/user/updateUnit"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    UserBean res = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                    onFinishListener.onFinish(res);
                }
            });
        }
    }
    
    public static class Video{
        
        public static void getVideos(Context context,UserBean userBean, OnFinishListener onFinishListener) {
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

        public static void addVideo(Context context,VideoBean videoBean, final OnFinishListener onFinishListener) {
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

        public static void getHistoryVideos(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
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

        public static void getVideosByContacts(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
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

        public static void getVideosByContacts2(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
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

        public static void commentVideo(Context context,CommentBean commentBean, final OnFinishListener onFinishListener) {
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

        public static void updateVideo(Context context,VideoBean videoBean, final OnFinishListener onFinishListener, final OnFinishListener onFinishListener2) {


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

        public static void updateVideoById(Context context,VideoBean videoBean, final OnFinishListener onFinishListener) {
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

        public static void isVideoUploaded(Context context,VideoBean videoBean, OnFinishListener onFinishListener) {

        }

        public static void setVideoUploaded(Context context,VideoBean videoBean, final OnFinishListener onFinishListener) {
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

        public static void getMaxVideoId(Context context,final OnFinishListener onFinishListener) {
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/server/getMaxVideoId"), new BaseReqBean(), new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    onFinishListener.onFinish(o.getData());
                }
            });
        }

        public static void insert_and_getid_fromvieo(Context context,final VideoBean videoBean, final OnFinishListener onFinishListener) {
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

        public static void getVideosByBothUserId(Context context,ContactBean contactBean, final OnFinishListener onFinishListener) {
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

        public static void getVideosByBothUserIdWithLimit(Context context,ContactBean contactBean, final OnFinishListener onFinishListener) {
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

        public static void getVideosByBothUserIdWithLimitAndSeach(Context context,ContactBean contactBean, final OnFinishListener onFinishListener) {
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

        public static void getByContacts(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
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

        public static void getUnUploadVideoNum(Context context,UserBean userBean, final OnFinishListener onFinishListener) {
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

        public static void updateCallState(Context context,VideoBean videoBean, final OnFinishListener onFinishListener) {
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

        public static void updateVideoCallTimeNum(Context context,VideoBean videoBean, final OnFinishListener onFinishListener) {
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
    
    public static class VideoComment{
        
        
        public static void addVideoComment(Context context,VideoCommentBean v, final OnFinishListener listener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(v));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/videocomment/addVideoComment"), baseReqBean, new UINetAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    if (listener != null) {
                        listener.onFinish(o);
                    }
                }
            });
        }

        public static void getVideoCommentByCallId(Context context,VideoBean v, OnFinishListener listener) {

        }

        public static void getVideoCommentByType(Context context,VideoCommentBean v, OnFinishListener listener) {

        }

        public static void getVideoCommentByTxt(Context context,VideoCommentBean v, OnFinishListener listener) {

        }
    }
    
    public static class VideoDetail{
        public static void insertVideo(Context context,VideoDetailBean v, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(v));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/videodetail/insertvideo"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    if (onFinishListener != null) {
                        onFinishListener.onFinish(o);
                    }

                }
            });
        }

        public static void updateUpload(Context context,VideoDetailBean v, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(v));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/videodetail/updateUpload"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    if (onFinishListener != null) {
                        onFinishListener.onFinish(o);
                    }
                }
            });
        }

        public static void getCommentToType(Context context,VideoDetailBean v, final OnFinishListener onFinishListener) {
            BaseReqBean baseReqBean = new BaseReqBean();
            baseReqBean.setData(GsonUtil.getInstance().toJson(v));
            NetWork.doHttpRequsetWithSession(context, NetValue.获取地址("/videodetail/getCommentToType"), baseReqBean, new OnNetWorkReqAdapter(context) {
                @Override
                public void onNetWorkResult(boolean b, BaseResBean o) {
                    if (onFinishListener != null) {
                        UserBean userBean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(o.getData()), UserBean.class);
                        onFinishListener.onFinish(userBean);
                    }
                }
            });
        }
    }

}
