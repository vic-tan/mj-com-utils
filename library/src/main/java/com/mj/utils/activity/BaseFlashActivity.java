package com.mj.utils.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.mj.utils.bean.BaseJson;
import com.mj.utils.call.ConlseCallback;
import com.mj.utils.call.CountDownTimerCallback;
import com.mj.utils.tools.GetDateUtils;

/**
 * Created by Dmytro Denysenko on 5/4/15.
 */
public abstract class BaseFlashActivity extends AppCompatActivity implements CountDownTimerCallback{

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //去掉标题栏
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //去掉信息栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mContext = this;
        initCountDown();
    }


    private void initCountDown() {
        GetDateUtils.getMJUrl(mContext, getURL(), new ConlseCallback() {
            @Override
            public void conlseResult(BaseJson baseJson) {

            }
        });
        GetDateUtils.getDefaultCountDownTimer(mContext,this);
    }

    public abstract String getURL();

}
