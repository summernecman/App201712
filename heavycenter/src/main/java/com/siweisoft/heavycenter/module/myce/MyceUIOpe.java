package com.siweisoft.heavycenter.module.myce;

//by summer on 2017-12-14.

import android.content.Context;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.StringUtil;
import com.siweisoft.heavycenter.GlideApp;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.databinding.FragMyceBinding;

public class MyceUIOpe extends AppUIOpe<FragMyceBinding> {

    public MyceUIOpe(Context context) {
        super(context);
    }

    @Override
    public void initUI(BaseUIFrag baseUIFrag) {
        super.initUI(baseUIFrag);
        GlideApp.with(context).asBitmap().load(NetValue.获取地址(LocalValue.getLoginInfo().getUserPhoto())).placeholder(R.drawable.icon_hv_myce_head).centerCrop().into(bind.llHead.ivHead);
        bind.llHead.tvName.setText(StringUtil.getStr(LocalValue.getLoginInfo().getTrueName()));
        bind.llHead.tvPhone.setText(StringUtil.getStr(LocalValue.getLoginInfo().getTel()));
        bind.itemUnit.getLeftTV().setText(StringUtil.getStr(LocalValue.getLoginInfo().getAbbreviationName()));

        if(LocalValue.getLoginInfo().getUserType()== UserTypeReqBean.USER_TYPE_DRIVER){
            bind.llHead.tvRole.setText(UserTypeReqBean.USER_TYPE_DRIVER_CN);

            bind.itemStore.setVisibility(View.GONE);
            bind.itemGood.setVisibility(View.GONE);
            bind.itemUser.setVisibility(View.GONE);
            bind.itemCar.setVisibility(View.GONE);

            bind.llCar.setVisibility(View.VISIBLE);

            //  bind.tvEmptyweight.setText(LocalValue.getLoginInfo());
            bind.itemDriver.getLeftTV().setText(StringUtil.getStr(LocalValue.getLoginInfo().getCarLicenseNo()));
            bind.itemDriver.getMidTV().setText(StringUtil.getStr(LocalValue.getLoginInfo().getCarBrand()));
            //bind.itemDriver.getRightTV().setText(StringUtil.getStr(LocalValue.getLoginInfo().getEmptyWeight()));
            bind.tvEmptyweight.setText("自重: "+StringUtil.getStr(LocalValue.getLoginInfo().getEmptyWeight()));
            bind.tvMaxweight.setText("载重: "+StringUtil.getStr(LocalValue.getLoginInfo().getMaxCapacity()));

            switch (LocalValue.getLoginInfo().getBindCompanyState()){
                case LoginResBean.BIND_UNIT_STATE_BINDED:
                    bind.itemDriver.setVisibility(View.VISIBLE);
                    break;
                default:
                    bind.itemDriver.setVisibility(View.GONE);
                    break;
            }

        }else{
            bind.llHead.tvRole.setText(UserTypeReqBean.USER_TYPE_GENERAL_CN);
            switch (LocalValue.getLoginInfo().getUserRole()){
                case LoginResBean.USER_ROLE_ADMIN:
                    bind.llHead.tvRole.setText(LoginResBean.USER_ROLE_ADMIN_CN);
                    break;
                case LoginResBean.USER_ROLE_DRIVER:
                    bind.llHead.tvRole.setText(LoginResBean.USER_ROLE_DRIVER_CN);
                    break;
                case LoginResBean.USER_ROLE_GENERAL:
                    bind.llHead.tvRole.setText(LoginResBean.USER_ROLE_GENERAL_CN);
                    break;
                case LoginResBean.USER_ROLE_SUPER_ADMIN:
                    bind.llHead.tvRole.setText(LoginResBean.USER_ROLE_SUPER_ADMIN_CN);
                    break;
                case LoginResBean.USER_ROLE_SYS_ADMIN:
                    bind.llHead.tvRole.setText(LoginResBean.USER_ROLE_SYS_ADMIN_CN);
                    break;
            }

            bind.itemStore.setVisibility(View.VISIBLE);
            bind.itemGood.setVisibility(View.VISIBLE);
            bind.itemUser.setVisibility(View.VISIBLE);
            bind.itemCar.setVisibility(View.VISIBLE);


            bind.itemStore.getRightTV().setText(StringUtil.getStr(LocalValue.getLoginInfo().getWareHouseCount()));
            bind.itemGood.getRightTV().setText(StringUtil.getStr(LocalValue.getLoginInfo().getProductCount()));
            bind.itemUser.getRightTV().setText(StringUtil.getStr(LocalValue.getLoginInfo().getUserCount()));
            bind.itemCar.getRightTV().setText(StringUtil.getStr(LocalValue.getLoginInfo().getVehicleCount()));
            GlideApp.with(context).asBitmap().load(NetValue.获取地址(LocalValue.getLoginInfo().getDriverNoPhoto())).placeholder(R.drawable.icon_hv_car).centerCrop().into(bind.ivCar);
            GlideApp.with(context).asBitmap().load(NetValue.获取地址(LocalValue.getLoginInfo().getVehicleLicensePhoto())).placeholder(R.drawable.icon_hv_car).centerCrop().into(bind.ivDirver);
            bind.itemDriver.setVisibility(View.GONE);
            bind.llCar.setVisibility(View.GONE);


        }

        switch (LocalValue.getLoginInfo().getBindCompanyState()){
            case LoginResBean.BIND_UNIT_STATE_UNBIND:
                bind.itemUnit.getRightTV().setText(LoginResBean.BIND_UNIT_STATE_UNBIND_CN);
                break;
            case LoginResBean.BIND_UNIT_STATE_CHECK:
                bind.itemUnit.getRightTV().setText(LoginResBean.BIND_UNIT_STATE_CHECK_CN);
                break;
            case LoginResBean.BIND_UNIT_STATE_BINDED:
                bind.itemUnit.getRightTV().setText(LoginResBean.BIND_UNIT_STATE_BINDED_CN);
                break;
            case LoginResBean.BIND_UNIT_STATE_REJECT:
                bind.itemUnit.getRightTV().setText(LoginResBean.BIND_UNIT_STATE_REJECT_CN);
                break;
        }


    }

    public void hideOrShowManageFunction(boolean show){
        int vis = View.VISIBLE;
        if(show&& LocalValue.getLoginInfo().getUserType()==UserTypeReqBean.USER_TYPE_GENERAL&&!LoginResBean.USER_ROLE_GENERAL.equals(LocalValue.getLoginInfo().getUserRole())){
            vis = View.VISIBLE;
        }else{
            vis = View.GONE;
        }
        bind.itemCar.setVisibility(vis);
        bind.itemGood.setVisibility(vis);
        bind.itemStore.setVisibility(vis);
        bind.itemUser.setVisibility(vis);
    }


}
