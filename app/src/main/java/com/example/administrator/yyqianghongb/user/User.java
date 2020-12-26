package com.example.administrator.yyqianghongb.user;


/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class User  {
    private volatile static User instance = null;

    public static User getInstance() {
        if (instance == null) {
            synchronized (User.class) {
                if (instance == null)
                    instance = new User();
            }
        }
        return instance;
    }

    private User() {
    }

    private String headerKey = "Authorization";
    private String token;
    private String uid;
    private String uName;
    private boolean quanxian;

    public boolean isQuanxian() {
        return quanxian;
    }

    public void setQuanxian(boolean quanxian) {
        this.quanxian = quanxian;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getHeaderKey() {
        return headerKey;
    }
}
