package com.siweisoft.heavycenter.module.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.OnClick;
import com.android.lib.base.fragment.BaseUIFrag;
import com.android.lib.util.IntentUtil;
import com.android.lib.util.LogUtil;
import com.siweisoft.heavycenter.R;
import com.siweisoft.heavycenter.base.AppFrag;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends AppFrag<TestUIOpe,TestDAOpe> implements View.OnClickListener{


    @Override
    public void doThing() {
        super.doThing();
    }

    @OnClick({R.id.tv_txt})
    public void onClick(View v) {
        IntentUtil.getInstance().photoShowFromphone(this,01);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        LogUtil.E("fdfdfdfdfdfd");
    }
}
