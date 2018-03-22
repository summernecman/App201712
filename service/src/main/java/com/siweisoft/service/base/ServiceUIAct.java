package com.siweisoft.service.base;

//by summer on 2018-03-22.

import com.android.lib.base.activity.BaseUIActivity;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.FragmentUtil2;
import com.siweisoft.service.R;

public class ServiceUIAct<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseUIActivity<A,B> {

    @Override
    public void onBackPressed() {
        if (FragmentUtil2.getInstance().getFragMap().get(R.id.act_base_root) == null) {
            finish();
        } else if (FragmentUtil2.getInstance().getFragMap().get(R.id.act_base_root).size() == 1) {
            FragmentUtil2.getInstance().clear();
            this.finish();
        } else {
            FragmentUtil2.getInstance().removeTopRightNow(getActivity(), R.id.act_base_root);
        }
    }

}
