package com.siweisoft.heavycenter.data.netd.jpush;

//by summer on 2018-01-30.

import com.android.lib.bean.BaseBean;

public class WeightMsg extends BaseBean {


    /**
     * message : {"content":"来拉货","state":"s0","order":{"orderId":50,"ysdId":"","fhdwName":"老干爹","driverId":87,"specification":"C50","shdwName":"王尼玛","receiveCompanyId":37,"ysdNo":"","develiverCompanyId":39,"orderNo":"201802013","productName":"混凝土"},"messageType":"bridge"}
     */

    private MessageBean message;

    public MessageBean getMessage() {
        return message;
    }

    public void setMessage(MessageBean message) {
        this.message = message;
    }

    public static class MessageBean {
        /**
         * content : 来拉货
         * state : s0
         * order : {"orderId":50,"ysdId":"","fhdwName":"老干爹","driverId":87,"specification":"C50","shdwName":"王尼玛","receiveCompanyId":37,"ysdNo":"","develiverCompanyId":39,"orderNo":"201802013","productName":"混凝土"}
         * messageType : bridge
         */

        private String content;
        private String state;
        private OrderBean order;
        private String messageType;

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

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public static class OrderBean extends BaseBean{
            /**
             * orderId : 50
             * ysdId :
             * fhdwName : 老干爹
             * driverId : 87
             * specification : C50
             * shdwName : 王尼玛
             * receiveCompanyId : 37
             * ysdNo :
             * develiverCompanyId : 39
             * orderNo : 201802013
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
