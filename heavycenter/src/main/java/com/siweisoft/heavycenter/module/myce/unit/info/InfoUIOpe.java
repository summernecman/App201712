package com.siweisoft.heavycenter.module.myce.unit.info;

//by summer on 2017-12-19.

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.unit.list.UnitInfo;
import com.siweisoft.heavycenter.databinding.FragMyceUnitInfoBinding;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.view.center.DiaLogCenterFrag;

public class InfoUIOpe extends AppUIOpe<FragMyceUnitInfoBinding>{


    public InfoUIOpe(Context context) {
        super(context);
    }

    public void initinfo(UnitInfo unitInfo){
        if(unitInfo==null){
            return;
        }
        bind.itemName.setMidTVTxt(unitInfo.getCompanyName());
        bind.itemShort.setMidTVTxt(unitInfo.getAbbreviationName());
        bind.itemUnunit.setMidTVTxt(unitInfo.getParentCompanyName());
        bind.itemAddr.setMidTVTxt(unitInfo.getCompanyAddress());
        bind.itemArea.setMidTVTxt(unitInfo.getBelongArea());
        bind.itemContact.setMidTVTxt(unitInfo.getContactName());
        bind.itemPhone.setMidTVTxt(unitInfo.getContactPhone());

    }

    public void showTip(View.OnClickListener onClickListener){
        DiaLogCenterFrag diaLogCenterFrag = new DiaLogCenterFrag();
        diaLogCenterFrag.setCustomView(LayoutInflater.from(context).inflate(R.layout.frag_myce_unit_bind_tip_leave,null));
        diaLogCenterFrag.setOnClickListener(onClickListener,R.id.close,R.id.sure);
        FragManager2.getInstance().setStartAnim(R.anim.scale_in,R.anim.scale_out,R.anim.scale_in,R.anim.scale_out).setFinishAnim(R.anim.fade_in,R.anim.fade_out).setHideLast(false).start(getActivity(),MainAct.主界面,diaLogCenterFrag);
    }
}
