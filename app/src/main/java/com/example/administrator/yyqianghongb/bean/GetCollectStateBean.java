package com.example.administrator.yyqianghongb.bean;

/**
 * Created by 杨勇 on 2020/11/14.
 * QQ邮箱：824343111@qq.com
 */
public class GetCollectStateBean {

    /**
     * status : 200
     * msg : 文章收藏状态
     * obj : {"cid":"","type":1}
     */

    private int status;
    private String msg;
    private ObjBean obj;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

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

    public static class ObjBean {
        /**
         * cid :
         * type : 1
         */

        private String cid;
        private int type;

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
