package com.example.administrator.yyqianghongb.bean;

/**
 * Created by 杨勇 on 2020/11/14.
 * QQ邮箱：824343111@qq.com
 */
public class SendsearchBinfosBean {

    /**
     * grade1 : 一级类目主键
     * grade2 : 二级类目主键
     * page : 页码
     * time : 时间（第一页不传参，拿取返回结果的time值）
     * value : 可为空，但是不能为NULL
     */

    private String grade1="0";
    private String grade2="0";
    private int page;
    private String time;
    private String value="";

    public String getGrade1() {
        return grade1;
    }

    public void setGrade1(String grade1) {
        this.grade1 = grade1;
    }

    public String getGrade2() {
        return grade2;
    }

    public void setGrade2(String grade2) {
        this.grade2 = grade2;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
