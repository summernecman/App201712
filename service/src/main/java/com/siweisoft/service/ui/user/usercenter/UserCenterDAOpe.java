package com.siweisoft.service.ui.user.usercenter;

//by summer on 17-08-24.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.LogUtil;
import com.siweisoft.service.bean.TipBean;
import com.siweisoft.service.netdb.NetDataOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.user.userinfo.UserInfoDAOpe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class UserCenterDAOpe extends BaseDAOpe {

    UserInfoDAOpe userInfoDAOpe;


    private ArrayList<TipBean> tipdata = new ArrayList<>();



    public UserInfoDAOpe getUserInfoDAOpe(Context context) {
        if (userInfoDAOpe == null) {
            userInfoDAOpe = new UserInfoDAOpe();
            userInfoDAOpe.setContext(context);
        }
        return userInfoDAOpe;
    }


    public void getUserCallInfo(Context context,OnFinishListener onFinishListener) {
        UserBean userBean = new UserBean();
        userBean.setPhone(Value.getUserInfo().getPhone());
        NetDataOpe.User.getUserCallInfo(context,userBean, onFinishListener);
    }

    public static void getUserTips(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        NetDataOpe.Comment.getUserTips(context,userBean, onFinishListener);
    }

    public void getCommentNumByUserName(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        NetDataOpe.Comment.getCommentNumByUserName(context,userBean, onFinishListener);
    }

    public void getCollectionNumByUserId(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        NetDataOpe.Collection.getCollectionNumByUserId(context,userBean, onFinishListener);
    }

    public void getShareNumByUserPhone(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        NetDataOpe.Share.getShareNumByUserPhone(context,userBean, onFinishListener);
    }

    public ArrayList<TipBean> mapTolist(HashMap<Integer, TipBean> data) {
        tipdata.clear();
        Iterator<Integer> iterator = data.keySet().iterator();
        while (iterator.hasNext()) {
            int key = iterator.next();
            LogUtil.E("key:" + key + ":" + data.get(key).getTip() + ":" + data.get(key).getNum());
            tipdata.add(new TipBean(key, data.get(key).getTip(), data.get(key).getNum()));
        }

        return tipdata;
    }

    public ArrayList<TipBean> getTipdata() {
        return tipdata;
    }
}
