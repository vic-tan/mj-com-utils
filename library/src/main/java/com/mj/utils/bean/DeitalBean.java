package com.mj.utils.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by tanlifei on 2018/1/16.
 */

public class DeitalBean implements Serializable{

    /**
     * lotId : 220051
     * lotName : 双色球
     * issue : 2018006
     * code : 02 07 08 09 17 29+11
     * date : 2018-01-14
     * time : 21:20
     * sale : 387617618
     * money : 425081503
     * level : [{"name":"一等奖","count":"11","fund":"6856113"},{"name":"二等奖","count":"172","fund":"148381"},{"name":"三等奖","count":"2073","fund":"3000"},{"name":"四等奖","count":"96099","fund":"200"},{"name":"五等奖","count":"1708775","fund":"10"},{"name":"六等奖","count":"9063964","fund":"5"}]
     */

    private String lotId;
    private String lotName;
    private String issue;
    private String code;
    private String date;
    private String time;
    private String sale;
    private String money;
    private List<LevelBean> level;

    public String getLotId() {
        return lotId;
    }

    public void setLotId(String lotId) {
        this.lotId = lotId;
    }

    public String getLotName() {
        return lotName;
    }

    public void setLotName(String lotName) {
        this.lotName = lotName;
    }

    public String getIssue() {
        return issue;
    }

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public List<LevelBean> getLevel() {
        return level;
    }

    public void setLevel(List<LevelBean> level) {
        this.level = level;
    }

    public static class LevelBean implements Serializable{
        /**
         * name : 一等奖
         * count : 11
         * fund : 6856113
         */

        private String name;
        private String count;
        private String fund;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCount() {
            return count;
        }

        public void setCount(String count) {
            this.count = count;
        }

        public String getFund() {
            return fund;
        }

        public void setFund(String fund) {
            this.fund = fund;
        }
    }
}
