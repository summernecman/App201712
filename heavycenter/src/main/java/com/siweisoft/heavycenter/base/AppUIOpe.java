package com.siweisoft.heavycenter.base;

//by summer on 2017-12-08.

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.view.title.TitleView;
import com.siweisoft.heavycenter.R;

public class AppUIOpe<A extends ViewDataBinding>  extends BaseUIOpe<A> {


    public TitleView titleView;

    public AppUIOpe(){

    }

    protected void initTitle(int leftres,String mid,int right2res,int rightres){
        if(getFrag().getView().findViewById(R.id.title11)!=null&&getFrag().getView().findViewById(R.id.title11) instanceof TitleView){
            titleView = getFrag().getView().findViewById(R.id.title11);
            titleView.setTitle(leftres,mid,right2res,rightres);
        }
    }

    public AppUIOpe(Context context) {
        super(context);
    }


}
