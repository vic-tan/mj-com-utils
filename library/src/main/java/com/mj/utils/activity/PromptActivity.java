package com.mj.utils.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.mj.utils.R;
import com.mj.utils.tools.CustomDialogUitls;

/**
 * app服务类， 用于服务中间处理，比较从后台开一人服务，在界面显示提示，如升级，网络断开提示之类
 *
 * @author tanlifei 16/1/19.
 */
//全屏
public class PromptActivity extends Activity {


    public static final String EXTRA_ALERT = "cn.jpush.android.ALERT";
    protected Context mContext;
    protected String msg = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fullscreen();//全屏
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_prompt);
        mContext = this;
        getJPushMsg(EXTRA_ALERT);
        init();
    }


    /**
     * app 版本升级业务处理
     */
    protected void getJPushMsg(String msg_key) {
        Intent intent = getIntent();
        if (null != intent) {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                msg = bundle.getString(msg_key);
            }
        }
    }


    /**
     * app 版本升级业务处理
     */
    private void init() {
        CustomDialogUitls.defaultSingleDialog(this, msg, new CustomDialogUitls.DialogBackcallInterface() {
            @Override
            public void dialogBackcall(DialogInterface dialog, int tag) {
                colseAcitvity();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        colseAcitvity();
    }


    /**
     * 全屏显示
     */
    protected void fullscreen() {
        //取消标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //取消状态栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Override
    public void onBackPressed() {
        colseAcitvity();
    }

    private void colseAcitvity() {
        finish();
    }
}
