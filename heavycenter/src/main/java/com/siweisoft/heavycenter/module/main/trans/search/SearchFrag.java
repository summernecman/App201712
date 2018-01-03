package com.siweisoft.heavycenter.module.main.trans.search;

//by summer on 2017-12-18.

import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;

import butterknife.OnClick;

public class SearchFrag extends AppFrag<SearchUIOpe,SearchDAOpe> {


    @OnClick({R.id.ll_root})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_root:
                FragManager.getInstance().removeJustInNormal(activity,this,R.anim.anim_push_up_in, R.anim.anim_push_up_out);
                break;
        }
    }
}
