package com.siweisoft.heavycenter.module.myce;

//by summer on 2017-12-14.

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.StringUtil;
import com.android.lib.util.UriUtils;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.mana.car.update.UpdateCarRes;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadReqBean;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.MainValue;
import com.siweisoft.heavycenter.module.mana.car.CarFrag;
import com.siweisoft.heavycenter.module.mana.good.GoodFrag;
import com.siweisoft.heavycenter.module.mana.store.StoreFrag;
import com.siweisoft.heavycenter.module.mana.user.list.UserFrag;
import com.siweisoft.heavycenter.module.myce.car.bind.BindFrag;
import com.siweisoft.heavycenter.module.myce.name.NameFrag;
import com.siweisoft.heavycenter.module.myce.sett.SetFrag;
import com.siweisoft.heavycenter.module.myce.unit.list.ListFrag;
import com.siweisoft.heavycenter.module.myce.base.info.InfoFrag;
import com.siweisoft.heavycenter.module.myce.unit.news.NewFrag;

import java.io.File;
import java.io.IOException;

import butterknife.OnClick;
import id.zelory.compressor.Compressor;

public class MyceFrag extends AppFrag<MyceUIOpe,MyceDAOpe> {

    public MyceFrag() {
        LogUtil.E(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LogUtil.E("onViewCreatedonViewCreatedonViewCreated");
    }

    @Override
    public void initNow() {
        super.initNow();
        init();
    }

    public void init(){
        getP().getU().initUI();
        getP().getU().hideOrShowManageFunction(((MainAct)(getActivity())).getP().getD().is绑定了单位());
    }

    public void initUINET(){
        getP().getD().getInfo(new UINetAdapter<LoginResBean>(getBaseUIAct()) {
            @Override
            public void onResult(boolean success, String msg, LoginResBean o) {
                super.onResult(success, msg, o);
                if(success){
                    LocalValue.save登录返回信息(o);
                    init();
                }
            }
        });
    }

    @OnClick({R.id.item_car,R.id.item_good,R.id.item_store,R.id.item_user,R.id.item_unit,R.id.iv_nameedit,R.id.ftv_right,R.id.iv_head,R.id.item_setting,R.id.iv_car,R.id.iv_dirver,R.id.item_driver})
    public void onClick(View v){

        //((MainAct)activity).getP().getU().unSelectBottomMenu();
        FragManager2.getInstance().clear(getBaseUIAct(), MainValue.主界面);
        switch (v.getId()){
            case R.id.login:
                break;
                case R.id.ftv_back:

                break;
            case R.id.item_car:
                FragManager2.getInstance().start(getBaseUIAct(),MainValue.主界面,MainValue.主界面ID,new CarFrag());

                break;
            case R.id.item_good:
                FragManager2.getInstance().start(getBaseUIAct(),MainValue.主界面,MainValue.主界面ID,new GoodFrag());
                break;
            case R.id.item_store:
                FragManager2.getInstance().start(getBaseUIAct(),MainValue.主界面,MainValue.主界面ID,new StoreFrag());
                break;
            case R.id.item_user:
                FragManager2.getInstance().start(getBaseUIAct(),MainValue.主界面,MainValue.主界面ID,new UserFrag());
                break;
            case R.id.item_unit:
                switch (LocalValue.get登录返回信息().getBindCompanyState()){
                    case LoginResBean.BIND_UNIT_STATE_BINDED:
                        Bundle bundle = new Bundle();
                        if(StringUtil.equals(LocalValue.get登录返回信息().getUserRole(),LoginResBean.USER_ROLE_SUPER_ADMIN)){
                            bundle.putString(ValueConstant.DATA_TYPE,NewFrag.修改单位信息);
                        }else{
                            bundle.putString(ValueConstant.DATA_TYPE,NewFrag.展示单位信息);
                        }
                        FragManager2.getInstance().start(getBaseUIAct(),MainValue.主界面,MainValue.主界面ID, new NewFrag(),bundle);
                        break;
                    case LoginResBean.BIND_UNIT_STATE_CHECK:
                    case LoginResBean.BIND_UNIT_STATE_REJECT:
                    case LoginResBean.BIND_UNIT_STATE_UNBIND:
                        FragManager2.getInstance().start(getBaseUIAct(),MainValue.主界面,MainValue.主界面ID,new ListFrag());
                        break;
                }
                break;
            case R.id.iv_nameedit:
                FragManager2.getInstance().start(getBaseUIAct(),MainValue.主界面,MainValue.主界面ID,new NameFrag());
                break;
            case R.id.ftv_right:
                InfoFrag infoFrag = new InfoFrag();
                infoFrag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        FragManager2.getInstance().setFinishAnim(R.anim.scale_in,R.anim.scale_out).finish(getBaseUIAct(),MainValue.主界面,false);
                            ((MainAct)getBaseUIAct()).getP().getU().switchDrawer(true);
                    }
                });

                FragManager2.getInstance().setShareName(ViewCompat.getTransitionName(v)).setShareElement(v).setStartAnim(R.anim.scale_in,R.anim.scale_out,R.anim.scale_in,R.anim.scale_out).start(getBaseUIAct(),MainValue.主界面,MainValue.主界面ID,infoFrag);
                break;
            case R.id.iv_head:
               // IntentUtil.getInstance().takeGetPhoto(activity);
                IntentUtil.getInstance().photoShowFromphone(this,01);
                break;
            case R.id.item_setting:
                FragManager2.getInstance().start(getBaseUIAct(),MainValue.主界面,MainValue.主界面ID,new SetFrag());
                break;
            case R.id.iv_car:
                //IntentUtil.getInstance().photoShowFromphone(this,02);
                break;
            case R.id.iv_dirver:
                //IntentUtil.getInstance().photoShowFromphone(this,03);
                break;
            case R.id.item_driver:
                FragManager2.getInstance().start(getBaseUIAct(),MainValue.主界面,MainValue.主界面ID,new BindFrag());
                break;
        }
        getBaseUIAct().setMoudle(MainValue.主界面);
        ((MainAct)getActivity()).getP().getU().switchDrawer();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        File file;
        try {
            file = new Compressor(getActivity()).compressToFile(new File(UriUtils.getPath(getBaseUIAct(), data.getData())));
        } catch (IOException e) {
            e.printStackTrace();
            file = new File(UriUtils.getPath(getBaseUIAct(), data.getData()));
        }
        String type = "";
        switch (requestCode){
            case 1:
                type = UpdateHeadReqBean.头像;
                break;
            case 2:
                type = UpdateHeadReqBean.车辆照片;
                break;
            case 3:
                type = UpdateHeadReqBean.行驶证照片;
                break;
        }
        final String finalType = type;
        getP().getD().uploadPhoto(file,type, new UINetAdapter<UpdateHeadResBean>(getBaseUIAct()) {
            @Override
            public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                stopLoading();
                if(haveData){
                    final LoginResBean loginResBean = LocalValue.get登录返回信息();
                    String s = baseResBean.getResult().toString();
                    if(s!=null){
                        if(s.trim().startsWith("[")){
                            s= s.substring(1,s.length()-1).trim();
                        }
                        switch (finalType){
                            case UpdateHeadReqBean.头像:
                                final String finalS = s;
                                getP().getD().updateHead(new UINetAdapter<UpdateHeadResBean>(getContext()) {
                                    @Override
                                    public void onResult(boolean success, String msg, UpdateHeadResBean o) {
                                        super.onResult(success, msg, o);
                                        loginResBean.setUserPhoto(finalS);
                                        LocalValue.save登录返回信息(loginResBean);
                                        getP().getU().initUI();
                                    }
                                });
                                break;
                            case UpdateHeadReqBean.车辆照片:
                                loginResBean.setVehicleLicense(s);
                                LocalValue.save登录返回信息(loginResBean);
                                getP().getD().updateCar(new UINetAdapter<UpdateCarRes>(getBaseUIAct()) {
                                    @Override
                                    public void onResult(boolean success, String msg, UpdateCarRes o) {
                                        super.onResult(success, msg, o);
                                        if(success){
                                            getP().getU().initUI();
                                        }
                                    }
                                });
                                break;
                            case UpdateHeadReqBean.行驶证照片:
                                loginResBean.setVehicleLicensePhoto(s);
                                LocalValue.save登录返回信息(loginResBean);
                                getP().getD().updateCar(new UINetAdapter<UpdateCarRes>(getBaseUIAct()) {
                                    @Override
                                    public void onResult(boolean success, String msg, UpdateCarRes o) {
                                        super.onResult(success, msg, o);
                                        if(success){
                                            getP().getU().initUI();
                                        }
                                    }
                                });
                                break;
                        }

                    }
                }
            }
        });


    }
}
