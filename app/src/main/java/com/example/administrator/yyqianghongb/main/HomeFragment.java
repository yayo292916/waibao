package com.example.administrator.yyqianghongb.main;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.preference.SwitchPreference;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.base.BaseFragmentNewest;
import com.example.administrator.yyqianghongb.fuzhu.FuzhuService;
import com.example.administrator.yyqianghongb.utils.MyToast;

import butterknife.BindView;


/**
 * Created by 杨勇 on 2020/11/12.
 * QQ邮箱：824343111@qq.com
 */
public class HomeFragment extends BaseFragmentNewest {

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    @BindView(R.id.wxSwitch)
    Switch wxSwitch;
    @BindView(R.id.openNotifi)
    TextView openNotifi;
    @BindView(R.id.openZhufu)
    TextView openZhufu;
    private Dialog mTipsDialog;
    private boolean isFuzhu;
    private boolean isNotif;//是否开启通知栏模式

    @Override
    public int initLayout() {
        return R.layout.home;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData(Context mContext) {
        isFuzhu = wxSwitch.isChecked();
        showOpenAccessibilityServiceDialog();
    }

    @Override
    public void initListener() {
        wxSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isFuzhu = isChecked;
            }
        });
        openZhufu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showOpenAccessibilityServiceDialog();
            }
        });
        openNotifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNotificationServiceSettings();
            }
        });
    }

    /**
     * 打开辅助服务的设置
     */
    private void openAccessibilityServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
            startActivity(intent);
            MyToast.addToast("找到[yy辅助]，然后开启服务即可");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示未开启辅助服务的对话框
     */
    private void showOpenAccessibilityServiceDialog() {
        if (mTipsDialog != null && mTipsDialog.isShowing()) {
            return;
        }
        View view = getLayoutInflater().inflate(R.layout.dialog_tips_layout, null);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccessibilityServiceSettings();
            }
        });
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(R.string.open_service_title);
        builder.setView(view);
        builder.setPositiveButton(R.string.open_service_button, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openAccessibilityServiceSettings();
            }
        });
        mTipsDialog = builder.show();
    }

    /**
     * 打开通知栏设置
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    private void openNotificationServiceSettings() {
        try {
            Intent intent = new Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS);
            startActivity(intent);
            MyToast.addToast("找到[yy辅助]，然后开启服务即可");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新快速读取通知的设置
     */
    public void updateNotifyPreference() {
        boolean running = FuzhuService.isNotificationServiceRunning();
        if (isNotif && running && !isFuzhu) {
        } else if ((!isNotif || !running) && isFuzhu) {
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        updateNotifyPreference();
    }
}
