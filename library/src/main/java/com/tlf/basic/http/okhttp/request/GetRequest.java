package com.tlf.basic.http.okhttp.request;

import java.util.Map;

import cn.bmob.v3.okhttp3.Request;
import cn.bmob.v3.okhttp3.RequestBody;


/**
 * Created by tanlifei on 15/12/14.
 */
public class GetRequest extends OkHttpRequest
{
    public GetRequest(String url, Object tag, Map<String, String> params, Map<String, String> headers)
    {
        super(url, tag, params, headers);
    }


    @Override
    protected RequestBody buildRequestBody()
    {
        return null;
    }

    @Override
    protected Request buildRequest(RequestBody requestBody)
    {
        return builder.get().build();
    }


}
