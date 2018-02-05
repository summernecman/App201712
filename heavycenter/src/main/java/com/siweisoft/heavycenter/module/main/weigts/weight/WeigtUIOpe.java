package com.siweisoft.heavycenter.module.main.weigts.weight;

//by summer on 2017-12-11.

import android.content.Context;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.lib.util.StringUtil;
import com.android.lib.util.fragment.two.FragManager2;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppUIOpe;
import com.siweisoft.heavycenter.data.netd.acct.login.LoginResBean;
import com.siweisoft.heavycenter.data.netd.jpush.WeightMsg;
import com.siweisoft.heavycenter.data.netd.jpush.WeightRes;
import com.siweisoft.heavycenter.databinding.FragMainWeigtBinding;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;
import com.siweisoft.heavycenter.module.main.MainAct;
import com.siweisoft.heavycenter.module.main.weigts.weighttips.WeightTipsFrag;
import com.siweisoft.heavycenter.module.view.center.DiaLogCenterFrag;

public class WeigtUIOpe extends AppUIOpe<FragMainWeigtBinding> {

    public WeigtUIOpe(Context context) {
        super(context);
    }

    FragManager2 fragManager2;

    public void initRefresh(){
    }


    public void showTip(String title,View.OnClickListener onClickListener){
        WeightTipsFrag weightTipsFrag = new WeightTipsFrag();
        weightTipsFrag.setOnClickListener(onClickListener);
        fragManager2 = FragManager2.getInstance().setStartAnim(R.anim.fade_in,R.anim.fade_out,R.anim.fade_in,R.anim.fade_out).setFinishAnim(R.anim.fade_in,R.anim.fade_out).setHideLast(false);
        fragManager2.start(getActivity(), MainAct.地磅,MainAct.地磅ID,weightTipsFrag);

    }

    public FragManager2 getFragManager2() {
        return fragManager2;
    }

    public void init(WeightMsg weightMsg){
        if(weightMsg==null|| weightMsg.getMessage()==null){
            return;
        }
        WeightMsg.MessageBean m = weightMsg.getMessage();
        bind.tvWeight.setText(StringUtil.getStr(m.getWeigh()));
        bind.tvMz.setText(StringUtil.getStr(m.getWeigh()));
        bind.tvPz.setText(StringUtil.getStr(m.getPz()));
        bind.tvKc.setText(StringUtil.getStr(m.getKc()));
        bind.tvJz.setText(StringUtil.getStr(m.getJz()));
        bind.weight.anim();
    }
}
