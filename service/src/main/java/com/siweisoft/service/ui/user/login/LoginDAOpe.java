package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.content.Context;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.NetI;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.LoadUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.system.UUUIDUtil;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.netdb.NetDataOpe;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;

public class LoginDAOpe extends BaseDAOpe {

    UserBean userBean = new UserBean();



    public void autoLogin() {

    }

    public void login(Context context,UserBean userBean, NetI<UserBean> adapter) {
        NetDataOpe.User.login(context,userBean,adapter);
    }

    public String getImageUril() {
        return "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1504080205794&di=f1615a7fc30840be57ff68b24e6f953e&imgtype=0&src=http%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F342ac65c103853437fc566079913b07eca80888a.jpg";
    }

    public void init(Context context){
        userBean.setPhone(SPUtil.getInstance().getStr(Value.USERNAME));
        userBean.setPwd(SPUtil.getInstance().getStr(Value.PWD));
        userBean.setUuuid(UUUIDUtil.getInstance().getUUUId(context));
    }


    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }


}
