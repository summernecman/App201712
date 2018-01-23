package com.android.lib.util.fragment.two;

//by summer on 2018-01-12.

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.android.lib.R;
import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.fragment.BaseFrg;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.constant.ValueConstant;

import java.util.ArrayList;
import java.util.HashMap;

public class FragManager2  {

    private static HashMap<String,Container> map = new HashMap<>();

    private int anim1,anim2,anim3,anim4,anim5,anim6;

    private boolean hideLast = true;

    private boolean anim = true;


    public static FragManager2 getInstance(){
        return new FragManager2();
    }




    public void start(BaseUIActivity activity, String moudle, int viewid, BaseUIFrag fragment, Bundle bundle){
        checkMap(moudle,viewid);
        checkArguments(fragment);
        fragment.getArguments().putAll(bundle);
        start(activity,moudle,viewid,fragment);
    }

    public void start(BaseUIActivity activity, String moudle, int viewid,BaseUIFrag fragment){
        checkMap(moudle,viewid);
        checkArguments(fragment);
        activity.setMoudle(moudle);
        fragment.getArguments().putString(ValueConstant.CONTAINER_NAME,moudle);
        fragment.getArguments().putInt(ValueConstant.VIEW_ID,viewid);
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        if(isAnim()){
            transaction.setCustomAnimations(getAnim1(),getAnim2());
        }
        if(map.get(moudle).haveLast()){
            if(hideLast){
            transaction.hide(map.get(moudle).getLast());
            }
        }
        transaction.add(viewid,fragment,fragment.getUniqueid()+"");
        transaction.commitNowAllowingStateLoss();
        map.get(moudle).addFrag(fragment);
    }


    public void start(BaseUIActivity activity, String moudle,BaseUIFrag fragment){
       if(map.get(moudle)==null){
           return;
       }
       if( map.get(moudle).getViewid()==-1){
           return;
       }
       start(activity,moudle,map.get(moudle).getViewid(),fragment);
    }

    public void start(BaseUIActivity activity, String moudle,BaseUIFrag fragment,Bundle bundle){
        checkArguments(fragment);
        fragment.getArguments().putAll(bundle);
        start(activity,moudle,fragment);
    }



    public boolean finish(BaseUIActivity activity, String moudle,boolean keepone){
        if(moudle==null || map.get(moudle)==null){
            return false;
        }
        if(keepone){
            if(!map.get(moudle).haveLastBefore()){
                return false;
            }
        }else{
            if(!map.get(moudle).haveLast()){
                return false;
            }
        }
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        if(isAnim()){
            transaction.setCustomAnimations(getAnim5(),getAnim6());
        }
        transaction.remove(map.get(moudle).getLast());
        if(map.get(moudle).haveLastBefore()){
            transaction.show(map.get(moudle).getLastBefore());
            map.get(moudle).getLastBefore().onRestart(map.get(moudle).getLast().getArguments().getInt(ValueConstant.FARG_REQ),map.get(moudle).getLast().getArguments());
        }
        map.get(moudle).removeLast();
        transaction.commitNowAllowingStateLoss();
        return true;
    }


    public void clear(BaseUIActivity activity,String moudle){
        if(map.get(moudle)==null){
            return;
        }
        FragmentTransaction transaction = activity.getSupportFragmentManager().beginTransaction();
        while (map.get(moudle).haveLast()){
            transaction.remove(map.get(moudle).getLast());
            map.get(moudle).removeLast();
        }
        transaction.commitNowAllowingStateLoss();
    }



    private void checkMap(String moudle,int viewid){
        if(map.get(moudle)==null){
            map.put(moudle,new Container(moudle,viewid));
        }
    }

    private void checkArguments(BaseUIFrag baseUIFrag){
        if(baseUIFrag.getArguments()==null){
            baseUIFrag.setArguments(new Bundle());
        }
    }

    public int getAnim1() {
        if(anim1==0){
            anim1 =R.anim.anim_push_right_in;
        }
        return anim1;
    }

    public int getAnim2() {
        if(anim2==0){
            anim2 =R.anim.anim_push_left_out;
        }
        return anim2;
    }

    public int getAnim3() {
        if(anim3==0){
            anim3 =R.anim.anim_push_right_in;
        }
        return anim3;
    }

    public int getAnim4() {
        if(anim4==0){
            anim4 =R.anim.anim_push_left_out;
        }
        return anim4;
    }

    public int getAnim5() {
        if(anim5==0){
            anim5 =R.anim.anim_push_left_in;
        }
        return anim5;
    }

    public int getAnim6() {
        if(anim6==0){
            anim6 =R.anim.anim_push_right_out;
        }
        return anim6;
    }

    public FragManager2 setStartAnim(int anim1, int anim2, int anim3, int anim4) {
        this.anim1 = anim1;
        this.anim2 = anim2;
        this.anim3 = anim3;
        this.anim4 = anim4;
        return this;
    }

    public FragManager2 setFinishAnim(int anim5, int anim6) {
        this.anim5 = anim5;
        this.anim6 = anim6;
        return this;
    }

    public void clear(){
        map = new HashMap<>();
    }


    public FragManager2 setHideLast(boolean hideLast) {
        this.hideLast = hideLast;
        return this;
    }

    public boolean isAnim() {
        return anim;
    }

    public FragManager2 setAnim(boolean anim) {
        this.anim = anim;
        return this;
    }
}
