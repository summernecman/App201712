package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.bean.AllUserBean;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.data.netd.NetDataOpe;
import com.siweisoft.service.data.netd.agree.AgreeBean;
import com.siweisoft.service.data.netd.comment.CommentBean;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.onlinelist.OnLineListFrag;
import com.siweisoft.service.ui.user.usercenter.UserCenterDAOpe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDAOpe extends BaseDAOpe {

    UserBean userBean;


    UserCenterDAOpe userCenterDAOpe;

    ArrayList<CommentBean> commentBeen = new ArrayList<>();



    public TipsBean getData() {
        TipsBean tipsBean = new TipsBean();
        ArrayList<TipBean> tipBeen = new ArrayList<>();
        tipBeen.add(new TipBean(0, "业务熟练", 0));
        tipBeen.add(new TipBean(1, "态度好", 0));
        tipBeen.add(new TipBean(2, "专业", 0));
        tipBeen.add(new TipBean(3, "解决问题", 0));
        tipBeen.add(new TipBean(4, "未解决问题", 0));
        tipBeen.add(new TipBean(5, "态度差", 0));
        tipsBean.setTipBeen(tipBeen);
        return tipsBean;
    }


    public void getUserInfo(Context context, int id, OnFinishListener onFinishListener) {
        UserBean u = new UserBean();
        u.setId(id);
        NetDataOpe.User.getUserInfoById(context,u, onFinishListener);
    }

    public void getRemarks(Context context,CommentBean commentBean, final OnFinishListener onFinishListener) {

        NetDataOpe.Comment.getCommentByUserIdWithMyOptionWithLimit(context,commentBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<CommentBean> res = (ArrayList<CommentBean>) o;
                onFinishListener.onFinish(res);
            }
        });

    }

    public void getOtherUsersInfoByPhone(Context context,final UserBean u, List<String> strs, final OnFinishListener onFinishListener) {
        ArrayList<UserBean> userBeen = new ArrayList<>();
        for (int i = 0; i < strs.size(); i++) {
            UserBean userBean = new UserBean(strs.get(i));
            userBeen.add(userBean);
        }
        AllUserBean allUserBean = new AllUserBean();
        allUserBean.setOther(userBeen);
        allUserBean.setMe(Value.getUserInfo());
        NetDataOpe.User.getOtherUsersInfoByPhone(context,allUserBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<UserBean> list = (ArrayList<UserBean>) o;
                for (int i = 0; list != null && i < list.size(); i++) {
                    if (u.getPhone().equals(list.get(i).getPhone())) {
                        onFinishListener.onFinish(true);
                        return;
                    }
                }
                onFinishListener.onFinish(false);
            }
        });
    }


    public void downloadFile(String path, NetWork.MyFileDownloadCallBack callBack) {
        String[] ss = path.split("/");
        if (ss.length > 0) {
            String name = ss[ss.length - 1];
            File file = new File(Value.getCacheFile(), name);
            NetWork.download(path, file.getPath(), callBack);
        }

    }

    public boolean isFileExit(String path) {
        String[] ss = path.split("/");
        if (ss.length > 0) {
            String name = ss[ss.length - 1];
            File file = new File(Value.getCacheFile(), name);
            return file.exists();
        }
        return false;
    }


    public void getUserRateIfNull(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        if (userBean.getAvg() == 0f) {
            NetDataOpe.Comment.getVideoRateCommentByUseId(context,userBean, onFinishListener);
        }
    }


    public void getUserCallInfo(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        NetDataOpe.User.getUserCallInfo(context,userBean, onFinishListener);
    }


    public CommentBean getCommentReq(UserBean local, UserBean userBean) {
        CommentBean commentBean = new CommentBean();
        commentBean.setFromUser(local);
        commentBean.setToUser(userBean);
        return commentBean;
    }


    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public UserCenterDAOpe getUserCenterDAOpe(Context context) {
        if (userCenterDAOpe == null) {
            userCenterDAOpe = new UserCenterDAOpe();
        }
        return userCenterDAOpe;
    }


    public UserBean getUserChatInfo(UserBean userBean) {
        return null;
    }

    public void clickAgree(Context context,AgreeBean agreeBean, OnFinishListener onFinishListener) {
        NetDataOpe.Agree.clickAgree(context,agreeBean, onFinishListener);
    }

    public ArrayList<CommentBean> getCommentBeen() {
        return commentBeen;
    }

    public void setCommentBeen(ArrayList<CommentBean> commentBeen) {
        this.commentBeen = commentBeen;
    }

    public boolean isOnline(String phone) {
        OnLineListFrag onLineListFrag = (OnLineListFrag) FragmentUtil2.getInstance().getFragment(OnLineListFrag.class);
        if (onLineListFrag != null) {
            return onLineListFrag.getP().getD().isOnline(phone);
        }
        return false;
    }

}
