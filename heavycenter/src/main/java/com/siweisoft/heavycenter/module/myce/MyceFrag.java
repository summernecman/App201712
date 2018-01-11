package com.siweisoft.heavycenter.module.myce;

//by summer on 2017-12-14.

import android.view.View;

import com.android.lib.network.news.NetAdapter;
import com.android.lib.util.LogUtil;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.locd.LocalValue;
import com.siweisoft.heavycenter.data.netd.NetDataOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.unit.info.InfoReqBean;
import com.siweisoft.heavycenter.data.netd.unit.info.InfoResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.car.CarFrag;
import com.siweisoft.heavycenter.module.mana.good.GoodFrag;
import com.siweisoft.heavycenter.module.mana.store.StoreFrag;
import com.siweisoft.heavycenter.module.mana.user.UserFrag;
import com.siweisoft.heavycenter.module.myce.name.NameFrag;
import com.siweisoft.heavycenter.module.myce.unit.bind.BindFrag;
import com.siweisoft.heavycenter.module.myce.base.info.InfoFrag;

import butterknife.OnClick;

public class MyceFrag extends AppFrag<MyceUIOpe,MyceDAOpe> {

    @Override
    public void initData() {
        super.initData();
        setIndex(((MainAct)(getActivity())).getP().getU().getPos_content());
        getP().getU().hideOrShowManageFunction(((MainAct)(getActivity())).getP().getD().isRead());

        InfoReqBean reqBean = new InfoReqBean();
        reqBean.setId(32);
        NetDataOpe.Unit.getInfo(getActivity(),reqBean,new NetAdapter<InfoResBean>(getActivity()){
            @Override
            public void onResult(boolean success, String msg, InfoResBean o) {
                super.onResult(success, msg, o);
                LogUtil.E(o);
            }
        });










    }

    @OnClick({R.id.item_car,R.id.item_good,R.id.item_store,R.id.item_user,R.id.item_unit,R.id.iv_nameedit,R.id.ftv_right})
    public void onClick(View v){

        FragManager.getInstance().clearAll(((MainAct)(getActivity())).getSupportFragmentManager(),getIndex());
        switch (v.getId()){
            case R.id.login:
                break;
                case R.id.ftv_back:

                break;
            case R.id.item_car:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new CarFrag());
                ((MainAct)getActivity()).getP().getD().setIndex(getIndex());
                break;
            case R.id.item_good:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new GoodFrag());
                ((MainAct)getActivity()).getP().getD().setIndex(getIndex());
                break;
            case R.id.item_store:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new StoreFrag());
                ((MainAct)getActivity()).getP().getD().setIndex(getIndex());
                break;
            case R.id.item_user:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new UserFrag());
                ((MainAct)getActivity()).getP().getD().setIndex(getIndex());
                break;
            case R.id.item_unit:
                switch (LocalValue.getLoginInfo().getBindCompanyState()){
                    case LoginResBean.BIND_UNIT_STATE_BINDED:
                        FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new com.siweisoft.heavycenter.module.myce.unit.info.InfoFrag());
                        break;
                    case LoginResBean.BIND_UNIT_STATE_CHECK:
                    case LoginResBean.BIND_UNIT_STATE_REJECT:
                    case LoginResBean.BIND_UNIT_STATE_UNBIND:
                        FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new BindFrag());
                        break;
                }
                ((MainAct)getActivity()).getP().getD().setIndex(getIndex());
                break;
            case R.id.iv_nameedit:
                FragManager.getInstance().startFragment(getActivity().getSupportFragmentManager(), getIndex(),new NameFrag());
                ((MainAct)getActivity()).getP().getD().setIndex(getIndex());
                break;
            case R.id.ftv_right:
                InfoFrag infoFrag = new InfoFrag();
                infoFrag.setOnClickListener(this);
                FragManager.getInstance().addId(MainAct.ID_ALL_ROOT);
                FragManager.getInstance().cover(getActivity(), MainAct.ID_ALL_ROOT,infoFrag,R.anim.fade_in,R.anim.fade_out);
                break;
        }

        ((MainAct)getActivity()).getP().getU().switchDrawer();
    }
}
