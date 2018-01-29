package com.siweisoft.heavycenter.data.netd.order.list;

//by summer on 2018-01-17.

import com.android.lib.network.bean.req.BaseReqBean;

public class OrdersReq extends BaseReqBean {

    private int companyId;

    private int isApp;

    private String orderStatus;

    private int pageIndex;

    private int pageSize;

    public static final String STATUS_NEW = "new";

    public static final String STATUS_ING = "ing";

    public static final String STATUS_DONE = "done";

    public OrdersReq() {
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getIsApp() {
        return isApp;
    }

    public void setIsApp(int isApp) {
        this.isApp = isApp;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
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
