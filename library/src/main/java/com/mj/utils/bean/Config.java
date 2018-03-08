package com.mj.utils.bean;


import cn.bmob.v3.BmobObject;

/**
 * Created by Way on 2017/7/8.
 */

public class Config extends BmobObject {
    @Override
    public String toString() {
        return "Config{" +
                "url='" + url + '\'' +
                ", show=" + show +
                ", appid='" + appid + '\'' +
                '}';
    }

    private String url;
    private boolean show;
    private String appid;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean getShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

}
