package com.siweisoft.heavycenter.module.view.center;

//by summer on 2017-12-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;

import butterknife.OnClick;

public class DiaLogCenterFrag extends BaseUIFrag<DialogCenterUIOpe,DialogCenterDAOpe> {


    View.OnClickListener onClickListener;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    public void close(){
        if(FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID)!=null&&FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID).size()>0){
            activity.getSupportFragmentManager().beginTransaction().remove(
                    FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID).get(
                            FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID).size()-1))
                    .commit();
            FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID).remove(FragManager.getInstance().getFragMaps().get(AcctAct.ROOT_ID).size()-1);
        }
        getActivity().getSupportFragmentManager().beginTransaction().remove(this).commit();
    }

    @OnClick({R.id.tv_close,R.id.tv_sure})
    public void onClick(View v) {
        if(onClickListener!=null){
            onClickListener.onClick(v);
        }
        close();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
