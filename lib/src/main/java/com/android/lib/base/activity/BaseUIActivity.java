package com.android.lib.base.activity;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.view.ViewGroup;

import com.android.lib.R;
import com.android.lib.base.ope.BaseDAOpe;
import com.android.lib.base.ope.BaseOpes;
import com.android.lib.base.ope.BaseUIOpe;
import com.android.lib.util.LogUtil;
import com.android.lib.view.bottommenu.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Constructor;
import java.lang.reflect.ParameterizedType;

import butterknife.ButterKnife;

/**
 * Created by summer on 2016/4/16 0016 11:51.
 */
public abstract class BaseUIActivity<A extends BaseUIOpe, B extends BaseDAOpe> extends BaseActivity {

    protected ViewGroup baseUIRoot;

    protected BaseOpes<A, B> opes;

    private String moudle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getBaseUILayout());
        baseUIRoot = findViewById(R.id.act_base_root);
        baseUIRoot.addView(getP().getU().getBind().getRoot(), new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        getP().getU().initUI();
        getP().getD().initDA();
        ButterKnife.bind(getActivity());
        if(registerEventBus()){
            EventBus.getDefault().register(this);
        }
    }

    protected int getBaseUILayout() {
        return R.layout.act_baseui;
    }


    public BaseOpes<A, B> getP() {
        if (opes == null) {
            getaabb(getClass());
        }
        return opes;
    }

    private BaseOpes<A, B> getaabb(Class<?> c) {
        if (c == null) {
            opes = (BaseOpes<A, B>) new BaseOpes<>(new BaseUIOpe<ViewDataBinding>(), new BaseDAOpe());
            opes.getD().setContext(getActivity());
            opes.getU().setContext(getActivity());
        }
        if (c.getGenericSuperclass() instanceof ParameterizedType) {
            Class<A> a = (Class<A>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[0];
            Class<B> b = (Class<B>) ((ParameterizedType) c.getGenericSuperclass()).getActualTypeArguments()[1];
            try {
                Constructor<A> ac = a.getConstructor();
                Constructor<B> bc = b.getConstructor();
                A aa = ac.newInstance();
                aa.setContext(getActivity());
                B bb = bc.newInstance();
                bb.setContext(getActivity());
                opes = new BaseOpes<>(aa, bb);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            getaabb(c.getSuperclass());
        }

        return opes;
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void dealMesage(MessageEvent event) {
        LogUtil.E(getClass().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(registerEventBus()){
            EventBus.getDefault().unregister(this);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected boolean registerEventBus(){
        return false;
    }

    public String getMoudle() {
        return moudle;
    }

    public void setMoudle(String moudle) {
        this.moudle = moudle;
    }

    public BaseUIActivity getActivity() {
        return this;
    }

    public ViewGroup getBaseUIRoot() {
        return baseUIRoot;
    }
}
