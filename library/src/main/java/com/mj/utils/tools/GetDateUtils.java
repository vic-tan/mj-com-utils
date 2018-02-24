package com.mj.utils.tools;

import android.content.Context;
import android.os.CountDownTimer;
import android.util.Base64;

import com.alibaba.fastjson.JSONException;
import com.google.gson.Gson;
import com.mj.utils.bean.BaseJson;
import com.mj.utils.bean.ConsoleBean;
import com.mj.utils.call.ConlseCallback;
import com.mj.utils.call.CountDownTimerCallback;
import com.mj.utils.call.ResultCallback;
import com.tlf.basic.http.okhttp.OkHttpUtils;

import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by tanlifei on 2018/1/11.
 */

public class GetDateUtils {

    public static final String SHOW_URL = "show_url";
    public static final String SHOW_URL_VALUE = "1";
    public static final String URL = "url";
    public static final String CLOSE = "close";
    public static final String URL_TAG = "url_tag";

    public static void getConlse(Context mContext, final String app_id, final ConlseCallback callback) {
        OkHttpUtils.get().url(MyUrl.MAJIA_CONSOLE).build().execute(new ResultCallback(mContext) {
            @Override
            public void onCusResponse(BaseJson response) {
                try {
                    try {
                        if (null != response.getData()) {
                            List<ConsoleBean> list = FastjsonUtils.parseToObjectList(new Gson().toJson(response.getData()), ConsoleBean.class);
                            if (null != list && list.size() > 0) {
                                for (ConsoleBean consoleBean : list) {
                                    if (app_id.equals(consoleBean.getApp_id())) {
                                        SPUtils.putBoolean(mContext, CLOSE, consoleBean.isClose());
                                        SPUtils.putString(mContext, URL_TAG, consoleBean.getUrl());
                                    }
                                }
                            }
                        }
                        callback.conlseResult(response);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        callback.conlseResult(response);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    SPUtils.putBoolean(mContext, CLOSE, false);
                    SPUtils.putString(mContext, URL_TAG, "");
                }
            }

            @Override
            public void onError(Call call, Exception e) {
                super.onError(call, e);
                SPUtils.putBoolean(mContext, CLOSE, false);
                SPUtils.putString(mContext, URL_TAG, "");
            }
        });
    }

    public static void getMJUrl(Context mContext, String url, final ConlseCallback callback) {
        getMJUrl(mContext, url, "", "", "", callback);
    }

    public static void getMJUrl(Context mContext, String url, final String key_show_url, final String value_show_url, final String key_url, final ConlseCallback callback) {
        OkHttpUtils.get().url(url).build().execute(new ResultCallback(mContext) {
            @Override
            public void onCusResponse(BaseJson response) {

            }

            @Override
            public BaseJson parseNetworkResponse(Response response) throws Exception {
                String string = response.body().string();
                BaseJson jsonBean = new BaseJson();
                try {
                    String d = FastjsonUtils.getKeyResult(string, "data");
                    jsonBean.setCode(d);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return jsonBean;
            }

            @Override
            public void onResponse(BaseJson response) {
                if (!isEmpty(response.getCode())) {
                    byte[] decode = Base64Coder.decode(response.getCode(), Base64.DEFAULT);
                    try {
                        String res = new String(decode, "UTF-8");
                        if (!isEmpty(res)) {
                            String show_url = FastjsonUtils.getKeyResult(res, isEmpty(key_show_url) ? SHOW_URL : key_show_url);
                            String url = FastjsonUtils.getKeyResult(res, isEmpty(key_url) ? URL : key_url);
                            //是否显示url 不为空且show_url =1 时
                            if (!isEmpty(show_url) && show_url.equals(isEmpty(value_show_url) ? SHOW_URL_VALUE : value_show_url)) {
                                SPUtils.putBoolean(mContext, isEmpty(key_show_url) ? SHOW_URL : key_show_url, true);
                                SPUtils.putString(mContext, isEmpty(key_url) ? URL : key_url, url);
                            } else {
                                SPUtils.putBoolean(mContext, isEmpty(key_show_url) ? SHOW_URL : key_show_url, false);
                            }
                            callback.conlseResult(response);
                        }
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        SPUtils.putBoolean(mContext, isEmpty(key_show_url) ? SHOW_URL : key_show_url, false);
                    }
                } else {
                    SPUtils.putBoolean(mContext, isEmpty(key_show_url) ? SHOW_URL : key_show_url, false);
                }
            }

        });
    }


    public static void getCountDownTimer(long millisInFuture, long countDownInterval, final CountDownTimerCallback callback) {
        countDownTimer(millisInFuture, countDownInterval, callback);
    }

    public static void getDefaultCountDownTimer(final CountDownTimerCallback callback) {
        countDownTimer(3000, 3000, callback);
    }

    public static void countDownTimer(long millisInFuture, long countDownInterval, final CountDownTimerCallback countDownTimerCallback) {
        new CountDownTimer(millisInFuture, countDownInterval) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                countDownTimerCallback.countDownTimerResult();
            }
        }.start();
    }

    public static boolean isEmpty(String value) {
        return !(value != null && !"".equalsIgnoreCase(value.trim())
                && !"null".equalsIgnoreCase(value.trim()));
    }

}
