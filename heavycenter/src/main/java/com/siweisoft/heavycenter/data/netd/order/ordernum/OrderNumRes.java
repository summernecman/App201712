package com.siweisoft.heavycenter.data.netd.order.ordernum;

//by summer on 2018-02-05.

import com.android.lib.bean.BaseBean;

public class OrderNumRes extends BaseBean {

    private int newShCount;

    private int newFhCount;

    private int ingShCount;

    private int ingFhCount;

    private int doneShCount;

    private int doneFhCount;

    private int totalPlan;

    private int totalSh;


    public int getNewShCount() {
        return newShCount;
    }

    public void setNewShCount(int newShCount) {
        this.newShCount = newShCount;
    }

    public int getNewFhCount() {
        return newFhCount;
    }

    public void setNewFhCount(int newFhCount) {
        this.newFhCount = newFhCount;
    }

    public int getIngShCount() {
        return ingShCount;
    }

    public void setIngShCount(int ingShCount) {
        this.ingShCount = ingShCount;
    }

    public int getIngFhCount() {
        return ingFhCount;
    }

    public void setIngFhCount(int ingFhCount) {
        this.ingFhCount = ingFhCount;
    }

    public int getDoneShCount() {
        return doneShCount;
    }

    public void setDoneShCount(int doneShCount) {
        this.doneShCount = doneShCount;
    }

    public int getDoneFhCount() {
        return doneFhCount;
    }

    public void setDoneFhCount(int doneFhCount) {
        this.doneFhCount = doneFhCount;
    }

    public int getTotalPlan() {
        return totalPlan;
    }

    public void setTotalPlan(int totalPlan) {
        this.totalPlan = totalPlan;
    }

    public int getTotalSh() {
        return totalSh;
    }

    public void setTotalSh(int totalSh) {
        this.totalSh = totalSh;
    }
}
