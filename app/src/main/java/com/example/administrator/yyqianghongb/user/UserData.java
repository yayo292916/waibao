package com.example.administrator.yyqianghongb.user;

import java.io.Serializable;

/**
 * Created by 杨勇 on 2020/11/13.
 * QQ邮箱：824343111@qq.com
 */
public class UserData implements Serializable {
    private String uName;
    private String pwd;
    private boolean jizhu = true;

    @Override
    public String toString() {
        return "UserData{" +
                "uName='" + uName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", jizhu=" + jizhu +
                '}';
    }

    public boolean isJizhu() {
        return jizhu;
    }

    public void setJizhu(boolean jizhu) {
        this.jizhu = jizhu;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
