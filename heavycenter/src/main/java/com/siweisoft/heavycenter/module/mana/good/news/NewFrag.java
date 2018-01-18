package com.siweisoft.heavycenter.module.mana.good.news;

//by summer on 2017-12-19.

import android.os.Bundle;
import android.view.View;

import com.android.lib.constant.ValueConstant;
import com.android.lib.network.news.UINetAdapter;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;
import com.siweisoft.heavycenter.data.netd.mana.good.names.NamesRes;
import com.siweisoft.heavycenter.data.netd.mana.good.news.NewsGoodRes;
import com.siweisoft.heavycenter.data.netd.mana.good.specs.SpecsRes;
import com.siweisoft.heavycenter.data.netd.mana.store.list.StoresResBean;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.mana.good.lists.NamesFrag;
import com.siweisoft.heavycenter.module.mana.good.specs.SpecsFrag;
import com.siweisoft.heavycenter.module.mana.store.StoreFrag;
import com.siweisoft.heavycenter.module.myce.unit.area.prov.ProvFrag;

import butterknife.OnClick;

public class NewFrag extends AppFrag<NewUIOpe,NewDAOpe> {

    @OnClick({R.id.item_wuniaoname,R.id.item_wuliaoguige,R.id.item_cangku,R.id.item_area,R.id.ftv_right2})
    public void onClick(View v) {
        super.onClick(v);
        Bundle bundle = new Bundle();
        switch (v.getId()){
            case R.id.item_wuniaoname:
                bundle.putInt(ValueConstant.FARG_REQ,1);
                FragManager2.getInstance().start(getBaseUIActivity(), MainAct.主界面,new NamesFrag(),bundle);
                break;
            case R.id.item_wuliaoguige:
                if(getP().getU().canSpecsGo(getP().getD().getNewsGoodReq())){
                    bundle.putInt(ValueConstant.DATA_POSITION2,getP().getD().getNewsGoodReq().getMaterielId());
                    bundle.putInt(ValueConstant.FARG_REQ,2);
                    FragManager2.getInstance().start(getBaseUIActivity(), MainAct.主界面,new SpecsFrag(),bundle);
                }
                break;
            case R.id.item_cangku:
                bundle.putInt(ValueConstant.FARG_REQ,3);
                bundle.putInt(ValueConstant.DATA_POSITION2,StoreFrag.选择一个仓库);
                FragManager2.getInstance().start(getBaseUIActivity(), MainAct.主界面,new StoreFrag(),bundle);
                break;
            case R.id.item_area:
                bundle.putInt(ValueConstant.FARG_REQ,4);
                FragManager2.getInstance().start(getBaseUIActivity(), MainAct.主界面,new ProvFrag(),bundle);
                break;
            case R.id.ftv_right2:
                if(getP().getU().canGo()){
                    getP().getD().NewsGood(getP().getU().getNewsGoodReq(getP().getD().getNewsGoodReq()), new UINetAdapter<NewsGoodRes>(getActivity()) {
                        @Override
                        public void onResult(boolean success, String msg, NewsGoodRes o) {
                            super.onResult(success, msg, o);
                            if(success){
                                getArguments().putBoolean(ValueConstant.FARG_TYPE,true);
                                getBaseUIActivity().onBackPressed();
                            }
                        }
                    });
                }
                break;
        }
    }
    @Override
    public void onRestart(int res, Bundle bundle) {
        super.onRestart(res, bundle);
        switch (res){
            case 1:
                if(bundle.getSerializable(ValueConstant.DATA_DATA2)==null){
                    return;
                }
                NamesRes.ResultsBean data = (NamesRes.ResultsBean) bundle.getSerializable(ValueConstant.DATA_DATA2);
                getP().getD().getNewsGoodReq().setMaterielId(data.getProductId());
                getP().getD().getNewsGoodReq().setMaterielName(data.getProductName());
                //getP().getD().getNewsGoodReq().setMaterielSpecId(data.getProductId());
                //getP().getD().getNewsGoodReq().setMaterielSpecName(data.getSpecifications());
                break;
            case 2:
                if(bundle.getSerializable(ValueConstant.DATA_DATA2)==null){
                    return;
                }
                SpecsRes.ResultsBean data1 = (SpecsRes.ResultsBean) bundle.getSerializable(ValueConstant.DATA_DATA2);
                getP().getD().getNewsGoodReq().setMaterielSpecId(data1.getSpecificationsId());
                getP().getD().getNewsGoodReq().setMaterielSpecName(data1.getSpecifications());
                break;
            case 3:
                if(bundle.getSerializable(ValueConstant.DATA_DATA2)!=null){
                    StoresResBean.ResultsBean d = (StoresResBean.ResultsBean) bundle.getSerializable(ValueConstant.DATA_DATA2);
                    getP().getD().getNewsGoodReq().setWarehouseId(d.getWarehouseId());
                    getP().getD().getNewsGoodReq().setWarehouseName(d.getWarehouseName());
                }
                break;
            case 4:
                if(bundle.getString(ValueConstant.DATA_RES)==null){
                    return;
                }
                String name = bundle.getString(ValueConstant.DATA_RES);
                String req = bundle.getString(ValueConstant.DATA_RES2);
                getP().getD().getNewsGoodReq().setBelongAreaName(name);
                getP().getD().getNewsGoodReq().setBelongArea(req);
                break;

        }
        getP().getU().init(getP().getD().getNewsGoodReq());
    }
}
