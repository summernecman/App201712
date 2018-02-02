package com.siweisoft.heavycenter.module.main.weigts.weighttips;

import android.view.View;
import butterknife.OnClick;
import butterknife.Optional;
import com.android.lib.base.fragment.BaseUIFrag;
import com.siweisoft.heavycenter.R;

/**
 * Created by summer on 2018/1/30 23:16.
 */

public class WeightTipsFrag  extends BaseUIFrag<WeightTipsUIOpe,WeightTipsDAOpe> {


    private View.OnClickListener onClickListener;

    @Optional
    @OnClick({R.id.tv_close,R.id.tv_sure})
    public void onClick(View v) {
        super.onClick(v);
        if(onClickListener!=null ){
            onClickListener.onClick(v);
            v.setTag(R.id.data,Double.parseDouble(getP().getU().bind.tvNewvalue.getText().toString()));
        }

    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
