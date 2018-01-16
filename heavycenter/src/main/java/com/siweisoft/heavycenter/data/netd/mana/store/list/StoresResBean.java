package com.siweisoft.heavycenter.data.netd.mana.store.list;

//by summer on 2018-01-11.

import com.android.lib.bean.BaseBean;

import java.util.List;

public class StoresResBean extends BaseBean {


    /**
     * totalCount : 1
     * pageIndex : -1000
     * pageCount : 1
     * pageSize : 1000
     * results : [{"locate":"","warehouseId":93,"companyName":"qwe","maxStock":250,"productMaxStock":0.1,"productMinStock":0.1,"currentStock":0.1,"warehouseName":"仓库1","specifications":"","productName":"","minStock":100.6,"status":1}]
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
         * locate :
         * warehouseId : 93
         * companyName : qwe
         * maxStock : 250
         * productMaxStock : 0.1
         * productMinStock : 0.1
         * currentStock : 0.1
         * warehouseName : 仓库1
         * specifications :
         * productName :
         * minStock : 100.6
         * status : 1
         */

        private String locate;
        private int warehouseId;
        private String companyName;
        private int maxStock;
        private float productMaxStock;
        private float productMinStock;
        private float currentStock;
        private String warehouseName;
        private String specifications;
        private String productName;
        private float minStock;
        private int status;

        public static final int STATUS_OFF = 0;

        public static final int STATUS_ON = 1;

        public static final String STATUS_OFF_CN = "禁用";

        public static final String STATUS_ON_CN = "启用";

        public String getLocate() {
            return locate;
        }

        public void setLocate(String locate) {
            this.locate = locate;
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getMaxStock() {
            return maxStock;
        }

        public void setMaxStock(int maxStock) {
            this.maxStock = maxStock;
        }

        public float getProductMaxStock() {
            return productMaxStock;
        }

        public void setProductMaxStock(float productMaxStock) {
            this.productMaxStock = productMaxStock;
        }

        public float getProductMinStock() {
            return productMinStock;
        }

        public void setProductMinStock(float productMinStock) {
            this.productMinStock = productMinStock;
        }

        public float getCurrentStock() {
            return currentStock;
        }

        public void setCurrentStock(float currentStock) {
            this.currentStock = currentStock;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
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

        public float getMinStock() {
            return minStock;
        }

        public void setMinStock(float minStock) {
            this.minStock = minStock;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public static int getStatusOff() {
            return STATUS_OFF;
        }

        public static int getStatusOn() {
            return STATUS_ON;
        }

        public static String getStatusOffCn() {
            return STATUS_OFF_CN;
        }

        public static String getStatusOnCn() {
            return STATUS_ON_CN;
        }
    }
}
