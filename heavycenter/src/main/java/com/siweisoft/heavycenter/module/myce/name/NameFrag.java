package com.siweisoft.heavycenter.module.myce.name;

//by summer on 2017-12-19.

import android.view.View;

import com.android.lib.util.fragment.FragManager;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;

public class NameFrag extends AppFrag<NameUIOpe,NameDAOpe> {

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.ftv_back:
                if(FragManager.getInstance().getFragMaps().get(getIndex())!=null&& FragManager.getInstance().getFragMaps().get(getIndex()).size()>0){
                    FragManager.getInstance().finish(getActivity().getSupportFragmentManager(),getIndex());
                }
                break;
        }
    }

}
