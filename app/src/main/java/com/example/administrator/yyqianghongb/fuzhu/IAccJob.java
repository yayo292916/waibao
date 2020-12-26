package com.example.administrator.yyqianghongb.fuzhu;

import android.view.accessibility.AccessibilityEvent;



public interface IAccJob {
    String getTargetPackageName();
    void onCreateJob(FuzhuService service);
    void onReceiveJob(AccessibilityEvent event);
    void onStopJob();
    void onNotificationPosted(IStatusBarNotification service);
}
