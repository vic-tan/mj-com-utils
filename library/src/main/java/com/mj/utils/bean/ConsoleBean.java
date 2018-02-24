package com.mj.utils.bean;

/**
 * Created by tanlifei on 2018/1/12.
 */

public class ConsoleBean {


    /**
     * app_id : app_2
     * mj_name : c773D福彩(应用宝)
     * close : false
     * url : 237573041156
     * flash : true
     */

    private String app_id;
    private String mj_name;
    private boolean close;
    private String url;
    private boolean flash;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getMj_name() {
        return mj_name;
    }

    public void setMj_name(String mj_name) {
        this.mj_name = mj_name;
    }

    public boolean isClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isFlash() {
        return flash;
    }

    public void setFlash(boolean flash) {
        this.flash = flash;
    }
}
