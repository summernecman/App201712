package com.siweisoft.heavycenter.module.mana.user.news;

//by summer on 2017-12-19.

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;
import com.android.lib.util.NullUtil;
import com.android.lib.util.OjectUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.ToastUtil;
import com.android.lib.util.data.DateFormatUtil;
import com.siweisoft.heavycenter.GlideApp;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.locd.scan.user.UserInfo;
import com.siweisoft.heavycenter.data.netd.NetValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.user.add.AddUserReqBean;
import com.siweisoft.heavycenter.data.netd.user.usertype.UserTypeReqBean;
import com.siweisoft.heavycenter.databinding.FragManaUserDetailBinding;
import com.siweisoft.heavycenter.databinding.FragManaUserInfoBinding;
import com.siweisoft.heavycenter.databinding.FragManaUserNewBinding;

import java.util.ArrayList;
import java.util.Date;

public class NewUIOpe extends AppUIOpe<FragManaUserDetailBinding> implements View.OnClickListener{

    ArrayList<View> views = new ArrayList<>();


    FragManaUserNewBinding fragManaUserNewBinding;

    FragManaUserInfoBinding fragManaUserInfoBinding;




    public void initUI() {
        super.initUI();
        switch (getFrag().getArguments().getString(ValueConstant.DATA_TYPE)){
            case NewUserValue.新建用户:

                fragManaUserNewBinding = DataBindingUtil.bind(LayoutInflater.from(getActivity()).inflate(R.layout.frag_mana_user_new,null));
                bind.llUserdetail.addView(fragManaUserNewBinding.getRoot(),new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

                fragManaUserNewBinding.one.setOnClickListener(this);views.add(fragManaUserNewBinding.one);fragManaUserNewBinding.one.setTag(R.id.data, LoginResBean.USER_ROLE_GENERAL);
                fragManaUserNewBinding.two.setOnClickListener(this);views.add(fragManaUserNewBinding.two);fragManaUserNewBinding.two.setTag(R.id.data, LoginResBean.USER_ROLE_DRIVER);
                fragManaUserNewBinding.three.setOnClickListener(this);views.add(fragManaUserNewBinding.three);fragManaUserNewBinding.three.setTag(R.id.data, LoginResBean.USER_ROLE_ADMIN);
                fragManaUserNewBinding.one.setSelected(true);
                break;
            case NewUserValue.用户信息:
                fragManaUserInfoBinding = DataBindingUtil.bind(LayoutInflater.from(getActivity()).inflate(R.layout.frag_mana_user_info,null));
                bind.llUserdetail.addView(fragManaUserInfoBinding.getRoot(),new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                break;

        }

    }

    public void initUserInfo(LoginResBean userInfo){
        GlideApp.with(context).asBitmap().load(NetValue.获取地址(userInfo.getUserPhoto())).placeholder(R.drawable.icon_hv_myce_head).centerCrop().into( fragManaUserInfoBinding.head);
        fragManaUserInfoBinding.itemName.setMidEtTxt(StringUtil.getStr(userInfo.getTrueName()));
        fragManaUserInfoBinding.itemPhone.setMidEtTxt(StringUtil.getStr(userInfo.getTel()));

        if(OjectUtil.equals(userInfo.getUserType(), UserTypeReqBean.驾驶员)){
            fragManaUserInfoBinding.llRole.setVisibility(View.GONE);
            fragManaUserInfoBinding.llImage.setVisibility(View.VISIBLE);
            GlideApp.with(context).asBitmap().load(NetValue.获取地址(userInfo.getVehicleLicensePhoto())).placeholder(R.drawable.icon_hv_car).centerCrop().into(fragManaUserInfoBinding.ivCarlicenseno);
            GlideApp.with(context).asBitmap().load(NetValue.获取地址(userInfo.getVehiclePhoto())).placeholder(R.drawable.icon_hv_driveid).centerCrop().into(fragManaUserInfoBinding.ivDriverno);
        }else{
            fragManaUserInfoBinding.llRole.setVisibility(View.VISIBLE);
            fragManaUserInfoBinding.llImage.setVisibility(View.GONE);
            switch (userInfo.getUserRole()){
                case LoginResBean.USER_ROLE_ADMIN:
                case LoginResBean.USER_ROLE_SUPER_ADMIN:
                case LoginResBean.USER_ROLE_SYS_ADMIN:
                    fragManaUserInfoBinding.tvUser.setSelected(true);
                    fragManaUserInfoBinding.tvMana.setSelected(false);
                    break;
                case LoginResBean.USER_ROLE_GENERAL:
                case LoginResBean.USER_ROLE_DRIVER:
                    fragManaUserInfoBinding.tvUser.setSelected(false);
                    fragManaUserInfoBinding.tvMana.setSelected(true);
                    break;
            }
        }
        if(userInfo.getBindCompanyTime()!=null){
            fragManaUserInfoBinding.tvDate.setText("由管理员审核于"+ StringUtil.getStr(DateFormatUtil.getdDateStr(DateFormatUtil.YYYY_MM_DD_HH_MM,new Date(userInfo.getBindCompanyTime()))));
        }
    }

    public boolean canGo(){
        if(NullUtil.isStrEmpty(fragManaUserNewBinding.itemTel.getMidET().getText().toString())){
            ToastUtil.getInstance().showShort(getActivity(),"请输入手机号");
            return false;
        }
        if(fragManaUserNewBinding.itemTel.getMidET().getText().toString().length()!=11){
            ToastUtil.getInstance().showShort(getActivity(),"手机号格式输入有误");
            return false;
        }
        return true;
    }

    public void onClick(View v){
        for(int i=0;i<views.size();i++){
            if(v.getId()==views.get(i).getId()){
                views.get(i).setSelected(true);
            }else{
                views.get(i).setSelected(false);
            }
        }
    }

    public AddUserReqBean getUser(AddUserReqBean reqBean){
        for(int i=0;i<views.size();i++){
            if(views.get(i).isSelected()){
                reqBean.setUserRole((String) views.get(i).getTag(R.id.data));
              break;
            }
        }
        reqBean.setTel(fragManaUserNewBinding.itemTel.getMidET().getText().toString());
        return reqBean;
    }
}
