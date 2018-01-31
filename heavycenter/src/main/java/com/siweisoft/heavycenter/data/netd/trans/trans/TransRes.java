package com.siweisoft.heavycenter.data.netd.trans.trans;

//by summer on 2018-01-18.

import com.android.lib.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

public class TransRes extends BaseBean {


    /**
     * totalCount : 26
     * pageIndex : -10
     * pageCount : 0
     * pageSize : 10
     * results : [{"orderType":"S","ShTime":123456799,"receiveCompanyName":"","signStatus":0,"totalSuttle":0,"specifications":"一级灰细度(8-12)","userId":81,"develiverCompanyName":"","productName":"粉煤灰","carLicenseNo":"沪A00000","planNumber":500,"carNumber":"","trueName":"驾驶员B","ordersId":20,"develiverNum":"","tel":"18721504444","transportrecordId":18,"receiveNum":"","FhTime":""}]
     */

    private int totalCount;
    private int pageIndex;
    private int pageCount;
    private int pageSize;
    private List<ResultsBean> results = new ArrayList<>();

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean extends BaseBean{
        /**
         * orderType : S
         * ShTime : 123456799
         * receiveCompanyName :
         * signStatus : 0
         * totalSuttle : 0
         * specifications : 一级灰细度(8-12)
         * userId : 81
         * develiverCompanyName :
         * productName : 粉煤灰
         * carLicenseNo : 沪A00000
         * planNumber : 500
         * carNumber :
         * trueName : 驾驶员B
         * ordersId : 20
         * develiverNum :
         * tel : 18721504444
         * transportrecordId : 18
         * receiveNum :
         * FhTime :
         */

        private String orderType;
        private int ShTime;
        private String receiveCompanyName;
        private int signStatus;
        private int totalSuttle;
        private String specifications;
        private int userId;
        private String develiverCompanyName;
        private String productName;
        private String carLicenseNo;
        private int planNumber;
        private String carNumber;
        private String trueName;
        private int ordersId;
        private String develiverNum;
        private String tel;
        private int transportrecordId;
        private String receiveNum;
        private String FhTime;

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public int getShTime() {
            return ShTime;
        }

        public void setShTime(int ShTime) {
            this.ShTime = ShTime;
        }

        public String getReceiveCompanyName() {
            return receiveCompanyName;
        }

        public void setReceiveCompanyName(String receiveCompanyName) {
            this.receiveCompanyName = receiveCompanyName;
        }

        public int getSignStatus() {
            return signStatus;
        }

        public void setSignStatus(int signStatus) {
            this.signStatus = signStatus;
        }

        public int getTotalSuttle() {
            return totalSuttle;
        }

        public void setTotalSuttle(int totalSuttle) {
            this.totalSuttle = totalSuttle;
        }

        public String getSpecifications() {
            return specifications;
        }

        public void setSpecifications(String specifications) {
            this.specifications = specifications;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getDeveliverCompanyName() {
            return develiverCompanyName;
        }

        public void setDeveliverCompanyName(String develiverCompanyName) {
            this.develiverCompanyName = develiverCompanyName;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getCarLicenseNo() {
            return carLicenseNo;
        }

        public void setCarLicenseNo(String carLicenseNo) {
            this.carLicenseNo = carLicenseNo;
        }

        public int getPlanNumber() {
            return planNumber;
        }

        public void setPlanNumber(int planNumber) {
            this.planNumber = planNumber;
        }

        public String getCarNumber() {
            return carNumber;
        }

        public void setCarNumber(String carNumber) {
            this.carNumber = carNumber;
        }

        public String getTrueName() {
            return trueName;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }

        public int getOrdersId() {
            return ordersId;
        }

        public void setOrdersId(int ordersId) {
            this.ordersId = ordersId;
        }

        public String getDeveliverNum() {
            return develiverNum;
        }

        public void setDeveliverNum(String develiverNum) {
            this.develiverNum = develiverNum;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }

        public int getTransportrecordId() {
            return transportrecordId;
        }

        public void setTransportrecordId(int transportrecordId) {
            this.transportrecordId = transportrecordId;
        }

        public String getReceiveNum() {
            return receiveNum;
        }

        public void setReceiveNum(String receiveNum) {
            this.receiveNum = receiveNum;
        }

        public String getFhTime() {
            return FhTime;
        }

        public void setFhTime(String FhTime) {
            this.FhTime = FhTime;
        }
    }
}
