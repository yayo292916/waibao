package com.example.administrator.yyqianghongb.bean;

import java.util.List;

/**
 * Created by 杨勇 on 2020/11/13.
 * QQ邮箱：824343111@qq.com
 */
public class GetfindGrade2ByGrade1Bean {

    /**
     * msg : string
     * obj : [{"grade1":"一级类目主键","grade2":"二级类目主键","grade2Name":"二级类目名称"}]
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
         * grade2 : 二级类目主键
         * grade2Name : 二级类目名称
         */

        private String grade1;
        private String grade2;
        private String grade2Name;
        private boolean isClick;

        public boolean isClick() {
            return isClick;
        }

        public void setClick(boolean click) {
            isClick = click;
        }

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

        public String getGrade2Name() {
            return grade2Name;
        }

        public void setGrade2Name(String grade2Name) {
            this.grade2Name = grade2Name;
        }
    }
}
