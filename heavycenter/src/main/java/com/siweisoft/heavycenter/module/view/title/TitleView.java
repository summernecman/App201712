package com.siweisoft.heavycenter.module.view.title;

//by summer on 2017-12-21.

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.siweisoft.heavycenter.R;

public class TitleView extends RelativeLayout {


    private TextView midTV;

    private ImageView leftIV;

    private ImageView rightIV;

    private String midTxt = "";

    private int leftivid;

    private int rightivid;


    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs){
        LayoutInflater.from(context).inflate(R.layout.item_title5,this,true);
        midTV = (TextView) findViewById(R.id.ftv_title);
        leftIV = (ImageView) findViewById(R.id.ftv_back);
        rightIV = (ImageView) findViewById(R.id.ftv_right);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.style_title);
        midTxt =  a.getString(R.styleable.style_title_txt_mid);
        leftivid = a.getResourceId(R.styleable.style_title_iv_left,R.drawable.icon_gou);
        rightivid = a.getResourceId(R.styleable.style_title_iv_right,R.drawable.icon_gou);
        leftIV.setBackgroundResource(leftivid);
        rightIV.setBackgroundResource(rightivid);
        midTV.setText(midTxt);

    }
}
