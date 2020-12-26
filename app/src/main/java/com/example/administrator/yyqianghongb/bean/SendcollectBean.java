package com.example.administrator.yyqianghongb.bean;

/**
 * Created by 杨勇 on 2020/11/14.
 * QQ邮箱：824343111@qq.com
 */
public class SendcollectBean {

    /**
     * bid : string
     * cid : string
     * ctime : string
     * userid : string
     */

    private String bid= "";
    private String cid = "";
    private String ctime = "";
    private String userid = "";

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
