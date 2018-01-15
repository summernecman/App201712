package com.siweisoft.heavycenter.data.netd.msg.list;

//by summer on 2018-01-11.

import com.android.lib.bean.BaseBean;

import java.util.List;

public class MsgsResBean extends BaseBean {


    /**
     * totalCount : 139
     * pageIndex : 0
     * pageCount : 7
     * pageSize : 20
     * results : [{"senderName":"yinguo123","messageType":"order","sender":95,"auditTime":"","auditor":0,"messageId":5478,"messageCate":"1","auditState":0,"auditorName":"","messageContent":"吊装国际有限公司的null向你们单位发了一个新的收货订单，请注意确认","sendTime":1515656604000}]
     */

    private int totalCount;
    private int pageIndex;
    private int pageCount;
    private int pageSize;
    private List<ResultsBean> results;

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

    public static class ResultsBean {
        /**
         * senderName : yinguo123
         * messageType : order
         * sender : 95
         * auditTime :
         * auditor : 0
         * messageId : 5478
         * messageCate : 1
         * auditState : 0
         * auditorName :
         * messageContent : 吊装国际有限公司的null向你们单位发了一个新的收货订单，请注意确认
         * sendTime : 1515656604000
         */

        public static final String MSG_TYPE_UPDATE = "update";
        public static final String MSG_TYPE_INVITE_D = "inviteD";
        public static final String MSG_TYPE_INVITE_U = "inviteU";
        public static final String MSG_TYPE_INVITE_M = "inviteM";
        public static final String MSG_TYPE_APPLY_D = "applyD";
        public static final String MSG_TYPE_APPLY_U = "applyU";
        public static final String MSG_TYPE_INFO = "info";


        public static final int AUDITOR_STATE_CHECKING = 0;//未审核

        public static final int AUDITOR_STATE_AGREEED = 1;//接受

        public static final int AUDITOR_STATE_REJECT = 2;//拒绝

        public static final int AUDITOR_STATE_NONEED = 3;//不用审核

        public static final String AUDITOR_STATE_CHECKING_CN = "";//未审核

        public static final String AUDITOR_STATE_AGREEED_CN = "已接受";//接受

        public static final String AUDITOR_STATE_REJECT_CN = "已拒绝";//拒绝

        public static final String AUDITOR_STATE_NONEED_CN = "不用审核";//不用审核


        private String senderName;
        private String messageType;
        private int sender;
        private String auditTime;
        private int auditor;
        private int messageId;
        private String messageCate;
        private int auditState;
        private String auditorName;
        private String messageContent;
        private long sendTime;

        public String getSenderName() {
            return senderName;
        }

        public void setSenderName(String senderName) {
            this.senderName = senderName;
        }

        public String getMessageType() {
            return messageType;
        }

        public void setMessageType(String messageType) {
            this.messageType = messageType;
        }

        public int getSender() {
            return sender;
        }

        public void setSender(int sender) {
            this.sender = sender;
        }

        public String getAuditTime() {
            return auditTime;
        }

        public void setAuditTime(String auditTime) {
            this.auditTime = auditTime;
        }

        public int getAuditor() {
            return auditor;
        }

        public void setAuditor(int auditor) {
            this.auditor = auditor;
        }

        public int getMessageId() {
            return messageId;
        }

        public void setMessageId(int messageId) {
            this.messageId = messageId;
        }

        public String getMessageCate() {
            return messageCate;
        }

        public void setMessageCate(String messageCate) {
            this.messageCate = messageCate;
        }

        public int getAuditState() {
            return auditState;
        }

        public void setAuditState(int auditState) {
            this.auditState = auditState;
        }

        public String getAuditorName() {
            return auditorName;
        }

        public void setAuditorName(String auditorName) {
            this.auditorName = auditorName;
        }

        public String getMessageContent() {
            return messageContent;
        }

        public void setMessageContent(String messageContent) {
            this.messageContent = messageContent;
        }

        public long getSendTime() {
            return sendTime;
        }

        public void setSendTime(long sendTime) {
            this.sendTime = sendTime;
        }
    }
}
