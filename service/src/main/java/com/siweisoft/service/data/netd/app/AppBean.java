package com.siweisoft.service.data.netd.app;

//by summer on 2017-12-29.

import com.android.lib.bean.BaseBean;

public class AppBean extends BaseBean {

    private int id;

    private int versioncode;

    private String versionname;

    private String url;

    public AppBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersioncode() {
        return versioncode;
    }

    public void setVersioncode(int versioncode) {
        this.versioncode = versioncode;
    }

    public String getVersionname() {
        return versionname;
    }

    public void setVersionname(String versionname) {
        this.versionname = versionname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
