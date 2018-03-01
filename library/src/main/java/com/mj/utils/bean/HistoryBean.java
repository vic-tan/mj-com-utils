package com.mj.utils.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by tanlifei on 2018/1/12.
 */

public class HistoryBean implements Parcelable {

    /**
     * Issue : 2018005
     * WinNumber : 02 20 21 28 31 33+06
     * BallNumber :
     * EndTime : 2018-01-11 20:00:00
     */

    private String Issue;
    private String WinNumber;
    private String BallNumber;
    private String EndTime;

    public String getIssue() {
        return Issue;
    }

    public void setIssue(String Issue) {
        this.Issue = Issue;
    }

    public String getWinNumber() {
        return WinNumber;
    }

    public void setWinNumber(String WinNumber) {
        this.WinNumber = WinNumber;
    }

    public String getBallNumber() {
        return BallNumber;
    }

    public void setBallNumber(String BallNumber) {
        this.BallNumber = BallNumber;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String EndTime) {
        this.EndTime = EndTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Issue);
        dest.writeString(this.WinNumber);
        dest.writeString(this.BallNumber);
        dest.writeString(this.EndTime);
    }

    public HistoryBean() {
    }

    protected HistoryBean(Parcel in) {
        this.Issue = in.readString();
        this.WinNumber = in.readString();
        this.BallNumber = in.readString();
        this.EndTime = in.readString();
    }

    public static final Creator<HistoryBean> CREATOR = new Creator<HistoryBean>() {
        @Override
        public HistoryBean createFromParcel(Parcel source) {
            return new HistoryBean(source);
        }

        @Override
        public HistoryBean[] newArray(int size) {
            return new HistoryBean[size];
        }
    };
}
