package com.siweisoft.heavycenter.module.view.input;

//by summer on 2017-12-25.

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siweisoft.heavycenter.R;

public class EditLoginAcctItem extends RelativeLayout {

    private ImageView leftIV;

    public EditLoginAcctItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_acct_login_edit,this,true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.style_common);
        leftIV = (ImageView) findViewById(R.id.iv_left);
        leftIV.setImageResource(a.getResourceId(R.styleable.style_common_iv_left,R.drawable.icon_hv_phone));
    }
}
