package com.siweisoft.heavycenter.module.view.center;

//by summer on 2017-12-18.

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.module.acct.acct.AcctAct;

import butterknife.OnClick;
import butterknife.Optional;

public class DiaLogCenterFrag extends BaseUIFrag<DialogCenterUIOpe,DialogCenterDAOpe> {


    View.OnClickListener onClickListener;

    View customerView;

    int[] views ;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(customerView!=null){
            ViewGroup viewGroup = (ViewGroup) getView().findViewById(R.id.dialog_root);
            viewGroup.removeAllViews();
            viewGroup.addView(customerView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        }
        if(views!=null){
            for(int i=0;i<views.length;i++){
                View view1 = getView().findViewById(views[i]);
                view1.setOnClickListener(this);
            }
        }
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

    public void setCustomView(View v){
        customerView = v;

    }

    public void onClick(View v) {
        if(onClickListener!=null){
            onClickListener.onClick(v);
        }
        close();
    }



    public void setOnClickListener(View.OnClickListener onClickListener,int... views) {
        this.onClickListener = onClickListener;
        this.views = views;
    }
}
