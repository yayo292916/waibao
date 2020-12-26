package com.example.administrator.yyqianghongb.fuzhu;

import android.app.Notification;


public interface IStatusBarNotification {
    String getPackageName();
    Notification getNotification();
}
