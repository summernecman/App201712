package com.siweisoft.heavycenter.data.netd.user.info;

//by summer on 2018-01-11.

import com.android.lib.network.bean.req.BaseReqBean;

public class InfoReqBean extends BaseReqBean {

    private int id;

    private int isApp;

    public InfoReqBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsApp() {
        return isApp;
    }

    public void setIsApp(int isApp) {
        this.isApp = isApp;
    }
}
