package com.mj.utils.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.mj.utils.R;

/**
 * 首页界面
 * Created by tanlifei on 16/1/19.
 */
public class WebViewActivity extends Activity {

    private WebView webView;
    private ProgressDialog processDialog;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_webview);
        webView = (WebView) findViewById(R.id.video_webview);
        initwidget();
        url = getIntent().getStringExtra("url");
        Log.i("webview----url----->", url);
        this.webView.loadUrl(url);
    }


    @SuppressWarnings("deprecation")
    private void initwidget() {
        WebSettings ws = webView.getSettings();
        ws.setBuiltInZoomControls(false);// 隐藏缩放按钮
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);// 排版适应屏幕
        ws.setUseWideViewPort(true);// 可任意比例缩放
        ws.setLoadWithOverviewMode(true);// setUseWideViewPort方法设置webview推荐使用的窗口。setLoadWithOverviewMode方法是设置webview加载的页面的模式。
        ws.setSavePassword(true);
        ws.setJavaScriptEnabled(true);
        ws.setUseWideViewPort(true);// 可任意比例缩放
        webView.setWebViewClient(new xWebViewClientent());
        processDialog = ProgressDialog.show(this, null, "正在加载,请稍后. ..");
        processDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface dialog, int keyCode,
                                 KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    webView.stopLoading();
                    finish();
                    return true;
                }
                return false;
            }
        });
        webView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }


    /**
     * 处理各种通知、请求等事件
     *
     * @author
     */
    public class xWebViewClientent extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return false;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            if (processDialog.isShowing()) { //加判断，防止dialog没显示就调用dismiss，导致报错
                processDialog.dismiss();
            }
            super.onPageFinished(view, url);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
