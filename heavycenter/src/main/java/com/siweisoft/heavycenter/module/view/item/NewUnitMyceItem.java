package com.siweisoft.heavycenter.module.view.item;

//by summer on 2017-12-25.

import android.content.Context;
import android.content.res.TypedArray;
import android.text.InputFilter;
import android.text.InputType;
import android.text.method.DigitsKeyListener;
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

    private boolean edit = false;



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
        edit = a.getBoolean(R.styleable.style_common_boo_edit,false);
       if(edit){
           midTV.setVisibility(View.GONE);
       }else{
           midET.setVisibility(View.GONE);
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
                //midTV.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
                break;
        }

        if(a.getInt(R.styleable.style_common_txt_maxlenth,-1)!=-1){
            midET.setFilters(new InputFilter[]{new InputFilter.LengthFilter(a.getInt(R.styleable.style_common_txt_maxlenth,-1))});
        }

    }

    public EditText getMidET() {
        return midET;
    }

    public TextView getMidTV() {
        return midTV;
    }

    public String getMidEtTxt(){
        return midET.getText().toString();
    }

    public String getMidTvTxt(){
        return midTV.getText().toString();
    }

    public void setMidTVTxt(String Str){
        midET.setVisibility(View.GONE);
        midTV.setVisibility(View.VISIBLE);
        midTV.setText(Str);
    }




    public void setEdit(boolean edit) {
        this.edit = edit;
        if(edit){
            midTV.setVisibility(View.GONE);
            removeView(midTV);
        }else{
            midET.setVisibility(View.GONE);
            removeView(midET);
        }
    }
}
