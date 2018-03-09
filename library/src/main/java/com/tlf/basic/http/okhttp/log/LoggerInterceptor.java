package com.tlf.basic.http.okhttp.log;

import android.text.TextUtils;

import com.tlf.basic.http.okhttp.OkHttpUtils;
import com.tlf.basic.http.okhttp.utils.SupportLogger;

import java.io.IOException;

import cn.bmob.v3.okhttp3.Headers;
import cn.bmob.v3.okhttp3.Interceptor;
import cn.bmob.v3.okhttp3.MediaType;
import cn.bmob.v3.okhttp3.Request;
import cn.bmob.v3.okhttp3.RequestBody;
import cn.bmob.v3.okhttp3.Response;
import cn.bmob.v3.okhttp3.ResponseBody;
import okio.Buffer;


/**
 * Created by tanlifei on 16/3/1.
 */
public class LoggerInterceptor implements Interceptor {
    public static final String TAG = "OkHttpUtils";
    private String tag;
    private boolean showResponse;

    public LoggerInterceptor(String tag, boolean showResponse) {
        if (TextUtils.isEmpty(tag)) {
            tag = TAG;
        }
        this.showResponse = showResponse;
        this.tag = tag;
    }

    public LoggerInterceptor(String tag) {
        this(tag, false);
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
        return logForResponse(response, request);
    }


    private Response logForResponse(Response response, Request request) {
        try {
            //===>response log
            Response.Builder builder = response.newBuilder();
            Response clone = builder.build();
            if (showResponse) {
                ResponseBody body = clone.body();
                if (body != null) {
                    MediaType mediaType = body.contentType();
                    if (mediaType != null) {
                        //Logger.i(tag, "responseBody's contentType : " + mediaType.toString());
                        if (isText(mediaType)) {
                            String resp = body.string();
                           /*if (JsonReader.getInstance().getJsonReader(clone.request().url().toString())) {
                                String responseBody = JsonReader.getInstance().getJsonReaderFileContent(BaseApplication.appContext, clone.request().url().toString());
                                resp =  responseBody;
                            }*/

                            try {
                                if (null == OkHttpUtils.okHttpConsole) {
                                    SupportLogger.i(tag, "" + clone.request().url().toString());
                                    SupportLogger.i(tag, "" + resp + "");
                                    SupportLogger.json(tag, "" + resp);
                                }
                                if (null != OkHttpUtils.okHttpConsole && !OkHttpUtils.okHttpConsole.isOn_of_level()) {
                                    SupportLogger.i(tag, "" + clone.request().url().toString());
                                    SupportLogger.i(tag, "" + resp + "");
                                    SupportLogger.json(tag, "" + resp);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            body = ResponseBody.create(mediaType, resp);
                            return response.newBuilder().body(body).build();
                        } else {
                            SupportLogger.i(tag, "responseBody's content : " + " maybe [file part] , too large too print , ignored!");
                        }
                        //Logger.d(tag, "" + body.string());
                        //Logger.json(tag, "" + body.string());
                    }

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private void logForRequest(Request request) {
        try {
            String url = request.url().toString();
            Headers headers = request.headers();

            //Logger.i(tag, "method : " + request.method());
            // Logger.i(tag, "url : " + url);
            if (headers != null && headers.size() > 0) {
                // Logger.i(tag, "headers : " + headers.toString());
            }
            RequestBody requestBody = request.body();
            if (requestBody != null) {
                MediaType mediaType = requestBody.contentType();
                if (mediaType != null) {
                    // Logger.i(tag, "requestBody's contentType : " + mediaType.toString());
                    if (isText(mediaType)) {
                        // Logger.i(tag, "requestBody's content : " + bodyToString(request));
                    } else {
                        // Logger.i(tag, "requestBody's content : " + " maybe [file part] , too large too print , ignored!");
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }

    private String bodyToString(final Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "something error when show requestBody.";
        }
    }
}
