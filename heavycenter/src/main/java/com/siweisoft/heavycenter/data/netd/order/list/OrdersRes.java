package com.siweisoft.heavycenter.data.netd.order.list;

//by summer on 2018-01-17.

import com.android.lib.bean.BaseBean;
import com.siweisoft.heavycenter.data.netd.mana.car.list.CarsResBean;

import java.util.ArrayList;
import java.util.List;

public class OrdersRes extends BaseBean {


    /**
     * totalCount : 1
     * pageIndex : -1000
     * pageCount : 1
     * pageSize : 1000
     * results : [{"orderId":45,"orderNo":"2018011715","orderType":"S","productName":"混凝土","specification":"C50","planNumber":2000,"shdwName":"雨后","fhdwName":"干干干"}]
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
         * orderId : 46
         * orderNo : 2018011716
         * orderType : S
         * productName : 混凝土
         * specification : C30
         * planNumber : 200
         * shdwName : 搞搞搞
         * fhdwName : 干干干
         * planTime : 1516118400000
         * address : 
         * shdwQName : 搞搞搞有限公司
         * fhdwQName : 干干干有限公司
         * signRule : 收货单位直接签收
         * orderStatus : new
         * vehicleList : [{"trueName":"驾驶员A","maxCapacity":25,"tel":"18721607200","vehicleId":45,"carLicenseNo":"沪A12332"},{"trueName":null,"maxCapacity":25,"tel":null,"vehicleId":44,"carLicenseNo":"沪A8485"}]
         */

        private int orderId;
        private String orderNo;
        private String orderType;
        private String productName;
        private String specification;
        private int planNumber;
        private String shdwName;
        private String fhdwName;
        private long planTime;
        private String address;
        private String shdwQName;
        private String fhdwQName;
        private String signRule;
        private String orderStatus;
        private List<CarsResBean.CarInfoRes> vehicleList = new ArrayList<>();

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }

        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }

        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }

        public int getPlanNumber() {
            return planNumber;
        }

        public void setPlanNumber(int planNumber) {
            this.planNumber = planNumber;
        }

        public String getShdwName() {
            return shdwName;
        }

        public void setShdwName(String shdwName) {
            this.shdwName = shdwName;
        }

        public String getFhdwName() {
            return fhdwName;
        }

        public void setFhdwName(String fhdwName) {
            this.fhdwName = fhdwName;
        }

        public long getPlanTime() {
            return planTime;
        }

        public void setPlanTime(long planTime) {
            this.planTime = planTime;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getShdwQName() {
            return shdwQName;
        }

        public void setShdwQName(String shdwQName) {
            this.shdwQName = shdwQName;
        }

        public String getFhdwQName() {
            return fhdwQName;
        }

        public void setFhdwQName(String fhdwQName) {
            this.fhdwQName = fhdwQName;
        }

        public String getSignRule() {
            return signRule;
        }

        public void setSignRule(String signRule) {
            this.signRule = signRule;
        }

        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }

        public List<CarsResBean.CarInfoRes> getVehicleList() {
            return vehicleList;
        }

        public void setVehicleList(List<CarsResBean.CarInfoRes> vehicleList) {
            this.vehicleList = vehicleList;
        }
    }
}
