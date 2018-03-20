package com.siweisoft.service.ui.user.login;

//by summer on 2017-07-03.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.lib.base.interf.OnFinishListener;
import com.android.lib.constant.UrlConstant;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.util.FragmentUtil2;
import com.android.lib.util.GsonUtil;
import com.android.lib.util.NullUtil;
import com.android.lib.util.SPUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.view.bottommenu.MessageEvent;
import com.hyphenate.chat.EMClient;
import com.siweisoft.service.R;
import com.siweisoft.service.base.BaseServerFrag;
import com.siweisoft.service.base.UISNetAdapter;
import com.siweisoft.service.netdb.NetValue;
import com.siweisoft.service.netdb.user.UserBean;
import com.siweisoft.service.ui.Constant.Value;
import com.siweisoft.service.ui.main.MainAct;
import com.siweisoft.service.ui.user.regist.RegistFrag;
import com.siweisoft.service.ui.user.resetpwd.ReSetPwdFrag;

import butterknife.OnClick;
import butterknife.Optional;

public class LoginFrag extends BaseServerFrag<LoginUIOpe, LoginDAOpe> {


    @Override
    public void initNow() {
        super.initNow();
        FragmentUtil2.getInstance().print();
        getP().getU().bind.setLogin(getP().getD().getUserBean());
        getP().getU().initImage(getP().getD().getImageUril());
        getP().getU().bind.etServer.setText(NetValue.获取域名从文件(getActivity()));
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
        getP().getU().initIp();
    }

    @Optional
    @OnClick({R.id.button, R.id.tv_regist, R.id.tv_reset})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.button:
                if (NullUtil.isStrEmpty(getP().getD().getUserBean().getPhone())) {
                    return;
                }
                getP().getD().login(getP().getD().getUserBean(), new UISNetAdapter<UserBean>(this) {
                    @Override
                    public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                        if(haveData){
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
                FragmentUtil2.getInstance().add(getActivity(), R.id.act_base_root, new RegistFrag());
                break;
            case R.id.tv_reset:
                FragmentUtil2.getInstance().add(getActivity(), R.id.act_base_root, new ReSetPwdFrag());
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
}
