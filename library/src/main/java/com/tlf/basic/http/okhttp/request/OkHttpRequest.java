package com.tlf.basic.http.okhttp.request;


import com.tlf.basic.http.okhttp.OkConsoleUtils;
import com.tlf.basic.http.okhttp.callback.Callback;

import java.util.Map;

import cn.bmob.v3.okhttp3.Headers;
import cn.bmob.v3.okhttp3.Request;
import cn.bmob.v3.okhttp3.RequestBody;

/**
 * Created by tanlifei on 15/11/6.
 */
public abstract class OkHttpRequest
{
    protected String url;
    protected Object tag;
    protected Map<String, String> params;
    protected Map<String, String> headers;

    protected Request.Builder builder = new Request.Builder();

    protected OkHttpRequest(String url, Object tag,
                            Map<String, String> params, Map<String, String> headers)
    {
        this.url = OkConsoleUtils.randomRequest(url);
        this.tag = tag;
        this.params = params;
        this.headers = headers;

        if (url == null)
        {
            throw new IllegalArgumentException("url can not be null.");
        }

        initBuilder();
    }



    /**
     * 初始化一些基本参数 url , tag , headers
     */
    private void initBuilder()
    {
        builder.url(url).tag(tag);
        appendHeaders();
    }

    protected abstract RequestBody buildRequestBody();

    protected RequestBody wrapRequestBody(RequestBody requestBody, final Callback callback)
    {
        return requestBody;
    }

    protected abstract Request buildRequest(RequestBody requestBody);

    public RequestCall build()
    {
        return new RequestCall(this);
    }


    public Request generateRequest(Callback callback)
    {
        RequestBody requestBody = buildRequestBody();
        RequestBody wrappedRequestBody = wrapRequestBody(requestBody, callback);
        Request request = buildRequest(wrappedRequestBody);
        return request;
    }


    protected void appendHeaders()
    {
        Headers.Builder headerBuilder = new Headers.Builder();
        if (headers == null || headers.isEmpty()) return;

        for (String key : headers.keySet())
        {
            headerBuilder.add(key, headers.get(key));
        }
        builder.headers(headerBuilder.build());
    }

}
