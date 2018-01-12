package com.android.lib.util.fragment.two;

//by summer on 2018-01-12.

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.android.lib.R;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;

import java.util.ArrayList;
import java.util.HashMap;

public class FragManager2  {

    private static  FragManager2 instance;

    private HashMap<Integer,ArrayList<BaseUIFrag>> map = new HashMap<>();



    public static FragManager2 getInstance(){
        if(instance==null){
            instance = new FragManager2();
        }
        return instance;
    }

    public void start(FragmentActivity activity, int viewid, BaseUIFrag fragment, Bundle bundle,int req){
        initFragList(viewid);
        if(fragment.getArguments()==null){
            fragment.setArguments(new Bundle());
        }
        fragment.getArguments().putInt(ValueConstant.FRAG_POSITION,viewid);
        fragment.getArguments().putInt(ValueConstant.FARG_REQ,req);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
        if(haveLastFrag(viewid)){
            transaction.hide(getLastFrag(viewid));
        }
        transaction.add(viewid,fragment,viewid+":"+map.get(viewid).size());
        transaction.commitNowAllowingStateLoss();
        getFragment(viewid).add(fragment);
    }

    public void finish(FragmentActivity activity,int viewid,Bundle bundle){

        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
        transaction.remove(getLastFrag(viewid));
        if(haveLastBeforeFrag(viewid)){
            transaction.show(getLastBeforeFrag(viewid));
            getLastBeforeFrag(viewid).onRestart(getLastFrag(viewid).getArguments().getInt(ValueConstant.FARG_REQ),bundle);
        }
        transaction.commitNowAllowingStateLoss();
        getFragment(viewid).remove(map.get(viewid).size()-1);
    }

    private void initFragList(int viewid){
        if(map.get(viewid)==null){
            map.put(viewid,new ArrayList<BaseUIFrag>());
        }
    }


    private boolean haveLastFrag(int viewid){
        if(map.get(viewid).size()>0){
            return true;
        }
        return false;
    }

    private boolean haveLastBeforeFrag(int viewid){
        if(map.get(viewid).size()-1>0){
            return true;
        }
        return false;
    }

    private BaseUIFrag getLastFrag(int viewid){
        return map.get(viewid).get(map.get(viewid).size()-1);
    }

    private BaseUIFrag getLastBeforeFrag(int viewid){
        return map.get(viewid).get(map.get(viewid).size()-2);
    }

    private ArrayList<BaseUIFrag> getFragment(int viewid){
        return map.get(viewid);
    }



}
