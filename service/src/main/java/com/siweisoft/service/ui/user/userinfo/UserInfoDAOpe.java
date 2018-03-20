package com.siweisoft.service.ui.user.userinfo;

//by summer on 17-08-24.

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.NetWork;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.bean.AllUserBean;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.bean.TipsBean;
import com.siweisoft.service.netdb.agree.AgreeBean;
import com.siweisoft.service.netdb.agree.AgreeOpe;
import com.siweisoft.service.netdb.comment.CommentBean;
import com.siweisoft.service.netdb.comment.CommentI;
import com.siweisoft.service.netdb.comment.CommentOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.netdb.user.UserNetOpe;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.onlinelist.OnLineListFrag;
import com.siweisoft.service.ui.user.usercenter.UserCenterDAOpe;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class UserInfoDAOpe extends BaseDAOpe {

    UserBean userBean;

    CommentI commentI;

    UserNetOpe userI;

    AgreeI agreeI;

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


    public void getUserInfo(int id, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe();
        }
        UserBean u = new UserBean();
        u.setId(id);
        userI.getUserInfoById(u, onFinishListener);
    }

    public void getRemarks(CommentBean commentBean, final OnFinishListener onFinishListener) {
        if (commentI == null) {
            commentI = new CommentOpe();
        }

        commentI.getCommentByUserIdWithMyOptionWithLimit(commentBean, new OnFinishListener() {
            @Override
            public void onFinish(Object o) {
                ArrayList<CommentBean> res = (ArrayList<CommentBean>) o;
                onFinishListener.onFinish(res);
            }
        });

    }

    public void getOtherUsersInfoByPhone(final UserBean u, List<String> strs, final OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe();
        }
        ArrayList<UserBean> userBeen = new ArrayList<>();
        for (int i = 0; i < strs.size(); i++) {
            UserBean userBean = new UserBean(strs.get(i));
            userBeen.add(userBean);
        }
        AllUserBean allUserBean = new AllUserBean();
        allUserBean.setOther(userBeen);
        allUserBean.setMe(Value.getUserInfo());
        userI.getOtherUsersInfoByPhone(allUserBean, new OnFinishListener() {
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


    public void getUserRateIfNull(UserBean userBean, OnFinishListener onFinishListener) {
        if (commentI == null) {
            commentI = new CommentOpe();
        }
        if (userBean.getAvg() == 0f) {
            commentI.getVideoRateCommentByUseId(userBean, onFinishListener);
        }
    }


    public void getUserCallInfo(UserBean userBean, OnFinishListener onFinishListener) {
        if (userI == null) {
            userI = new UserNetOpe();
        }
        userI.getUserCallInfo(userBean, onFinishListener);
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

    public UserCenterDAOpe getUserCenterDAOpe() {
        if (userCenterDAOpe == null) {
            userCenterDAOpe = new UserCenterDAOpe();
        }
        return userCenterDAOpe;
    }


    public UserBean getUserChatInfo(UserBean userBean) {
        return null;
    }

    public void clickAgree(AgreeBean agreeBean, OnFinishListener onFinishListener) {
        if (agreeI == null) {
            agreeI = new AgreeOpe();
        }
        agreeI.clickAgree(agreeBean, onFinishListener);
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
