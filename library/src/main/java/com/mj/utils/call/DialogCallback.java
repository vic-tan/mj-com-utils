package com.mj.utils.call;

import android.content.Context;

import com.google.gson.Gson;
import com.mj.utils.bean.BaseJson;
import com.tlf.basic.http.okhttp.callback.Callback;
import com.tlf.basic.uikit.kprogresshud.KProgressHUD;

import cn.bmob.v3.okhttp3.Call;
import cn.bmob.v3.okhttp3.Request;
import cn.bmob.v3.okhttp3.Response;


/**
 * 提示框加载基类，
 * 所有的提示框都得继承本类，
 * @author tanlifei 15/12/14.
 */
public abstract class DialogCallback extends Callback<BaseJson> {
    // 项目级别消息 用户验证问题
    public static String CODE_SUCCEE = "0000";// 操作成功
    protected KProgressHUD hud;
    protected Context mContext;

    public DialogCallback(Context mContext) {
        this.mContext = mContext;
        hud = KProgressHUD.create(mContext)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setDimAmount(0.5f)
                .setLabel("加载中")
                .setCancellable(true);
    }


    @Override
    public void onAfter() {
        super.onAfter();
        hud.dismiss();
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
        if (null != hud) {
            hud.show();
        }
    }

    @Override
    public void onError(Call call, Exception e) {
        super.onError(call, e);
        if (null != hud && hud.isShowing()) {
            hud.dismiss();
        }


    }

    public abstract void onCusResponse(BaseJson response);


}
