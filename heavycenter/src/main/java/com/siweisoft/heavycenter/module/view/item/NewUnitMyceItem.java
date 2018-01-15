package com.siweisoft.heavycenter.module.view.item;

//by summer on 2017-12-25.

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siweisoft.heavycenter.R;

public class NewUnitMyceItem extends RelativeLayout {

    private TextView leftTV;

    private ImageView rightIV;

    private EditText midET;

    private float leftW = 0;

    private TextView midTV;



    public NewUnitMyceItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_myce_unit_new,this,true);
        leftTV = (TextView) findViewById(R.id.tv_left);
        rightIV = (ImageView) findViewById(R.id.iv_right);
        midET = (EditText) findViewById(R.id.et_mid);
        midTV = (TextView) findViewById(R.id.tv_mid);

        TypedArray a  =context.obtainStyledAttributes(attrs,R.styleable.style_common);
        String leftStr = a.getString(R.styleable.style_common_txt_left);
        if(leftStr!=null){
            leftTV.setText(leftStr);
        }
        int rightivres = a.getResourceId(R.styleable.style_common_iv_right,0);
        if(rightivres!=0){
            rightIV.setImageResource(rightivres);
        }
        String hint = a.getString(R.styleable.style_common_txt_mid);
        if(hint!=null){
            midET.setHint(hint);
        }
        leftW = a.getDimension(R.styleable.style_common_minwidth,0);
        leftTV.setMinWidth((int) leftW);
        boolean edit = a.getBoolean(R.styleable.style_common_boo_edit,false);
       if(edit){
           midTV.setVisibility(View.GONE);
           removeView(midTV);
       }else{
           midET.setVisibility(View.GONE);
           removeView(midET);
           midTV.setText(a.getString(R.styleable.style_common_txt_two));
       }

        switch (a.getInt(R.styleable.style_common_inputType,-1)){
            case 0:
                midET.setInputType(InputType.TYPE_CLASS_PHONE|InputType.TYPE_CLASS_NUMBER);
                break;
            case 1:
                midET.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                break;
            case 2:
                midET.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                break;
            case 3:
                midET.setInputType(InputType.TYPE_NUMBER_FLAG_DECIMAL|InputType.TYPE_CLASS_NUMBER);
                break;
        }

    }

    public EditText getMidET() {
        return midET;
    }

    public TextView getMidTV() {
        return midTV;
    }

    public void setMidTVTxt(String Str){
        midTV.setText(Str);
    }
}
