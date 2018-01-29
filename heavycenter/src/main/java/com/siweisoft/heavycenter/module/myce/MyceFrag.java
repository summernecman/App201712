package com.siweisoft.heavycenter.module.myce;

//by summer on 2017-12-14.

import android.content.Intent;
import android.view.View;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.IntentUtil;
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
import com.siweisoft.heavycenter.module.mana.car.CarFrag;
import com.siweisoft.heavycenter.module.mana.good.GoodFrag;
import com.siweisoft.heavycenter.module.mana.store.StoreFrag;
import com.siweisoft.heavycenter.module.mana.user.UserFrag;
import com.siweisoft.heavycenter.module.myce.car.bind.BindFrag;
import com.siweisoft.heavycenter.module.myce.name.NameFrag;
import com.siweisoft.heavycenter.module.myce.sett.SetFrag;
import com.siweisoft.heavycenter.module.myce.unit.list.ListFrag;
import com.siweisoft.heavycenter.module.myce.base.info.InfoFrag;

import java.io.File;
import java.io.IOException;

import butterknife.OnClick;
import id.zelory.compressor.Compressor;

public class MyceFrag extends AppFrag<MyceUIOpe,MyceDAOpe> {

    @Override
    public void initData() {
        super.initData();
        init();
    }

    public void init(){
        getP().getU().initUI(this);
        getP().getU().hideOrShowManageFunction(((MainAct)(activity)).getP().getD().isBindUnit());
    }

    public void initUINET(){
        getP().getD().getInfo(new UINetAdapter<LoginResBean>(activity) {
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
        FragManager2.getInstance().clear(getBaseUIActivity(),MainAct.主界面);
        switch (v.getId()){
            case R.id.login:
                break;
                case R.id.ftv_back:

                break;
            case R.id.item_car:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,new CarFrag());

                break;
            case R.id.item_good:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,new GoodFrag());
                break;
            case R.id.item_store:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,new StoreFrag());
                break;
            case R.id.item_user:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,new UserFrag());
                break;
            case R.id.item_unit:
                switch (LocalValue.get登录返回信息().getBindCompanyState()){
                    case LoginResBean.BIND_UNIT_STATE_BINDED:
                        FragManager2.getInstance().start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,new com.siweisoft.heavycenter.module.myce.unit.info.InfoFrag());
                        break;
                    case LoginResBean.BIND_UNIT_STATE_CHECK:
                    case LoginResBean.BIND_UNIT_STATE_REJECT:
                    case LoginResBean.BIND_UNIT_STATE_UNBIND:
                        FragManager2.getInstance().start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,new ListFrag());
                        break;
                }
                break;
            case R.id.iv_nameedit:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,new NameFrag());
                break;
            case R.id.ftv_right:
                InfoFrag infoFrag = new InfoFrag();
                infoFrag.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        FragManager2.getInstance().setFinishAnim(R.anim.scale_in,R.anim.scale_out).finish(getBaseUIActivity(),MainAct.主界面,false);
                    }
                });
                FragManager2.getInstance().setStartAnim(R.anim.scale_in,R.anim.scale_out,R.anim.scale_in,R.anim.scale_out).start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,infoFrag);
                break;
            case R.id.iv_head:
               // IntentUtil.getInstance().takeGetPhoto(activity);
                IntentUtil.getInstance().photoShowFromphone(this,01);
                break;
            case R.id.item_setting:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,new SetFrag());
                break;
            case R.id.iv_car:
                IntentUtil.getInstance().photoShowFromphone(this,02);
                break;
            case R.id.iv_dirver:
                IntentUtil.getInstance().photoShowFromphone(this,03);
                break;
            case R.id.item_driver:
                FragManager2.getInstance().start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,new BindFrag());
                break;
        }
        getBaseUIActivity().setMoudle(MainAct.主界面);
        ((MainAct)activity).getP().getU().switchDrawer();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        File file;
        try {
            file = new Compressor(getActivity()).compressToFile(new File(UriUtils.getPath(activity, data.getData())));
        } catch (IOException e) {
            e.printStackTrace();
            file = new File(UriUtils.getPath(activity, data.getData()));
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
        getP().getD().uploadPhoto(file,type, new UINetAdapter<UpdateHeadResBean>(activity) {
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
                                        getP().getU().initUI(MyceFrag.this);
                                    }
                                });
                                break;
                            case UpdateHeadReqBean.车辆照片:
                                loginResBean.setVehicleLicense(s);
                                LocalValue.save登录返回信息(loginResBean);
                                getP().getD().updateCar(new UINetAdapter<UpdateCarRes>(activity) {
                                    @Override
                                    public void onResult(boolean success, String msg, UpdateCarRes o) {
                                        super.onResult(success, msg, o);
                                        if(success){
                                            getP().getU().initUI(MyceFrag.this);
                                        }
                                    }
                                });
                                break;
                            case UpdateHeadReqBean.行驶证照片:
                                loginResBean.setVehicleLicensePhoto(s);
                                LocalValue.save登录返回信息(loginResBean);
                                getP().getD().updateCar(new UINetAdapter<UpdateCarRes>(activity) {
                                    @Override
                                    public void onResult(boolean success, String msg, UpdateCarRes o) {
                                        super.onResult(success, msg, o);
                                        if(success){
                                            getP().getU().initUI(MyceFrag.this);
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
