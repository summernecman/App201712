package com.siweisoft.heavycenter.data.netd.msg.list;

//by summer on 2018-01-11.

import com.android.lib.network.bean.req.BaseReqBean;

public class MsgsReqBean extends BaseReqBean {


    //全部、运输、车辆、公告、系统


    public static final String MESSAGE_CATE_ALL = "";

    public static final String MESSAGE_CATE_TRANS = "2";

    public static final String MESSAGE_CATE_CAR = "3";

    public static final String MESSAGE_CATE_PUB = "4";

    public static final String MESSAGE_CATE_SYS = "1";

    private int userId;

    private String messageCate;

    private int pageIndex;

    private int pageSize;

    public MsgsReqBean() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessageCate() {
        return messageCate;
    }

    public void setMessageCate(String messageCate) {
        this.messageCate = messageCate;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
