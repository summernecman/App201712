package com.siweisoft.service.ui.user.resetpwd;

//by summer on 2017-07-10.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.util.thread.ThreadUtil;
import com.siweisoft.service.netdb.NetDataOpe;
import com.siweisoft.service.netdb.user.UserBean;

import cn.jpush.sms.SMSSDK;
import cn.jpush.sms.listener.SmscheckListener;
import cn.jpush.sms.listener.SmscodeListener;

public class ReSetPwdDAOpe extends BaseDAOpe {

    UserBean userBean = new UserBean();


    private ThreadUtil threadUtil = new ThreadUtil();


    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public void resetPwd(Context context,UserBean userBean, OnFinishListener onFinishListener) {
        NetDataOpe.User.resetPwd(context,userBean, onFinishListener);
    }

    public void sendCode(String phone, final OnFinishListener onFinishListener) {
        SMSSDK.getInstance().getSmsCode(phone, "1", new SmscodeListener() {
            @Override
            public void getCodeSuccess(String s) {
                onFinishListener.onFinish(s);
            }

            @Override
            public void getCodeFail(int i, String s) {

            }
        });
    }

    public void checkCode(String phone, String code, final OnFinishListener onFinishListener) {
        SMSSDK.getInstance().checkSmsCode(phone, code, new SmscheckListener() {
            @Override
            public void checkCodeSuccess(String s) {
                onFinishListener.onFinish(true);
            }

            @Override
            public void checkCodeFail(int i, String s) {
                onFinishListener.onFinish(false);
            }
        });
    }

    public ThreadUtil getThreadUtil() {
        return threadUtil;
    }
}
