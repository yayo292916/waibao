package com.example.administrator.yyqianghongb.utils;

import android.util.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class Md5Util {
    private static final String[] a = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public Md5Util() {
    }

    public static String byteArrayToHexString(byte[] var0) {
        StringBuffer var1 = new StringBuffer();

        for(int var2 = 0; var2 < var0.length; ++var2) {
            int var3;
            if ((var3 = var0[var2]) < 0) {
                var3 += 256;
            }

            int var4 = var3 / 16;
            var3 %= 16;
            var1.append(a[var4] + a[var3]);
        }

        return var1.toString();
    }

    public static byte[] hexStringtoByteArray(String var0) {
        if (var0.length() % 2 != 0) {
            return null;
        } else {
            byte[] var1 = new byte[var0.length() / 2];

            for(int var2 = 0; var2 < var0.length() - 1; var2 += 2) {
                char var10000 = var0.charAt(var2);
                char var4 = var0.charAt(var2 + 1);
                char var3 = Character.toLowerCase(var10000);
                var4 = Character.toLowerCase(var4);
                int var5;
                if (var3 <= '9') {
                    var5 = var3 - 48;
                } else {
                    var5 = var3 - 97 + 10;
                }

                var5 <<= 4;
                if (var4 <= '9') {
                    var5 += var4 - 48;
                } else {
                    var5 += var4 - 97 + 10;
                }

                if (var5 > 127) {
                    var5 -= 256;
                }

                byte var6 = (byte)var5;
                var1[var2 / 2] = var6;
            }

            return var1;
        }
    }

    public static String encode(String var0) {
        String var1 = null;

        try {
            var1 = new String(var0);
            var1 = byteArrayToHexString(MessageDigest.getInstance("MD5").digest(var1.getBytes("UTF-8")));
        } catch (Exception var2) {
        }

        return var1;
    }

    public static String encodeBase64String(String var0) {
        byte[] var3 = Base64.decode(var0, 0);

        try {
            return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(var3));
        } catch (Exception var2) {
            return null;
        }
    }

    public static String encode(File var0) {
        if (var0 == null) {
            return "";
        } else {
            FileInputStream var1 = null;
            boolean var9 = false;

            String var16;
            label105: {
                try {
                    var9 = true;
                    MessageDigest var2 = MessageDigest.getInstance("MD5");
                    var1 = new FileInputStream(var0);
                    byte[] var3 = new byte[1024];

                    int var15;
                    while((var15 = var1.read(var3)) > 0) {
                        var2.update(var3, 0, var15);
                    }

                    var16 = byteArrayToHexString(var2.digest());
                    var9 = false;
                    break label105;
                } catch (Exception var13) {
                    var9 = false;
                } finally {
                    if (var9) {
                        if (var1 != null) {
                            try {
                                var1.close();
                            } catch (Exception var10) {
                            }
                        }

                    }
                }

                if (var1 != null) {
                    try {
                        var1.close();
                    } catch (Exception var11) {
                    }
                }

                return "";
            }

            try {
                var1.close();
            } catch (Exception var12) {
            }

            return var16;
        }
    }
}
