package com.siweisoft.heavycenter.data.netd.jpush;

//by summer on 2018-01-30.

import com.android.lib.bean.BaseBean;

public class WeightMsg extends BaseBean {


    /**
     * message : {"messageType":"bridge","content":"去送货","state":"s1","time":"2018-02-02 16:17:23","weigh":222,"order":{"orderId":66,"ysdId":107,"fhdwName":"老干爹","driverId":87,"specification":"C50","shdwName":"王尼玛","receiveCompanyId":37,"ysdNo":"15301","develiverCompanyId":39,"orderNo":"201802029","productName":"混凝土"},"fhTare":10}
     */

    private MessageBean message = new MessageBean();

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean extends BaseBean{
        /**
         * messageType : bridge
         * content : 去送货
         * state : s1
         * time : 2018-02-02 16:17:23
         * weigh : 222
         * order : {"orderId":66,"ysdId":107,"fhdwName":"老干爹","driverId":87,"specification":"C50","shdwName":"王尼玛","receiveCompanyId":37,"ysdNo":"15301","develiverCompanyId":39,"orderNo":"201802029","productName":"混凝土"}
         * fhTare : 10
         */

        private  double kc;
        private  double jz;
        private  double pz;

        private String weighResult;
        private String messageType;
        private String content;
        private String state;
        private String time;
        private double weigh;
        private OrderBean order = new OrderBean();
        private double fhTare;
        private String suttle;


        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public double getWeigh() {
            return weigh;
        }

        public void setWeigh(double weigh) {
            this.weigh = weigh;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public double getFhTare() {
            return fhTare;
        }

        public void setFhTare(double fhTare) {
            this.fhTare = fhTare;
        }

        public String getWeighResult() {
            return weighResult;
        }

        public void setWeighResult(String weighResult) {
            this.weighResult = weighResult;
        }

        public String getSuttle() {
            return suttle;
        }

        public void setSuttle(String suttle) {
            this.suttle = suttle;
        }

        public double getKc() {
            return kc;
        }

        public void setKc(double kc) {
            this.kc = kc;
        }

        public double getJz() {
            return jz;
        }

        public void setJz(double jz) {
            this.jz = jz;
        }

        public double getPz() {
            return pz;
        }

        public void setPz(double pz) {
            this.pz = pz;
        }

        public static class OrderBean extends BaseBean{
            /**
             * orderId : 66
             * ysdId : 107
             * fhdwName : 老干爹
             * driverId : 87
             * specification : C50
             * shdwName : 王尼玛
             * receiveCompanyId : 37
             * ysdNo : 15301
             * develiverCompanyId : 39
             * orderNo : 201802029
             * productName : 混凝土
             */

            private int orderId;
            private int ysdId;
            private String fhdwName;
            private int driverId;
            private String specification;
            private String shdwName;
            private int receiveCompanyId;
            private String ysdNo;
            private int develiverCompanyId;
            private String orderNo;
            private String productName;
            private String carLicenseNo;
            private String trueName;
            private String tel;


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

            public String getTel() {
                return tel;
            }

            public void setTel(String tel) {
                this.tel = tel;
            }

            public int getOrderId() {
                return orderId;
            }

            public void setOrderId(int orderId) {
                this.orderId = orderId;
            }

            public int getYsdId() {
                return ysdId;
            }

            public void setYsdId(int ysdId) {
                this.ysdId = ysdId;
            }

            public String getFhdwName() {
                return fhdwName;
            }

            public void setFhdwName(String fhdwName) {
                this.fhdwName = fhdwName;
            }

            public int getDriverId() {
                return driverId;
            }

            public void setDriverId(int driverId) {
                this.driverId = driverId;
            }

            public String getSpecification() {
                return specification;
            }

            public void setSpecification(String specification) {
                this.specification = specification;
            }

            public String getShdwName() {
                return shdwName;
            }

            public void setShdwName(String shdwName) {
                this.shdwName = shdwName;
            }

            public int getReceiveCompanyId() {
                return receiveCompanyId;
            }

            public void setReceiveCompanyId(int receiveCompanyId) {
                this.receiveCompanyId = receiveCompanyId;
            }

            public String getYsdNo() {
                return ysdNo;
            }

            public void setYsdNo(String ysdNo) {
                this.ysdNo = ysdNo;
            }

            public int getDeveliverCompanyId() {
                return develiverCompanyId;
            }

            public void setDeveliverCompanyId(int develiverCompanyId) {
                this.develiverCompanyId = develiverCompanyId;
            }

            public String getOrderNo() {
                return orderNo;
            }

            public void setOrderNo(String orderNo) {
                this.orderNo = orderNo;
            }

            public String getProductName() {
                return productName;
            }

            public void setProductName(String productName) {
                this.productName = productName;
            }
        }
    }
}
