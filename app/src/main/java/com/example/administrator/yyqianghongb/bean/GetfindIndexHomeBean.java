package com.example.administrator.yyqianghongb.bean;

import java.util.List;

/**
 * Created by 杨勇 on 2020/11/13.
 * QQ邮箱：824343111@qq.com
 */
public class GetfindIndexHomeBean {

    /**
     * msg : string
     * obj : {"list":[{"bid":"文章主键","grade1":"一级类目主键","grade1Name":"一级类目名称","grade2":"二级类目主键","grade2Name":"二级类目名称","title":"文章标题","type":"发布类型：1-word；2-PDF","typeName":"文件类型：word/pdf/img","uname":"用户名称","url":"文章路径"}],"time":"返回的请求时间，在后面的页数中回传"}
     * status : 0
     */

    private String msg;
    private ObjBean obj;
    private int status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public static class ObjBean {
        /**
         * list : [{"bid":"文章主键","grade1":"一级类目主键","grade1Name":"一级类目名称","grade2":"二级类目主键","grade2Name":"二级类目名称","title":"文章标题","type":"发布类型：1-word；2-PDF","typeName":"文件类型：word/pdf/img","uname":"用户名称","url":"文章路径"}]
         * time : 返回的请求时间，在后面的页数中回传
         */

        private String time;
        private List<ListBean> list;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * bid : 文章主键
             * grade1 : 一级类目主键
             * grade1Name : 一级类目名称
             * grade2 : 二级类目主键
             * grade2Name : 二级类目名称
             * title : 文章标题
             * type : 发布类型：1-word；2-PDF
             * typeName : 文件类型：word/pdf/img
             * uname : 用户名称
             * url : 文章路径
             */

            private String bid;
            private String cid;
            private String grade1;
            private String grade1Name;
            private String grade2;
            private String grade2Name;
            private String title;
            private int type;
            private String typeName;
            private String uname;
            private String url;
            private String ctime;

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

            public String getBid() {
                return bid;
            }

            public void setBid(String bid) {
                this.bid = bid;
            }

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
