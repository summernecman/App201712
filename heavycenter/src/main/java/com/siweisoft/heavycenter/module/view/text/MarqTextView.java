package com.siweisoft.heavycenter.module.view.text;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by summer on 2018/3/11 13:01.
 */

public class MarqTextView extends android.support.v7.widget.AppCompatTextView{

    public MarqTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setSelected(true);
    }
}
