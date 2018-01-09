package com.siweisoft.heavycenter.data.netd.acct.regist;

//by summer on 2018-01-09.

import com.android.lib.network.bean.req.BaseReqBean;

public class RegistReqBean extends BaseReqBean {

    public static final int IDENTITY_TYPE_PHONE = 1;

    public static final int IDENTITY_TYPE_QQ = 2;

    public static final int IDENTITY_TYPE_WEIXIN = 3;

    public static final int IDENTITY_TYPE_WEIBO = 4;



    private int identityType;

    private String tel;

    private String passWord;

    private String trueName;

    private int identifier;

    private String securityCode;

    public RegistReqBean() {
    }

    public int getIdentityType() {
        return identityType;
    }

    public void setIdentityType(int identityType) {
        this.identityType = identityType;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }
}
