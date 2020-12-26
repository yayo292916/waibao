package com.example.administrator.yyqianghongb.fuzhu;

import android.app.Notification;
import android.content.Intent;
import android.os.Build;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import com.example.administrator.yyqianghongb.BuildConfig;
import com.example.administrator.yyqianghongb.utils.MyLoge;


/**
 *
 * Created by 杨勇 on 2020/11/25.
 * QQ邮箱：824343111@qq.com
 */
public class FuzhuNotificationService extends NotificationListenerService {
    private static final String TAG = "FuzhuNotifi";
    public static final String ACTION_QIANGHONGBAO_SERVICE_DISCONNECT2 = "com.example.administrator.yyqianghongb.ACCESSBILITY_DISCONNECT";
    public static final String ACTION_QIANGHONGBAO_SERVICE_CONNECT2 = "com.example.administrator.yyqianghongb.ACCESSBILITY_CONNECT";
    private static FuzhuNotificationService service;

    @Override
    public void onCreate() {
        super.onCreate();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            onListenerConnected();
        }
    }

    @Override
    public void onNotificationPosted(final StatusBarNotification sbn) {
        if(BuildConfig.DEBUG) {
            Log.i(TAG, "onNotificationRemoved");
        }
        MyLoge.addLoge(TAG,"FuzhuNotificationService");
        FuzhuService.handeNotificationPosted(new IStatusBarNotification() {
            @Override
            public String getPackageName() {
                return sbn.getPackageName();
            }

            @Override
            public Notification getNotification() {
                return sbn.getNotification();
            }
        });
    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onNotificationRemoved(sbn);
        }
        if(BuildConfig.DEBUG) {
            Log.i(TAG, "onNotificationRemoved");
        }
    }

    @Override
    public void onListenerConnected() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            super.onListenerConnected();
        }

        Log.i(TAG, "onListenerConnected");
        service = this;
        //发送广播，已经连接上了
        Intent intent = new Intent(ACTION_QIANGHONGBAO_SERVICE_DISCONNECT2);
        sendBroadcast(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        service = null;
        //发送广播，已经连接上了
        Intent intent = new Intent(ACTION_QIANGHONGBAO_SERVICE_CONNECT2);
        sendBroadcast(intent);
    }

    /** 是否启动通知栏监听*/
    public static boolean isRunning() {
        if(service == null) {
            return false;
        }
        return true;
    }
}
