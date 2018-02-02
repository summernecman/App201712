package com.siweisoft.heavycenter.data.netd.trans.detail;

import com.android.lib.bean.BaseBean;

import java.util.List;

public  class TransDetailRes extends BaseBean {


    /**
     * orderType : S
     * develiverCompanyLat : 31.314282
     * receiveCompanyName : 王尼玛有限公司
     * develiverCompanyLng : 121.515221
     * develiverAbbreviationName : 老干爹
     * signStatus : 0
     * deliverRecordList : [{"adjustGross":0,"fhDeduct":0,"adjustTare":0,"gross":20,"grossPhoto":"","deductTime":null,"tareTime":null,"grossTime":null,"remark":"","editTime":null,"warehouseId":0,"createTime":1517537731000,"tare":10,"creater":80,"id":106,"editer":0,"transportRecordId":105,"tarePhoto":"","status":1}]
     * specifications : 100f
     * productName : 水泥
     * carLicenseNo : 沪A44444
     * trueName : 18721503333
     * receiveRecordList : [{"adjustGross":0,"adjustTare":0,"gross":20,"grossPhoto":null,"deductTime":null,"tareTime":null,"grossTime":null,"remark":"","editTime":null,"shDeduct":0,"warehouseId":0,"createTime":1517537731000,"tare":10,"creater":80,"id":101,"editer":0,"transportRecordId":105,"tarePhoto":"","status":1}]
     * receiveAbbreviationName : 王尼玛
     * receiveCompanyLat : 31.314243
     * signRule : zjSign
     * develiverNum : 10
     * tel : 18721503333
     * receiveCompanyLng : 121.515273
     * transportrecordId : 105
     * receiveNum : 0
     * orderNo : 201802022
     * userId : 87
     * develiverCompanyName : 老干爹有限公司
     * carNumber : null
     * ordersId : 59
     */

    private String orderType;
    private double develiverCompanyLat;
    private String receiveCompanyName;
    private double develiverCompanyLng;
    private String develiverAbbreviationName;
    private int signStatus;
    private String specifications;
    private String productName;
    private String carLicenseNo;
    private String trueName;
    private String receiveAbbreviationName;
    private double receiveCompanyLat;
    private String signRule;
    private int develiverNum;
    private String tel;
    private double receiveCompanyLng;
    private int transportrecordId;
    private int receiveNum;
    private String orderNo;
    private int userId;
    private String develiverCompanyName;
    private Object carNumber;
    private int ordersId;
    private double totalSuttle;
    private double planNumber;
    private Object FhTime;
    private Object ShTime;
    private List<DeliverRecordListBean> deliverRecordList;
    private List<ReceiveRecordListBean> receiveRecordList;


    public static final int SING_STATUS_等待确认 = 0;

    public static final int SING_STATUS_已确认 = 1;

    public static final String 订单类型_发货 = "S";

    public static final String 订单类型_收货 = "R";


    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public double getDeveliverCompanyLat() {
        return develiverCompanyLat;
    }

    public void setDeveliverCompanyLat(double develiverCompanyLat) {
        this.develiverCompanyLat = develiverCompanyLat;
    }

    public String getReceiveCompanyName() {
        return receiveCompanyName;
    }

    public void setReceiveCompanyName(String receiveCompanyName) {
        this.receiveCompanyName = receiveCompanyName;
    }

    public double getDeveliverCompanyLng() {
        return develiverCompanyLng;
    }

    public void setDeveliverCompanyLng(double develiverCompanyLng) {
        this.develiverCompanyLng = develiverCompanyLng;
    }

    public String getDeveliverAbbreviationName() {
        return develiverAbbreviationName;
    }

    public void setDeveliverAbbreviationName(String develiverAbbreviationName) {
        this.develiverAbbreviationName = develiverAbbreviationName;
    }

    public int getSignStatus() {
        return signStatus;
    }

    public void setSignStatus(int signStatus) {
        this.signStatus = signStatus;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
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

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getReceiveAbbreviationName() {
        return receiveAbbreviationName;
    }

    public void setReceiveAbbreviationName(String receiveAbbreviationName) {
        this.receiveAbbreviationName = receiveAbbreviationName;
    }

    public double getReceiveCompanyLat() {
        return receiveCompanyLat;
    }

    public void setReceiveCompanyLat(double receiveCompanyLat) {
        this.receiveCompanyLat = receiveCompanyLat;
    }

    public String getSignRule() {
        return signRule;
    }

    public void setSignRule(String signRule) {
        this.signRule = signRule;
    }

    public int getDeveliverNum() {
        return develiverNum;
    }

    public void setDeveliverNum(int develiverNum) {
        this.develiverNum = develiverNum;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public double getReceiveCompanyLng() {
        return receiveCompanyLng;
    }

    public void setReceiveCompanyLng(double receiveCompanyLng) {
        this.receiveCompanyLng = receiveCompanyLng;
    }

    public int getTransportrecordId() {
        return transportrecordId;
    }

    public void setTransportrecordId(int transportrecordId) {
        this.transportrecordId = transportrecordId;
    }

    public int getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(int receiveNum) {
        this.receiveNum = receiveNum;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
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

    public Object getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(Object carNumber) {
        this.carNumber = carNumber;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public List<DeliverRecordListBean> getDeliverRecordList() {
        return deliverRecordList;
    }

    public void setDeliverRecordList(List<DeliverRecordListBean> deliverRecordList) {
        this.deliverRecordList = deliverRecordList;
    }

    public List<ReceiveRecordListBean> getReceiveRecordList() {
        return receiveRecordList;
    }

    public void setReceiveRecordList(List<ReceiveRecordListBean> receiveRecordList) {
        this.receiveRecordList = receiveRecordList;
    }

    public static class DeliverRecordListBean {
        /**
         * adjustGross : 0
         * fhDeduct : 0
         * adjustTare : 0
         * gross : 20
         * grossPhoto :
         * deductTime : null
         * tareTime : null
         * grossTime : null
         * remark :
         * editTime : null
         * warehouseId : 0
         * createTime : 1517537731000
         * tare : 10
         * creater : 80
         * id : 106
         * editer : 0
         * transportRecordId : 105
         * tarePhoto :
         * status : 1
         */

        private int adjustGross;
        private int fhDeduct;
        private int adjustTare;
        private int gross;
        private String grossPhoto;
        private Object deductTime;
        private Object tareTime;
        private Object grossTime;
        private String remark;
        private Object editTime;
        private int warehouseId;
        private long createTime;
        private int tare;
        private int creater;
        private int id;
        private int editer;
        private int transportRecordId;
        private String tarePhoto;
        private int status;

        public int getAdjustGross() {
            return adjustGross;
        }

        public void setAdjustGross(int adjustGross) {
            this.adjustGross = adjustGross;
        }

        public int getFhDeduct() {
            return fhDeduct;
        }

        public void setFhDeduct(int fhDeduct) {
            this.fhDeduct = fhDeduct;
        }

        public int getAdjustTare() {
            return adjustTare;
        }

        public void setAdjustTare(int adjustTare) {
            this.adjustTare = adjustTare;
        }

        public int getGross() {
            return gross;
        }

        public void setGross(int gross) {
            this.gross = gross;
        }

        public String getGrossPhoto() {
            return grossPhoto;
        }

        public void setGrossPhoto(String grossPhoto) {
            this.grossPhoto = grossPhoto;
        }

        public Object getDeductTime() {
            return deductTime;
        }

        public void setDeductTime(Object deductTime) {
            this.deductTime = deductTime;
        }

        public Object getTareTime() {
            return tareTime;
        }

        public void setTareTime(Object tareTime) {
            this.tareTime = tareTime;
        }

        public Object getGrossTime() {
            return grossTime;
        }

        public void setGrossTime(Object grossTime) {
            this.grossTime = grossTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getEditTime() {
            return editTime;
        }

        public void setEditTime(Object editTime) {
            this.editTime = editTime;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getTare() {
            return tare;
        }

        public void setTare(int tare) {
            this.tare = tare;
        }

        public int getCreater() {
            return creater;
        }

        public void setCreater(int creater) {
            this.creater = creater;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getEditer() {
            return editer;
        }

        public void setEditer(int editer) {
            this.editer = editer;
        }

        public int getTransportRecordId() {
            return transportRecordId;
        }

        public void setTransportRecordId(int transportRecordId) {
            this.transportRecordId = transportRecordId;
        }

        public String getTarePhoto() {
            return tarePhoto;
        }

        public void setTarePhoto(String tarePhoto) {
            this.tarePhoto = tarePhoto;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }


    public Object getFhTime() {
        return FhTime;
    }

    public void setFhTime(Object fhTime) {
        FhTime = fhTime;
    }

    public Object getShTime() {
        return ShTime;
    }

    public void setShTime(Object shTime) {
        ShTime = shTime;
    }

    public static class ReceiveRecordListBean {
        /**
         * adjustGross : 0
         * adjustTare : 0
         * gross : 20
         * grossPhoto : null
         * deductTime : null
         * tareTime : null
         * grossTime : null
         * remark :
         * editTime : null
         * shDeduct : 0
         * warehouseId : 0
         * createTime : 1517537731000
         * tare : 10
         * creater : 80
         * id : 101
         * editer : 0
         * transportRecordId : 105
         * tarePhoto :
         * status : 1
         */

        private int adjustGross;
        private int adjustTare;
        private int gross;
        private Object grossPhoto;
        private Object deductTime;
        private Object tareTime;
        private Object grossTime;
        private String remark;
        private Object editTime;
        private int shDeduct;
        private int warehouseId;
        private long createTime;
        private int tare;
        private int creater;
        private int id;
        private int editer;
        private int transportRecordId;
        private String tarePhoto;
        private int status;

        public int getAdjustGross() {
            return adjustGross;
        }

        public void setAdjustGross(int adjustGross) {
            this.adjustGross = adjustGross;
        }

        public int getAdjustTare() {
            return adjustTare;
        }

        public void setAdjustTare(int adjustTare) {
            this.adjustTare = adjustTare;
        }

        public int getGross() {
            return gross;
        }

        public void setGross(int gross) {
            this.gross = gross;
        }

        public Object getGrossPhoto() {
            return grossPhoto;
        }

        public void setGrossPhoto(Object grossPhoto) {
            this.grossPhoto = grossPhoto;
        }

        public Object getDeductTime() {
            return deductTime;
        }

        public void setDeductTime(Object deductTime) {
            this.deductTime = deductTime;
        }

        public Object getTareTime() {
            return tareTime;
        }

        public void setTareTime(Object tareTime) {
            this.tareTime = tareTime;
        }

        public Object getGrossTime() {
            return grossTime;
        }

        public void setGrossTime(Object grossTime) {
            this.grossTime = grossTime;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public Object getEditTime() {
            return editTime;
        }

        public void setEditTime(Object editTime) {
            this.editTime = editTime;
        }

        public int getShDeduct() {
            return shDeduct;
        }

        public void setShDeduct(int shDeduct) {
            this.shDeduct = shDeduct;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public int getTare() {
            return tare;
        }

        public void setTare(int tare) {
            this.tare = tare;
        }

        public int getCreater() {
            return creater;
        }

        public void setCreater(int creater) {
            this.creater = creater;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getEditer() {
            return editer;
        }

        public void setEditer(int editer) {
            this.editer = editer;
        }

        public int getTransportRecordId() {
            return transportRecordId;
        }

        public void setTransportRecordId(int transportRecordId) {
            this.transportRecordId = transportRecordId;
        }

        public String getTarePhoto() {
            return tarePhoto;
        }

        public void setTarePhoto(String tarePhoto) {
            this.tarePhoto = tarePhoto;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }

    public double getTotalSuttle() {
        return totalSuttle;
    }

    public void setTotalSuttle(double totalSuttle) {
        this.totalSuttle = totalSuttle;
    }

    public double getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(double planNumber) {
        this.planNumber = planNumber;
    }
}