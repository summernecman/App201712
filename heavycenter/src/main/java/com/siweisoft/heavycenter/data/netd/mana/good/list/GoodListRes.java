package com.siweisoft.heavycenter.data.netd.mana.good.list;

//by summer on 2018-01-17.

import com.android.lib.bean.BaseBean;

import java.util.List;

public class GoodListRes extends BaseBean{


    /**
     * totalCount : 1
     * pageIndex : 0
     * pageCount : 1
     * pageSize : 20
     * results : [{"belongArea":"漯河市,商丘市","specifications":"P\u2022O42.5R","warehouseName":"仓库2","productInfoId":68,"productName":"普通硅酸盐水泥"}]
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
         * belongArea : 漯河市,商丘市
         * specifications : P•O42.5R
         * warehouseName : 仓库2
         * productInfoId : 68
         * productName : 普通硅酸盐水泥
         */

        private String belongArea;
        private String specifications;
        private String warehouseName;
        private int productInfoId;
        private String productName;

        public String getBelongArea() {
            return belongArea;
        }

        public void setBelongArea(String belongArea) {
            this.belongArea = belongArea;
        }

        public String getSpecifications() {
            return specifications;
        }

        public void setSpecifications(String specifications) {
            this.specifications = specifications;
        }

        public String getWarehouseName() {
            return warehouseName;
        }

        public void setWarehouseName(String warehouseName) {
            this.warehouseName = warehouseName;
        }

        public int getProductInfoId() {
            return productInfoId;
        }

        public void setProductInfoId(int productInfoId) {
            this.productInfoId = productInfoId;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }
    }
}