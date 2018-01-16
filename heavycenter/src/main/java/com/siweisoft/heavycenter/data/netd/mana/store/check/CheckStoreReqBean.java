package com.siweisoft.heavycenter.data.netd.mana.store.check;

//by summer on 2018-01-16.

import com.android.lib.network.bean.req.BaseReqBean;

public class CheckStoreReqBean extends BaseReqBean {

    private int userId;

    private String wareHouseListString;

    private int warehouseId;

    private int productId;

    private int beforeAdjust;

    private int afterAdjust;

    public CheckStoreReqBean() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getWareHouseListString() {
        return wareHouseListString;
    }

    public void setWareHouseListString(String wareHouseListString) {
        this.wareHouseListString = wareHouseListString;
    }

    public int getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        this.warehouseId = warehouseId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBeforeAdjust() {
        return beforeAdjust;
    }

    public void setBeforeAdjust(int beforeAdjust) {
        this.beforeAdjust = beforeAdjust;
    }

    public int getAfterAdjust() {
        return afterAdjust;
    }

    public void setAfterAdjust(int afterAdjust) {
        this.afterAdjust = afterAdjust;
    }

    public static class Check{

        private int warehouseId;

        private int productId;

        private float beforeAdjust ;

        private float afterAdjust;

        public Check() {
        }

        public int getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(int warehouseId) {
            this.warehouseId = warehouseId;
        }

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public float getBeforeAdjust() {
            return beforeAdjust;
        }

        public void setBeforeAdjust(float beforeAdjust) {
            this.beforeAdjust = beforeAdjust;
        }

        public float getAfterAdjust() {
            return afterAdjust;
        }

        public void setAfterAdjust(float afterAdjust) {
            this.afterAdjust = afterAdjust;
        }
    }
}
