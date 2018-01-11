package com.siweisoft.heavycenter.data.netd.unit.info;

//by summer on 2018-01-11.

import com.android.lib.network.bean.req.BaseReqBean;

public class InfoReqBean extends BaseReqBean {

    private int id;

    public InfoReqBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
