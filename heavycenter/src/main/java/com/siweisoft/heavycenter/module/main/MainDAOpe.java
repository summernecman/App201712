package com.siweisoft.heavycenter.module.main;

//by summer on 17-08-23.

import android.Manifest;
import android.content.Context;
import android.widget.RelativeLayout;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.system.PermissionUtil;
import com.android.lib.view.bottommenu.BottomMenuBean;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppDAOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.module.main.msg.MsgFrag;
import com.siweisoft.heavycenter.module.main.order.OrderFrag;
import com.siweisoft.heavycenter.module.main.store.StoreFrag;
import com.siweisoft.heavycenter.module.main.trans.TransFrag;
import com.siweisoft.heavycenter.module.main.weigts.WeigtsFrag;
import com.siweisoft.heavycenter.module.myce.MyceFrag;

import java.util.ArrayList;
import java.util.List;

public class MainDAOpe extends AppDAOpe {

    private ArrayList<BottomMenuBean> menudata = new ArrayList<>();

    private PermissionUtil permissionUtil;

    private int index=0;

    MyceFrag myceFrag = new MyceFrag();


    public MainDAOpe(Context context) {
        super(context);
        initBottomMenuViewData();
        permissionUtil= new PermissionUtil();
    }

    protected ArrayList<BottomMenuBean> initBottomMenuViewData(){
        if(menudata==null){
            menudata = new ArrayList<>();
        }
        menudata.clear();
        RelativeLayout v0 = new RelativeLayout(context);v0.setId(11111+0);
        BaseUIFrag fragment0 = new WeigtsFrag(); fragment0.setIndex(0);
        menudata.add(new BottomMenuBean(MainAct.地磅, R.drawable.drawable_main_bottom_weight,new WeigtsFrag(),v0, context.getResources().getColorStateList(R.color.color_hv_bottom_select)));

        RelativeLayout v1 = new RelativeLayout(context);v1.setId(11111+1);
        BaseUIFrag fragment1 = new TransFrag(); fragment1.setIndex(1);
        menudata.add(new BottomMenuBean(MainAct.运输单, R.drawable.drawable_main_bottom_trans,new TransFrag(),v1,context.getResources().getColorStateList(R.color.color_hv_bottom_select)));

        RelativeLayout v2 = new RelativeLayout(context);v2.setId(11111+2);
        BaseUIFrag fragment2 = new TransFrag(); fragment2.setIndex(2);
        menudata.add(new BottomMenuBean(MainAct.订单, R.drawable.drawable_main_bottom_order,new OrderFrag(),v2,context.getResources().getColorStateList(R.color.color_hv_bottom_select)));

        RelativeLayout v3 = new RelativeLayout(context);v3.setId(11111+3);
        BaseUIFrag fragment3 = new TransFrag(); fragment3.setIndex(3);
        menudata.add(new BottomMenuBean(MainAct.仓库, R.drawable.drawable_main_bottom_store,new StoreFrag(),v3,context.getResources().getColorStateList(R.color.color_hv_bottom_select)));

        RelativeLayout v4 = new RelativeLayout(context);v4.setId(11111+4);
        BaseUIFrag fragment4 = new TransFrag(); fragment4.setIndex(4);
        menudata.add(new BottomMenuBean(MainAct.消息, R.drawable.drawable_main_bottom_msg,new MsgFrag(),v4,context.getResources().getColorStateList(R.color.color_hv_bottom_select)));
        return menudata;
    }


    public MyceFrag getMyceFrag() {
        return myceFrag;
    }

    private String[] permissions = new String[]{
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.VIBRATE,
            Manifest.permission.INTERNET,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE,
            Manifest.permission.WAKE_LOCK,
            Manifest.permission.MODIFY_AUDIO_SETTINGS,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.DISABLE_KEYGUARD
    };

    public String[] getPermissions() {
        return permissions;
    }


    public ArrayList<BottomMenuBean> getMenudata() {
        return menudata;
    }

    public PermissionUtil getPermissionUtil() {
        return permissionUtil;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isBindUnit() {
        //绑定了单位== true
        if(LocalValue.getLoginInfo().getBindCompanyState()== LoginResBean.BIND_UNIT_STATE_BINDED){
            return true;
        }
        return false;
    }


    public void testData(){
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
        loginResBean.setUserType(UserTypeReqBean.USER_TYPE_GENERAL);
        loginResBean.setVehicleCount(10);
        loginResBean.setWareHouseCount(10);
        LocalValue.saveLoginInfo(loginResBean);
    }
}
