package com.siweisoft.heavycenter.module.acct.repwd;

//by summer on 2017-12-14.

import android.content.Context;

import com.android.lib.util.MD5Util;
import com.android.lib.util.NullUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.acct.code.CodeReqBean;
import com.siweisoft.heavycenter.data.netd.acct.forget.ForGetReqBean;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginReqBean;
import com.siweisoft.heavycenter.databinding.FragAcctRepwdBinding;

import cn.jpush.android.api.JPushInterface;

public class RepwdUIOpe extends AppUIOpe<FragAcctRepwdBinding> {

    ForGetReqBean forGetReqBean = new ForGetReqBean();

    CodeReqBean codeReqBean = new CodeReqBean();

    public RepwdUIOpe(Context context) {
        super(context);
    }

    public void initBg(String url){
       // GlideApp.with(context).asBitmap().load(url).centerCrop().into(bind.image);
    }


    public boolean go(){
        if(NullUtil.isStrEmpty(bind.phone.getText())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入手机号");
            return false;
        }
        if(NullUtil.isStrEmpty(bind.securycode.getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"请输入验证码");
            return false;
        }

        if(!bind.pwd.getText().toString().equals(bind.repwd.getText().toString())){
            ToastUtil.getInstance().showLong(getActivity(),"密码不一致");
            return false;
        }
        return true;
    }

    public ForGetReqBean getforGetReqBean() {
        forGetReqBean.setTel(bind.phone.getText().toString());
        forGetReqBean.setSecurityCode(bind.securycode.getText().toString());
        forGetReqBean.setNewPwd(MD5Util.md5(bind.repwd.getText().toString()));
        return forGetReqBean;
    }

    public CodeReqBean getCodeReqBean() {
        codeReqBean.setTel(bind.phone.getText().toString());
        codeReqBean.setType("2");
        return codeReqBean;
    }
}
