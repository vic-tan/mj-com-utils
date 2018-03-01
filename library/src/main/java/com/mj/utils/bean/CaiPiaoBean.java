package com.mj.utils.bean;

import java.io.Serializable;

/**
 * Created by tanlifei on 2018/1/12.
 */

public class CaiPiaoBean implements Serializable {

    /**
     * issue : 2018005
     * lotName : 双色球
     * balls : 02 20 21 28 31 33+06
     * date : 2018-01-11
     * index : 1
     */
    private String tag_id;
    private String issue;
    private String lotName;
    private String balls;
    private String date;
    private String index;


    public String getTag_id() {
        return tag_id;
    }

    public void setTag_id(String tag_id) {
        this.tag_id = tag_id;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getBalls() {
        return balls;
    }

    public void setBalls(String balls) {
        this.balls = balls;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
