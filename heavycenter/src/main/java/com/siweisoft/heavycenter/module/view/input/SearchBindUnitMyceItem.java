package com.siweisoft.heavycenter.module.view.input;

//by summer on 2017-12-27.

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.siweisoft.heavycenter.R;

public class SearchBindUnitMyceItem extends LinearLayout {

    EditText editText;

    public SearchBindUnitMyceItem(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_myce_unit_bind_search,this,true);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.style_common);
        editText = (EditText) findViewById(R.id.et_name);
        editText.setHint(a.getString(R.styleable.style_common_txt_mid));

    }

    public EditText getEditText() {
        return editText;
    }
}
