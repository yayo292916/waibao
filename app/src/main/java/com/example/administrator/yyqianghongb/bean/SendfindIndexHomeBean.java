package com.example.administrator.yyqianghongb.bean;

/**
 * Created by 杨勇 on 2020/11/13.
 * QQ邮箱：824343111@qq.com
 */
public class SendfindIndexHomeBean {

    /**
     * grade1 : 一级类目主键
     * grade2 : 二级类目主键
     * page : 页码
     * time : 时间（第一页不传参，拿取返回结果的time值）
     */

    private String grade1;
    private String grade2;
    private int page;
    private String time;

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
}
