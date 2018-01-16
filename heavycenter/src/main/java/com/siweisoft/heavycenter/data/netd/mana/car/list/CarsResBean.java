package com.siweisoft.heavycenter.data.netd.mana.car.list;

//by summer on 2018-01-11.

import com.android.lib.bean.BaseBean;

import java.util.List;

public class CarsResBean extends BaseBean {


    /**
     * totalCount : 7
     * pageIndex : -1000
     * pageCount : 1
     * pageSize : 1000
     * results : [{"vehicleId":18,"carLicenseNo":"沪A22224","specifications":"","currentDriver":0,"carBrand":"900","maxCapacity":1000,"emptyWeight":1000,"icCard":"3000","status":1,"trueName":null,"tel":"","companyId":32,"companyName":"huluwaCompany3"}]
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

    public static class ResultsBean extends BaseBean{
        /**
         * vehicleId : 18
         * carLicenseNo : 沪A22224
         * specifications :
         * currentDriver : 0
         * carBrand : 900
         * maxCapacity : 1000
         * emptyWeight : 1000
         * icCard : 3000
         * status : 1
         * trueName : null
         * tel :
         * companyId : 32
         * companyName : huluwaCompany3
         */

        public static final int STATUS_ON = 1;

        public static final int STATUS_OFF = 0;

        public static final String STATUS_ON_CN = "启用";

        public static final String STATUS_OFF_CN = "停用";

        private int vehicleId;
        private String carLicenseNo;
        private String specifications;
        private int currentDriver;
        private String carBrand;
        private int maxCapacity;
        private int emptyWeight;
        private String icCard;
        private int status;
        private String trueName;
        private String tel;
        private int companyId;
        private String companyName;

        public int getVehicleId() {
            return vehicleId;
        }

        public void setVehicleId(int vehicleId) {
            this.vehicleId = vehicleId;
        }

        public String getCarLicenseNo() {
            return carLicenseNo;
        }

        public void setCarLicenseNo(String carLicenseNo) {
            this.carLicenseNo = carLicenseNo;
        }

        public String getSpecifications() {
            return specifications;
        }

        public void setSpecifications(String specifications) {
            this.specifications = specifications;
        }

        public int getCurrentDriver() {
            return currentDriver;
        }

        public void setCurrentDriver(int currentDriver) {
            this.currentDriver = currentDriver;
        }

        public String getCarBrand() {
            return carBrand;
        }

        public void setCarBrand(String carBrand) {
            this.carBrand = carBrand;
        }

        public int getMaxCapacity() {
            return maxCapacity;
        }

        public void setMaxCapacity(int maxCapacity) {
            this.maxCapacity = maxCapacity;
        }

        public int getEmptyWeight() {
            return emptyWeight;
        }

        public void setEmptyWeight(int emptyWeight) {
            this.emptyWeight = emptyWeight;
        }

        public String getIcCard() {
            return icCard;
        }

        public void setIcCard(String icCard) {
            this.icCard = icCard;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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

        public int getCompanyId() {
            return companyId;
        }

        public void setCompanyId(int companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }
    }
}
