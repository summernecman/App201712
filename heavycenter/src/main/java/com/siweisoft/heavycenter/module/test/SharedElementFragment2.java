package com.siweisoft.heavycenter.module.test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.siweisoft.heavycenter.R;

public class SharedElementFragment2 extends Fragment {
    private static final String EXTRA_SAMPLE = "sample";

    public static SharedElementFragment2 newInstance() {
        Bundle args = new Bundle();
        SharedElementFragment2 fragment = new SharedElementFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_sharedelement_fragment2, container, false);

        ImageView squareBlue = (ImageView) view.findViewById(R.id.square_blue);

        return view;
    }

}
