package com.siweisoft.heavycenter.data.netd.jpush;

//by summer on 2018-01-30.

import com.android.lib.bean.BaseBean;

public class WeightMsg extends BaseBean {


    /**
     * message : {"messageType":"bridge","content":"来拉货","state":"s0","time":"2018-01-30 16:49:02","weigh":20,"order":{"orderId":20,"ysdId":18,"fhdwName":"老干爹","specification":"300f","shdwName":"王尼玛","receiveCompanyId":37,"ysdNo":"","develiverCompanyId":39,"orderNo":"201801308","productName":"水泥"}}
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
         * messageType : bridge
         * content : 来拉货
         * state : s0
         * time : 2018-01-30 16:49:02
         * weigh : 20
         * order : {"orderId":20,"ysdId":18,"fhdwName":"老干爹","specification":"300f","shdwName":"王尼玛","receiveCompanyId":37,"ysdNo":"","develiverCompanyId":39,"orderNo":"201801308","productName":"水泥"}
         */

        private String messageType;
        private String content;
        private String state;
        private String time;
        private int weigh;
        private OrderBean order;
        private float fhTare;//发货皮重

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

        public int getWeigh() {
            return weigh;
        }

        public void setWeigh(int weigh) {
            this.weigh = weigh;
        }

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public float getFhTare() {
            return fhTare;
        }

        public void setFhTare(float fhTare) {
            this.fhTare = fhTare;
        }

        public static class OrderBean {
            /**
             * orderId : 20
             * ysdId : 18
             * fhdwName : 老干爹
             * specification : 300f
             * shdwName : 王尼玛
             * receiveCompanyId : 37
             * ysdNo :
             * develiverCompanyId : 39
             * orderNo : 201801308
             * productName : 水泥
             */

            private int orderId;
            private int ysdId;
            private String fhdwName;
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
