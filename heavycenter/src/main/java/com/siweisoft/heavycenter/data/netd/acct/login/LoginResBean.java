package com.siweisoft.heavycenter.data.netd.acct.login;

//by summer on 2018-01-09.

import com.android.lib.bean.BaseBean;

public class LoginResBean extends BaseBean {


    /**
     * userId : 112
     * passWord : e10adc3949ba59abbe56e057f20f883e
     * tel : 18721607438
     * trueName : 18721607438
     * userPhoto :
     * companyId : 0
     * bindCompanyState : 0
     * bindCompanyTime :
     * companyName :
     * abbreviationName :
     * userType : 0
     * deviceType : 1
     * deviceId : 1104a89792ac1fc2d38
     * userRole : 1
     * loginStatus : 1
     */

    private int userId;
    private String passWord;
    private String tel;
    private String trueName;
    private String userPhoto;
    private int companyId;
    private int bindCompanyState;
    private String bindCompanyTime;
    private String companyName;
    private String abbreviationName;
    private int userType;
    private int deviceType;
    private String deviceId;

    public static final String USER_ROLE_DRIVER = "driver";

    public static final String USER_ROLE_SUPER_ADMIN = "superAdmin";

    public static final String USER_ROLE_ADMIN = "admin";

    public static final String USER_ROLE_GENERAL = "general";

    public static final String USER_ROLE_SYS_ADMIN = "sysAdmin";

    public static final String USER_ROLE_DRIVER_CN = "驾驶员";

    public static final String USER_ROLE_SUPER_ADMIN_CN = "超级管理员";

    public static final String USER_ROLE_ADMIN_CN = "管理员";

    public static final String USER_ROLE_GENERAL_CN = "普通用户";

    public static final String USER_ROLE_SYS_ADMIN_CN = "系统管理员";

    public static final int BIND_UNIT_STATE_UNBIND = 0;

    public static final int BIND_UNIT_STATE_CHECK = 1;

    public static final int BIND_UNIT_STATE_BINDED = 2;

    public static final int BIND_UNIT_STATE_REJECT = 3;

    public static final String BIND_UNIT_STATE_UNBIND_CN = "未绑定";

    public static final String BIND_UNIT_STATE_CHECK_CN = "审核中";

    public static final String BIND_UNIT_STATE_BINDED_CN = "已绑定";

    public static final String BIND_UNIT_STATE_REJECT_CN = "绑定失败";


    private String userRole;
    private int loginStatus;


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(String userPhoto) {
        this.userPhoto = userPhoto;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getBindCompanyState() {
        return bindCompanyState;
    }

    public void setBindCompanyState(int bindCompanyState) {
        this.bindCompanyState = bindCompanyState;
    }

    public String getBindCompanyTime() {
        return bindCompanyTime;
    }

    public void setBindCompanyTime(String bindCompanyTime) {
        this.bindCompanyTime = bindCompanyTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAbbreviationName() {
        return abbreviationName;
    }

    public void setAbbreviationName(String abbreviationName) {
        this.abbreviationName = abbreviationName;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(int deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


    public int getLoginStatus() {
        return loginStatus;
    }

    public void setLoginStatus(int loginStatus) {
        this.loginStatus = loginStatus;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
}
