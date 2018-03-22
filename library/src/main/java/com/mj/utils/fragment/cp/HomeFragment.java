package com.mj.utils.fragment.cp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mj.utils.R;
import com.mj.utils.activity.AboutActivity;
import com.mj.utils.activity.BaseEmailActivity;
import com.mj.utils.activity.BasePhoneActivity;
import com.mj.utils.activity.BaseQQActivity;
import com.mj.utils.activity.BaseWXActivity;
import com.mj.utils.activity.FeedbackActivity;
import com.mj.utils.activity.LocalWebViewActivity;


/**
 * 今天
 */
public class HomeFragment extends Fragment {

    public static final String TAG = HomeFragment.class.getSimpleName();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment_navigator_setting, container, false);
        view.findViewById(R.id.rl_advice_rep).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
            }
        });
        view.findViewById(R.id.rl_about_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });
        view.findViewById(R.id.rl_help_book).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), LocalWebViewActivity.class);
                intent2.putExtra("title", "玩法");
                intent2.putExtra("name", "VIPDesc.html");
                startActivity(intent2);
            }
        });
        view.findViewById(R.id.rl_advice_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BasePhoneActivity.class));
            }
        });
        view.findViewById(R.id.rl_advice_emial).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BaseEmailActivity.class));
            }
        });
        view.findViewById(R.id.rl_advice_qq).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BaseQQActivity.class));
            }
        });
        view.findViewById(R.id.rl_advice_wx).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), BaseWXActivity.class));
            }
        });
        view.findViewById(R.id.rl_advice_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AboutActivity.class));
            }
        });
        view.findViewById(R.id.rl_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        return view;
    }
}
