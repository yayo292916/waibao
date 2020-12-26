package com.example.administrator.yyqianghongb.bean;

import java.util.List;

/**
 * Created by 杨勇 on 2020/11/13.
 * QQ邮箱：824343111@qq.com
 */
public class GetfindGrade1Bean {

    /**
     * msg : string
     * obj : [{"grade1":"一级类目主键","grade1Name":"一级类目名字"}]
     * status : 0
     */

    private String msg;
    private int status;
    private List<ObjBean> obj;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<ObjBean> getObj() {
        return obj;
    }

    public void setObj(List<ObjBean> obj) {
        this.obj = obj;
    }

    public static class ObjBean {
        /**
         * grade1 : 一级类目主键
         * grade1Name : 一级类目名字
         */

        private String grade1;
        private String grade1Name;

        public String getGrade1() {
            return grade1;
        }

        public void setGrade1(String grade1) {
            this.grade1 = grade1;
        }

        public String getGrade1Name() {
            return grade1Name;
        }

        public void setGrade1Name(String grade1Name) {
            this.grade1Name = grade1Name;
        }
    }
}
