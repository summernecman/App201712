package com.siweisoft.heavycenter;

//by summer on 2018-01-11.

import android.content.Context;
import com.android.lib.util.GsonUtil;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoreDetail;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.data.netd.msg.list.MsgsResBean;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersReq;
import com.siweisoft.heavycenter.data.netd.order.list.OrdersRes;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Test {



    private static HashMap<String,String> testdata = new HashMap<>();

    public void init(){
        testData();
        initTestData();
    }

    private void testData(){
        LoginResBean loginResBean = new LoginResBean();
        loginResBean.setAbbreviationName("公司简称");
        loginResBean.setBindCompanyState(LoginResBean.BIND_UNIT_STATE_BINDED);
        loginResBean.setBindCompanyTime("2017-10-11");

        List<LoginResBean.BranchCompanyListBean> branchCompanyList = new ArrayList<>();
        LoginResBean.BranchCompanyListBean branchCompanyListBean = new LoginResBean.BranchCompanyListBean();
        branchCompanyListBean.setAbbreviationName("公司简称");
        branchCompanyListBean.setBranchId(40);
        branchCompanyListBean.setCompanyName("id为40的公司");
        branchCompanyList.add(branchCompanyListBean);
        loginResBean.setBranchCompanyList(branchCompanyList);
        loginResBean.setCompanyId(40);
        loginResBean.setCompanyName("公司名称");
        loginResBean.setDeviceId("fdjfoewhgiehgoir");
        loginResBean.setDeviceType(1);
        loginResBean.setLoginStatus(1);
        loginResBean.setPassWord("123456");
        loginResBean.setProductCount(10);
        loginResBean.setTel("18721607438");
        loginResBean.setTrueName("唐杰");
        loginResBean.setUserCount(10);
        loginResBean.setUserId(150);
        loginResBean.setUserPhoto("1747494443");
        loginResBean.setUserRole(LoginResBean.USER_ROLE_ADMIN);
        loginResBean.setUserType(UserTypeReqBean.非驾驶员);
        loginResBean.setVehicleCount(10);
        loginResBean.setWareHouseCount(10);
        LocalValue.save登录返回信息(loginResBean);

    }


    private void initTestData(){

    }


    public MsgsResBean getMsgsResBean(){
        MsgsResBean msgsResBean = new MsgsResBean();
        msgsResBean.setResults(new ArrayList<MsgsResBean.ResultsBean>());
        for(int i=0;i<10;i++){
            MsgsResBean.ResultsBean resultsBean = new MsgsResBean.ResultsBean();
            resultsBean.setAuditor(0);
            resultsBean.setAuditorName("授权人");
            resultsBean.setAuditState(0);
            resultsBean.setAuditTime("2017/12/12");
            resultsBean.setMessageCate("");
            resultsBean.setMessageContent("吊装国际有限公司的null向你们单位发了一个新的收货订单，请注意确认");
            resultsBean.setMessageId(10);
            resultsBean.setMessageType("消息类型");
            resultsBean.setSender(1);
            resultsBean.setSenderName("发送人");
            resultsBean.setSendTime(System.currentTimeMillis());
            msgsResBean.getResults().add(resultsBean);
        }
        return msgsResBean;
    }


    public StoresResBean getStoresResBean(){
        StoresResBean storesResBean = new StoresResBean();
        for(int i=0;i<10;i++){
            StoreDetail storeDetail = new StoreDetail();
            storeDetail.setAfterAdjust(123);
            storeDetail.setAllProfitNum("allprofit");
            storeDetail.setCompanyName("公司");
            storeDetail.setCurrentStock(123.6f);
            storeDetail.setLocate("南门");
            storeDetail.setMaxStock(1235.6f);
            storeDetail.setMinStock(35f);
            storeDetail.setProductId(1);
            storeDetail.setProductMaxStock(132f);
            storeDetail.setProductMinStock(34f);
            storeDetail.setSpecifications("300f");
            storeDetail.setStatus(1);
            storeDetail.setTodayOutStorage("344.5");
            storeDetail.setTodayProfitNum("profitnum");
            storeDetail.setTodayStorage("100.5");
            storeDetail.setWarehouseId(1);
            storeDetail.setWarehouseName("仓库1");
            storesResBean.getResults().add(storeDetail);
        }
        return storesResBean;
    }

    public StoreDetail getStoreDetail(){
        StoreDetail storeDetail = new StoreDetail();
        storeDetail.setAfterAdjust(123);
        storeDetail.setAllProfitNum("allprofit");
        storeDetail.setCompanyName("公司");
        storeDetail.setCurrentStock(123.6f);
        storeDetail.setLocate("南门");
        storeDetail.setMaxStock(1235.6f);
        storeDetail.setMinStock(35f);
        storeDetail.setProductId(1);
        storeDetail.setProductMaxStock(132f);
        storeDetail.setProductMinStock(34f);
        storeDetail.setSpecifications("300f");
        storeDetail.setStatus(1);
        storeDetail.setTodayOutStorage("344.5");
        storeDetail.setTodayProfitNum("profitnum");
        storeDetail.setTodayStorage("100.5");
        storeDetail.setWarehouseId(1);
        storeDetail.setWarehouseName("仓库1");
        return storeDetail;
    }

    public OrdersRes getOrdersRes(){
        OrdersRes ordersRes = new OrdersRes();
        for(int i=0;i<10;i++){
            OrdersRes.ResultsBean resultsBean = new OrdersRes.ResultsBean();
            resultsBean.setAddress("安徽省广德桃州镇");
            resultsBean.setFhdwName("发货单位");
            resultsBean.setFhdwQName("发货单位简称");
            resultsBean.setOrderId(1);
            resultsBean.setOrderNo("NO123reffd");
            resultsBean.setOrderStatus(OrdersReq.STATUS_ING);
            resultsBean.setOrderType("S");
            resultsBean.setPlanNumber(110);
            resultsBean.setPlanTime(System.currentTimeMillis());
            resultsBean.setProductName("沙子");
            resultsBean.setShdwName("收货单位");
            resultsBean.setShdwQName("收货单位简称");
            resultsBean.setSignRule("驾驶员直接签收");
            resultsBean.setSpecification("规格型号");
            ordersRes.getResults().add(resultsBean);
        }
        return ordersRes;
    }

    public static HashMap<String, String> getTestdata() {
        return testdata;
    }
}
