package com.example.administrator.yyqianghongb.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by 杨勇 on 2020/11/16.
 * QQ邮箱：824343111@qq.com
 */
public class EncoderByMd5Util {
    public static String md5jm(String string, int times,int head,int foot) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        String md5 = stringToMD5(head+string+foot);
        for (int i = 0; i < times - 1; i++) {
            md5 = stringToMD5(head+md5+foot);
            MyLoge.addLoge("md5",i+"ci:"+md5);
        }
        return md5;
    }

    public static String stringToMD5(String string) {
        byte[] hash;
        try {
            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));
        }
        return hex.toString().toUpperCase();
    }


}
