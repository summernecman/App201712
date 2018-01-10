package com.siweisoft.heavycenter.data.netd.unit.list;

//by summer on 2018-01-10.

import com.android.lib.bean.BaseBean;

import java.util.List;

public class ListResBean extends BaseBean {


    /**
     * totalCount : 34
     * pageIndex : 0
     * pageCount : 2
     * pageSize : 20
     * results : [{"businessLicense":"","belongArea":"210000","companyName":"huluwaCompany7","remark":"","editTime":"","companyUrl":"","companyLng":0,"highCompany":32,"creditLevel":"","companyPropery":"","companyFax":"","id":36,"companyLat":0,"abbreviationName":"huluwa7","companyType":0,"contactId":0,"contactName":"huluwa","companyStatus":"0","postcode":"","taxRegistration":"","yhNum":1,"organizationCode":"","createTime":1515492446000,"companyAddress":"国和路与清源环路交叉口东南50米","creater":111,"companyPostbox":"","editer":0,"contactPhone":"13000000000","cpNum":0,"isMarket":0,"status":1},{"businessLicense":"","belongArea":"230000","companyName":"huluwaCompany6","remark":"","editTime":"","companyUrl":"","companyLng":0,"highCompany":32,"creditLevel":"","companyPropery":"","companyFax":"","id":35,"companyLat":0,"abbreviationName":"hulu6","companyType":0,"contactId":0,"contactName":"huluwa","companyStatus":"0","postcode":"","taxRegistration":"","yhNum":0,"organizationCode":"","createTime":1515491628000,"companyAddress":"上海市杨浦区国和路587号","creater":111,"companyPostbox":"","editer":0,"contactPhone":"13000000000","cpNum":0,"isMarket":0,"status":1}]
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
         * businessLicense :
         * belongArea : 210000
         * companyName : huluwaCompany7
         * remark :
         * editTime :
         * companyUrl :
         * companyLng : 0
         * highCompany : 32
         * creditLevel :
         * companyPropery :
         * companyFax :
         * id : 36
         * companyLat : 0
         * abbreviationName : huluwa7
         * companyType : 0
         * contactId : 0
         * contactName : huluwa
         * companyStatus : 0
         * postcode :
         * taxRegistration :
         * yhNum : 1
         * organizationCode :
         * createTime : 1515492446000
         * companyAddress : 国和路与清源环路交叉口东南50米
         * creater : 111
         * companyPostbox :
         * editer : 0
         * contactPhone : 13000000000
         * cpNum : 0
         * isMarket : 0
         * status : 1
         */

        private String businessLicense;
        private String belongArea;
        private String companyName;
        private String remark;
        private String editTime;
        private String companyUrl;
        private int companyLng;
        private int highCompany;
        private String creditLevel;
        private String companyPropery;
        private String companyFax;
        private int id;
        private int companyLat;
        private String abbreviationName;
        private int companyType;
        private int contactId;
        private String contactName;
        private String companyStatus;
        private String postcode;
        private String taxRegistration;
        private int yhNum;
        private String organizationCode;
        private long createTime;
        private String companyAddress;
        private int creater;
        private String companyPostbox;
        private int editer;
        private String contactPhone;
        private int cpNum;
        private int isMarket;
        private int status;

        public String getBusinessLicense() {
            return businessLicense;
        }

        public void setBusinessLicense(String businessLicense) {
            this.businessLicense = businessLicense;
        }

        public String getBelongArea() {
            return belongArea;
        }

        public void setBelongArea(String belongArea) {
            this.belongArea = belongArea;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public String getEditTime() {
            return editTime;
        }

        public void setEditTime(String editTime) {
            this.editTime = editTime;
        }

        public String getCompanyUrl() {
            return companyUrl;
        }

        public void setCompanyUrl(String companyUrl) {
            this.companyUrl = companyUrl;
        }

        public int getCompanyLng() {
            return companyLng;
        }

        public void setCompanyLng(int companyLng) {
            this.companyLng = companyLng;
        }

        public int getHighCompany() {
            return highCompany;
        }

        public void setHighCompany(int highCompany) {
            this.highCompany = highCompany;
        }

        public String getCreditLevel() {
            return creditLevel;
        }

        public void setCreditLevel(String creditLevel) {
            this.creditLevel = creditLevel;
        }

        public String getCompanyPropery() {
            return companyPropery;
        }

        public void setCompanyPropery(String companyPropery) {
            this.companyPropery = companyPropery;
        }

        public String getCompanyFax() {
            return companyFax;
        }

        public void setCompanyFax(String companyFax) {
            this.companyFax = companyFax;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCompanyLat() {
            return companyLat;
        }

        public void setCompanyLat(int companyLat) {
            this.companyLat = companyLat;
        }

        public String getAbbreviationName() {
            return abbreviationName;
        }

        public void setAbbreviationName(String abbreviationName) {
            this.abbreviationName = abbreviationName;
        }

        public int getCompanyType() {
            return companyType;
        }

        public void setCompanyType(int companyType) {
            this.companyType = companyType;
        }

        public int getContactId() {
            return contactId;
        }

        public void setContactId(int contactId) {
            this.contactId = contactId;
        }

        public String getContactName() {
            return contactName;
        }

        public void setContactName(String contactName) {
            this.contactName = contactName;
        }

        public String getCompanyStatus() {
            return companyStatus;
        }

        public void setCompanyStatus(String companyStatus) {
            this.companyStatus = companyStatus;
        }

        public String getPostcode() {
            return postcode;
        }

        public void setPostcode(String postcode) {
            this.postcode = postcode;
        }

        public String getTaxRegistration() {
            return taxRegistration;
        }

        public void setTaxRegistration(String taxRegistration) {
            this.taxRegistration = taxRegistration;
        }

        public int getYhNum() {
            return yhNum;
        }

        public void setYhNum(int yhNum) {
            this.yhNum = yhNum;
        }

        public String getOrganizationCode() {
            return organizationCode;
        }

        public void setOrganizationCode(String organizationCode) {
            this.organizationCode = organizationCode;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public String getCompanyAddress() {
            return companyAddress;
        }

        public void setCompanyAddress(String companyAddress) {
            this.companyAddress = companyAddress;
        }

        public int getCreater() {
            return creater;
        }

        public void setCreater(int creater) {
            this.creater = creater;
        }

        public String getCompanyPostbox() {
            return companyPostbox;
        }

        public void setCompanyPostbox(String companyPostbox) {
            this.companyPostbox = companyPostbox;
        }

        public int getEditer() {
            return editer;
        }

        public void setEditer(int editer) {
            this.editer = editer;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public int getCpNum() {
            return cpNum;
        }

        public void setCpNum(int cpNum) {
            this.cpNum = cpNum;
        }

        public int getIsMarket() {
            return isMarket;
        }

        public void setIsMarket(int isMarket) {
            this.isMarket = isMarket;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
