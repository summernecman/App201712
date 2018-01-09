package com.siweisoft.heavycenter.module.view.input;

//by summer on 2017-12-25.

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siweisoft.heavycenter.R;

public class EditLoginAcctItem extends RelativeLayout implements View.OnClickListener{

    private ImageView leftIV;

    private ImageView rightIV;

    private EditText editText;

    public EditLoginAcctItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_acct_login_edit,this,true);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.style_common);
        leftIV = (ImageView) findViewById(R.id.iv_left);
        leftIV.setImageResource(a.getResourceId(R.styleable.style_common_iv_left,R.drawable.icon_hv_phone));

        rightIV = (ImageView) findViewById(R.id.iv_right);
        if(a.getResourceId(R.styleable.style_common_iv_right,-1)!=-1){
            rightIV.setImageResource(a.getResourceId(R.styleable.style_common_iv_right,-1));
        }else{
            rightIV.setVisibility(View.GONE);
        }
        editText = (EditText) findViewById(R.id.et_txt);
        editText.setHint(a.getString(R.styleable.style_common_txt_mid));
        rightIV.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_right:
                editText.setText("");
                break;
        }
    }

    public String getText(){
        return editText.getText().toString();
    }
}
