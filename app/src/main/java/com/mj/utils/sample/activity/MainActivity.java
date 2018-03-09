package com.mj.utils.sample.activity;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;

import com.mj.utils.bean.Config;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.GetListener;

/**
 * Created by Dmytro Denysenko on 5/4/15.
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BmobQuery<Config> bmobQuery = new BmobQuery<>();
        bmobQuery.getObject(this, "terM444I", new GetListener<Config>() {
            @Override
            public void onFailure(int i, String s) {
                Log.e("way", "查询成功 : " + s.toString());
            }

            @Override
            public void onSuccess(com.mj.utils.bean.Config config) {
                Log.e("way", "查询成功 : " + config.toString());

                SystemClock.sleep(2000);
                boolean isShowWap = config.getShow();
                if (isShowWap) {
                    String wapUrl = config.getUrl();
                    if (!TextUtils.isEmpty(wapUrl)) {

                    } else {
//                        callback.reload();
                    }
                } else {
//                    callback.home();
                }
            }


        });
    }

}
