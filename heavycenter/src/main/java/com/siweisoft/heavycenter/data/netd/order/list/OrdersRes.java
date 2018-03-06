package com.siweisoft.heavycenter.data.netd.order.list;

//by summer on 2018-01-17.

import android.databinding.Bindable;

import com.android.lib.bean.BaseBean;
import com.android.lib.util.StringUtil;
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
        private double planNumber;
        private String shdwName;
        private String fhdwName;
        private long planTime;
        private String address;
        private String shdwQName;
        private String fhdwQName;
        private String signRule;
        private String orderStatus;
        private String carLicenseNo;
        private String trueName;
        private String tel;
        private Object startTime;
        private Object finishTime;
        private int totalRecord;
        private double actualFh;
        private double actualSh;
        private  int auditState;
        private String accessComName;
        private String type;


        public final static int AUDITSTATE_未审核 = 0;

        public final static int AUDITSTATE_接收 = 1;

        public final static int AUDITSTATE_拒绝 = 2;


        public String getAccessComName() {
            if("S".equals(getOrderType())){
                accessComName = getShdwName();
            }else{
                accessComName = getFhdwName();
            }
            return accessComName;
        }

        public String getType() {
            if("S".equals(getOrderType())){
                type = "发往";
            }else{
                type = "来自";
            }
            return type;
        }

        public boolean isSendType(){
            if(getType().equals("发往")){
               return true;
            }else{
              return false;
            }
        }

        public int getAuditState() {
            return auditState;
        }

        public void setAuditState(int auditState) {
            this.auditState = auditState;
        }

        private List<CarsResBean.CarInfoRes> vehicleList = new ArrayList<>();
        @Bindable
        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
        @Bindable
        public String getOrderNo() {
            return orderNo;
        }

        public void setOrderNo(String orderNo) {
            this.orderNo = orderNo;
        }
        @Bindable
        public String getOrderType() {
            return orderType;
        }

        public void setOrderType(String orderType) {
            this.orderType = orderType;
        }
        @Bindable
        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
        @Bindable
        public String getSpecification() {
            return specification;
        }

        public void setSpecification(String specification) {
            this.specification = specification;
        }
        @Bindable
        public double getPlanNumber() {
            return planNumber;
        }


        @Bindable
        public String getPlanNumberCN() {
            return "计划:"+StringUtil.getStr(planNumber)+"t";
        }

        public void setPlanNumber(double planNumber) {
            this.planNumber = planNumber;
        }



        @Bindable
        public String getShdwName() {
            return shdwName;
        }

        public void setShdwName(String shdwName) {
            this.shdwName = shdwName;
        }
        @Bindable
        public String getFhdwName() {
            return fhdwName;
        }

        public void setFhdwName(String fhdwName) {
            this.fhdwName = fhdwName;
        }
        @Bindable
        public long getPlanTime() {
            return planTime;
        }
        public void setPlanTime(long planTime) {
            this.planTime = planTime;
        }
        @Bindable
        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
        @Bindable
        public String getShdwQName() {
            return shdwQName;
        }

        public void setShdwQName(String shdwQName) {
            this.shdwQName = shdwQName;
        }
        @Bindable
        public String getFhdwQName() {
            return fhdwQName;
        }

        public void setFhdwQName(String fhdwQName) {
            this.fhdwQName = fhdwQName;
        }
        @Bindable
        public String getSignRule() {
            return signRule;
        }

        public void setSignRule(String signRule) {
            this.signRule = signRule;
        }
        @Bindable
        public String getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(String orderStatus) {
            this.orderStatus = orderStatus;
        }
        @Bindable
        public List<CarsResBean.CarInfoRes> getVehicleList() {
            return vehicleList;
        }

        public void setVehicleList(List<CarsResBean.CarInfoRes> vehicleList) {
            this.vehicleList = vehicleList;
        }
        @Bindable
        public String getCarLicenseNo() {
            return carLicenseNo;
        }

        public void setCarLicenseNo(String carLicenseNo) {
            this.carLicenseNo = carLicenseNo;
        }
        @Bindable
        public String getTrueName() {
            return trueName;
        }

        public void setTrueName(String trueName) {
            this.trueName = trueName;
        }
        @Bindable
        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
        @Bindable
        public Object getStartTime() {
            return startTime;
        }

        public void setStartTime(Object startTime) {
            this.startTime = startTime;
        }
        @Bindable
        public Object getFinishTime() {
            return finishTime;
        }

        public void setFinishTime(Object finishTime) {
            this.finishTime = finishTime;
        }


        @Bindable
        public String getTotalRecordCN() {
            return "总计:"+StringUtil.getStr(totalRecord)+"车";
        }



        @Bindable
        public int getTotalRecord() {
            return totalRecord;
        }

        public void setTotalRecord(int totalRecord) {
            this.totalRecord = totalRecord;
        }
        @Bindable
        public double getActualFh() {
            return actualFh;
        }


        @Bindable
        public String getActualFhCN() {
            return "实发:"+StringUtil.getStr(actualFh)+"t";
        }

        public void setActualFh(double actualFh) {
            this.actualFh = actualFh;
        }
        @Bindable
        public double getActualSh() {
            return actualSh;
        }


        @Bindable
        public String getActualShCN() {
            return "实收:"+StringUtil.getStr(actualSh)+"t";
        }

        public void setActualSh(double actualSh) {
            this.actualSh = actualSh;
        }


        @Bindable
        public String getYKCN(){
            return "盈亏:"+(actualSh-actualFh)+"t";
        }

        @Bindable
        public String getYK(){
            return  (actualSh-actualFh)+"";
        }



    }
}
