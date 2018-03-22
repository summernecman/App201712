package com.siweisoft.service.ui.acct.login;

//by summer on 2017-07-03.

import android.content.Intent;
import android.view.View;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.android.lib.view.bottommenu.MessageEvent;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.base.UISNetAdapter;
import com.siweisoft.service.data.netd.NetValue;
import com.siweisoft.service.data.netd.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.acct.acct.LoginAct;
import com.siweisoft.service.ui.main.MainAct;
import com.siweisoft.service.ui.acct.regist.RegistFrag;
import com.siweisoft.service.ui.acct.resetpwd.ReSetPwdFrag;

import butterknife.OnClick;
import butterknife.Optional;

public class LoginFrag extends BaseServerFrag<LoginUIOpe, LoginDAOpe> {


    @Override
    public void initNow() {
        super.initNow();
        getP().getD().init(getBaseUIAct());
        getP().getU().initInput(getP().getD().getUserBean());
        getP().getU().initImage(getP().getD().getImageUril());
        if (EMClient.getInstance().isLoggedInBefore() && SPUtil.getInstance().getBoolean(Value.autologin)) {
            EMClient.getInstance().chatManager().loadAllConversations();
            EMClient.getInstance().groupManager().loadAllGroups();
            if (Value.getUserInfo() == null || NullUtil.isStrEmpty(Value.getUserInfo().getPhone())) {
                return;
            }
            Intent intent = new Intent(getActivity(), MainAct.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
    }

    @Optional
    @OnClick({R.id.button, R.id.tv_regist, R.id.tv_reset})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (NullUtil.isStrEmpty(getP().getD().getUserBean().getPhone())) {
                    return;
                }
                getP().getD().login(getBaseAct(),getP().getD().getUserBean(), new UISNetAdapter<UserBean>(this) {
                    @Override
                    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                        if(haveData&&!baseResBean.isException()){
                            UserBean userBean = GsonUtil.getInstance().fromJson(GsonUtil.getInstance().toJson(baseResBean.getData()),UserBean.class);
                            Value.saveUserInfo(userBean);
                            Value.saveVideoTips(StringUtil.getStr(baseResBean.getOther()));
                            SPUtil.getInstance().init(getActivity()).saveStr(Value.DATA_INTENT2, NetValue.获取地址(""));
                            Intent intent = new Intent(getActivity(), MainAct.class);
                            getActivity().startActivity(intent);
                            getActivity().finish();
                        }
                    }
                });
                break;
            case R.id.tv_regist:
                FragManager2.getInstance().start(getBaseUIAct(), LoginAct.登录模块,new RegistFrag());
                break;
            case R.id.tv_reset:
                FragManager2.getInstance().start(getBaseUIAct(), LoginAct.登录模块,new ReSetPwdFrag());
                break;
        }
    }

    @Override
    public void dealMesage(MessageEvent event) {
        super.dealMesage(event);
        UserBean userBean = (UserBean) event.data;
        getP().getD().getUserBean().setPhone(userBean.getPhone());
        getP().getD().getUserBean().setPwd(userBean.getPwd());
        getP().getU().bind.setLogin(getP().getD().getUserBean());


    }


    @Override
    protected boolean is注册事件总线() {
        return true;
    }
}
