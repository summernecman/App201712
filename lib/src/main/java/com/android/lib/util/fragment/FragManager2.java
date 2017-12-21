package com.android.lib.util.fragment;

//by summer on 2017-12-21.

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.android.lib.R;
import com.android.lib.constant.ValueConstant;

import java.util.ArrayList;
import java.util.HashMap;

public class FragManager2  {


    private HashMap<Integer,ArrayList<Fragment>> fragMap = new HashMap<>();

    private static FragManager2 instance;

    private int pos;

    private ArrayList<Integer> ids = new ArrayList<>();

    public static FragManager2 getInstance(){
        if(instance==null){
            instance = new FragManager2();
        }
        return instance;
    }




    public void add(FragmentManager manager,View view,Fragment fragment){
        if(view.getTag(R.id.position)==null){
            view.setTag(R.id.position,ids.size());
        }
        ids.add((Integer) view.getTag(R.id.position));
        this.pos = (int) view.getTag(R.id.position);
        if (fragMap.get(pos) == null) {
            fragMap.put(pos, new ArrayList<Fragment>());
        }
        FragmentTransaction transaction = manager.beginTransaction();
        if (fragment != null) {
            if (fragMap.get(pos).size() - 1 >= 0) {
                transaction.setCustomAnimations(R.anim.anim_push_right_in, R.anim.anim_push_left_out, R.anim.anim_push_right_in, R.anim.anim_push_left_out);
                transaction.hide(fragMap.get(pos).get(fragMap.get(pos).size() - 1));
            }
            Bundle bundle = fragment.getArguments();
            if (bundle == null) {
                fragment.setArguments(new Bundle());
            }
            fragment.getArguments().putInt(ValueConstant.FRAG_POSITION, pos);

            transaction.add(view.getId(), fragment, fragment.getClass().getSimpleName());
            fragMap.get(pos).add(fragment);
        }
        transaction.commitAllowingStateLoss();
    }

    public void clearAll(FragmentManager manager, int positon) {
        ArrayList<Fragment> fragments = fragMap.get(positon);
        if (fragments.size() > 0) {
            FragmentTransaction transaction = manager.beginTransaction();
            for (int j = fragments.size() - 1; j >= 0; j--) {
                transaction.remove(fragments.get(j));
                fragments.remove(j);
            }
            transaction.commitAllowingStateLoss();
        }
    }
}
