package com.mj.utils.sample;

import android.app.Application;
import android.graphics.Typeface;

import cn.bmob.v3.Bmob;

/**
 * Created by Dmytro Denysenko on 5/6/15.
 */
public class App extends Application {
    private static final String CANARO_EXTRA_BOLD_PATH = "fonts/canaro_extra_bold.otf";
    public static Typeface canaroExtraBold;

    @Override
    public void onCreate() {
        super.onCreate();
        initTypeface();
        Bmob.initialize(this, "a0f4455c7a728fbd5c526722181d702e");
    }

    private void initTypeface() {
        canaroExtraBold = Typeface.createFromAsset(getAssets(), CANARO_EXTRA_BOLD_PATH);

    }
}
