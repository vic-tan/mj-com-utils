package com.mj.utils.call;

import com.mj.utils.bean.BaseJson;

/**
 * 只返回结果onCusResponse方法，忽略其它方法
 * Created by tanlifei on 15/12/14.
 */
public interface ConlseCallback {

    void conlseResult(BaseJson response);

}
