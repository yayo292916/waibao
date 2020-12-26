package com.example.administrator.yyqianghongb.bean;

/**
 * Created by 杨勇 on 2020/11/13.
 * QQ邮箱：824343111@qq.com
 */
public class GetLoginBean {

    /**
     * status : 200
     * msg : 登录成功
     * obj : {"user":{"userid":"202011114702e795e3f9d7134a59cb7c3ef8fe2f","uname":"admin","pwd":"123456","login_time":"2020-11-11 21:27:21"},"token":"6K75hCf/tVUFrUVXVjGEbXpriYvsBpSCMwMfvpqraXVye0GDeZJuBpD6g/Ym2GOW"}
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
         * user : {"userid":"202011114702e795e3f9d7134a59cb7c3ef8fe2f","uname":"admin","pwd":"123456","login_time":"2020-11-11 21:27:21"}
         * token : 6K75hCf/tVUFrUVXVjGEbXpriYvsBpSCMwMfvpqraXVye0GDeZJuBpD6g/Ym2GOW
         */

        private UserBean user;
        private String token;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public static class UserBean {
            /**
             * userid : 202011114702e795e3f9d7134a59cb7c3ef8fe2f
             * uname : admin
             * pwd : 123456
             * login_time : 2020-11-11 21:27:21
             */

            private String userid;
            private String uname;
            private String pwd;
            private String login_time;

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public String getUname() {
                return uname;
            }

            public void setUname(String uname) {
                this.uname = uname;
            }

            public String getPwd() {
                return pwd;
            }

            public void setPwd(String pwd) {
                this.pwd = pwd;
            }

            public String getLogin_time() {
                return login_time;
            }

            public void setLogin_time(String login_time) {
                this.login_time = login_time;
            }
        }
    }
}
