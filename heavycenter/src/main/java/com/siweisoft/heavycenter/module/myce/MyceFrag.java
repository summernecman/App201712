package com.siweisoft.heavycenter.module.myce;

//by summer on 2017-12-14.

import android.content.Intent;
import android.os.Environment;
import android.view.View;

import com.android.lib.network.bean.res.BaseResBean;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.LogUtil;
import com.android.lib.util.UriUtils;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.user.head.UpdateHeadResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.car.CarFrag;
import com.siweisoft.heavycenter.module.mana.good.GoodFrag;
import com.siweisoft.heavycenter.module.mana.store.StoreFrag;
import com.siweisoft.heavycenter.module.mana.user.UserFrag;
import com.siweisoft.heavycenter.module.myce.name.NameFrag;
import com.siweisoft.heavycenter.module.myce.unit.list.ListFrag;
import com.siweisoft.heavycenter.module.myce.base.info.InfoFrag;

import java.io.File;

import butterknife.OnClick;

public class MyceFrag extends AppFrag<MyceUIOpe,MyceDAOpe> {

    @Override
    public void initData() {
        super.initData();
        setIndex(((MainAct)(getActivity())).getP().getU().getPos_content());
        getP().getU().hideOrShowManageFunction(((MainAct)(getActivity())).getP().getD().isBindUnit());

    }

    public void init(){
        getP().getU().initUI(this);
        getP().getU().hideOrShowManageFunction(((MainAct)(getActivity())).getP().getD().isBindUnit());
    }

    @OnClick({R.id.item_car,R.id.item_good,R.id.item_store,R.id.item_user,R.id.item_unit,R.id.iv_nameedit,R.id.ftv_right,R.id.iv_head})
    public void onClick(View v){

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
                switch (LocalValue.getLoginInfo().getBindCompanyState()){
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
                        FragManager2.getInstance().setFinishAnim(R.anim.scale_in,R.anim.scale_out).finish(getBaseUIActivity(),MainAct.主界面);
                    }
                });
                FragManager2.getInstance().setStartAnim(R.anim.scale_in,R.anim.scale_out,R.anim.scale_in,R.anim.scale_out).start(getBaseUIActivity(),MainAct.主界面,MainAct.主界面ID,infoFrag);
                break;
            case R.id.iv_head:
                IntentUtil.getInstance().photoShowFromphone(this,01);
                break;
        }
        getBaseUIActivity().setMoudle(MainAct.主界面);
        ((MainAct)getActivity()).getP().getU().switchDrawer();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(data==null){
            return;
        }
        LogUtil.E(Environment.getDownloadCacheDirectory().getPath());
        File file = new File(UriUtils.getPath(getActivity(), data.getData()));
        LogUtil.E(file.exists());
        getP().getD().updateHead(UriUtils.getPath(getActivity(), data.getData()), new UINetAdapter<UpdateHeadResBean>(getActivity()) {
            @Override
            public void onNetFinish(boolean haveData, String url, BaseResBean baseResBean) {
                stopLoading();
                if(haveData){
                    LoginResBean loginResBean = LocalValue.getLoginInfo();
                    String s = baseResBean.getResult().toString();
                    if(s!=null){
                        if(s.trim().startsWith("[")){
                            s= s.substring(1,s.length()-1).trim();
                        }
                        loginResBean.setUserPhoto(s);
                        LocalValue.saveLoginInfo(loginResBean);
                        getP().getU().initUI(MyceFrag.this);
                    }
                }
            }
        });


    }
}
