package com.siweisoft.heavycenter.module.mana.user.news;

//by summer on 2017-12-19.

import android.content.Context;
import android.view.View;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.NullUtil;
import com.android.lib.util.ToastUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.user.add.AddUserReqBean;
import com.siweisoft.heavycenter.databinding.FragManaUserNewBinding;

import java.util.ArrayList;

public class NewUIOpe extends AppUIOpe<FragManaUserNewBinding> implements View.OnClickListener{

    ArrayList<View> views = new ArrayList<>();


    public void initUI() {
        super.initUI();
        bind.itemName.setVisibility(View.GONE);
        bind.one.setOnClickListener(this);views.add(bind.one);bind.one.setTag(R.id.data, LoginResBean.USER_ROLE_GENERAL);
        bind.two.setOnClickListener(this);views.add(bind.two);bind.two.setTag(R.id.data, LoginResBean.USER_ROLE_DRIVER);
        bind.three.setOnClickListener(this);views.add(bind.three);bind.three.setTag(R.id.data, LoginResBean.USER_ROLE_ADMIN);
        bind.one.setSelected(true);

    }

    public boolean canGo(){
        if(NullUtil.isStrEmpty(bind.itemTel.getMidET().getText().toString())){
            ToastUtil.getInstance().showShort(getActivity(),"请输入手机号");
            return false;
        }
        if(bind.itemTel.getMidET().getText().toString().length()!=11){
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
        reqBean.setTel(bind.itemTel.getMidET().getText().toString());
        return reqBean;
    }
}
