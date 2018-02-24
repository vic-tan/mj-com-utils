package com.mj.utils.call;

import android.content.Context;

import com.google.gson.Gson;
import com.mj.utils.bean.BaseJson;
import com.tlf.basic.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 只返回结果onCusResponse方法，忽略其它方法
 * Created by tanlifei on 15/12/14.
 */
public abstract class ResultCallback extends Callback<BaseJson> {

    // 项目级别消息 用户验证问题
    public static String CODE_SUCCEE = "0000";// 操作成功

    protected Context mContext;

    public ResultCallback(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onAfter() {
        super.onAfter();
    }

    @Override
    public BaseJson parseNetworkResponse(Response response) throws Exception {
        String string = response.body().string();
        BaseJson jsonBean = new Gson().fromJson(replaceId(new String(string)), BaseJson.class);
        return jsonBean;
    }

    @Override
    public void onResponse(BaseJson response) {
        try {
            if (CODE_SUCCEE.equals(response.getCode())) {
                onCusResponse(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBefore(Request request) {
        super.onBefore(request);
    }

    @Override
    public void onError(Call call, Exception e) {
        super.onError(call, e);
        try {
            throw new Exception(e);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    public abstract void onCusResponse(BaseJson response);

}
