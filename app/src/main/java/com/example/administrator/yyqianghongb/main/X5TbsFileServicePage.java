package com.example.administrator.yyqianghongb.main;

import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arialyy.annotations.Download;
import com.arialyy.aria.core.Aria;
import com.arialyy.aria.core.task.DownloadTask;
import com.example.administrator.yyqianghongb.R;
import com.example.administrator.yyqianghongb.base.BaseActivityNewest;
import com.example.administrator.yyqianghongb.base.GetBaseBean;
import com.example.administrator.yyqianghongb.bean.GetCollectStateBean;
import com.example.administrator.yyqianghongb.bean.SendcollectBean;
import com.example.administrator.yyqianghongb.utils.MyEasyHttp;
import com.example.administrator.yyqianghongb.utils.MyLoge;
import com.example.administrator.yyqianghongb.utils.MyToast;
import com.example.administrator.yyqianghongb.view.YYTitleBar;
import com.tencent.smtt.sdk.TbsReaderView;

import java.io.File;
import java.util.HashMap;

import butterknife.BindView;

/**
 * Created by 杨勇 on 2020/11/13.
 * QQ邮箱：824343111@qq.com
 */
public class X5TbsFileServicePage extends BaseActivityNewest implements TbsReaderView.ReaderCallback {
    @BindView(R.id.X5TbsView)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.bar)
    YYTitleBar bar;
    @BindView(R.id.collImg)
    ImageView collImg;
    @BindView(R.id.downTv)
    TextView downTv;
    private String cid;
    private boolean isColl;
    private String tbsReaderTemp = Environment.getExternalStorageDirectory() + "/TbsReaderTemp";
    TbsReaderView mTbsReaderView;
    private String url, bid;
    private String typeName;
    private String title;
    private String filePath = Environment.getExternalStorageDirectory().getPath() + "/huihuang";
    private long downId;

    @Override
    protected int setStatusBar() {
        return 0;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_x5_tbs_file_service_page;
    }

    @Override
    protected void initView() {
        Aria.download(this).register();
        bid = getIntent().getStringExtra("bid");
        title = getIntent().getStringExtra("title");
        bar.setText(title + "");
        url = getIntent().getStringExtra("url");
        typeName = getIntent().getStringExtra("typeName");

        if (TextUtils.isEmpty(typeName)) {
            MyToast.addToast("typeName不能为空");
            return;
        }

        if (TextUtils.isEmpty(url)) {
            MyToast.addToast("url不能为空");
            return;
        }

        MyLoge.addLoge("urlName", url + "   typeName" + typeName);
        if (!TextUtils.isEmpty(url)) {
            File fileD = new File(filePath);
            deleteDirWithFile(fileD);

            File file = new File(filePath);
            if (!file.exists()) {
                boolean mkdir = file.mkdir();
                if (!mkdir) {

                }
            }
            downId = Aria.download(this)
                    .load(url)
                    .setFilePath(filePath + "/yy" + System.currentTimeMillis() + "." + typeName)
                    .create();

        }
    }

    //删除文件夹和文件夹里面的文件
    private void deleteDirWithFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                file.delete(); // 删除所有文件
            else if (file.isDirectory())
                deleteDirWithFile(file); // 递规的方式删除文件夹
        }
    }

    @Override
    protected void initData() {
        getCollect();
    }

    @Override
    protected void initListener() {
        collImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isColl) {
                    deleteCollect();
                } else {
                    collect();
                }
            }
        });
        bar.setOnTitleBackClick(new YYTitleBar.OnTitleBackClick() {
            @Override
            public void titleBackClick() {
                finish();
            }
        });
    }


    public void displayFile(String path) {
        mTbsReaderView = new TbsReaderView(this, this);
        //增加下面一句解决没有TbsReaderTemp文件夹存在导致加载文件失败
        String bsReaderTemp = tbsReaderTemp;
        File bsReaderTempFile = new File(bsReaderTemp);

        if (!bsReaderTempFile.exists()) {
            Log.d("print", "文件不存在准备创建/TbsReaderTemp！！");
            boolean mkdir = bsReaderTempFile.mkdir();
            if (!mkdir) {
                Log.d("print", "创建/TbsReaderTemp失败！！！！！");
            }
            Toast.makeText(this, "文件不存在！", Toast.LENGTH_SHORT).show();
        }
        mRelativeLayout.addView(mTbsReaderView, new RelativeLayout.LayoutParams(-1, -1));
        Bundle bundle = new Bundle();
        bundle.putString("filePath", path);
        bundle.putString("tempPath", tbsReaderTemp);
        boolean result = mTbsReaderView.preOpen(getFileType("word." + typeName), false);
        Log.d("print", "查看文档---" + result);
        if (result) {
            mTbsReaderView.openFile(bundle);
        } else {

        }
    }

    private String getFileType(String paramString) {
        String str = "";
        if (TextUtils.isEmpty(paramString)) {
            Log.d("print", "paramString---->null");
            return str;
        }
        Log.d("print", "paramString:" + paramString);
        int i = paramString.lastIndexOf('.');
        if (i <= -1) {
            Log.d("print", "i <= -1");
            return str;
        }

        str = paramString.substring(i + 1);
        Log.d("print", "paramString.substring(i + 1)------>" + str);
        return str;
    }

    @Override
    public void onCallBackAction(Integer integer, Object o, Object o1) {
        Log.i("触摸监听：", " ");
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (downId != 0) {
            Aria.download(this)
                    .load(downId)//读取任务id
                    .stop();
        }
        if (mTbsReaderView != null) {
            mTbsReaderView.onStop();
            mTbsReaderView.destroyDrawingCache();
        }
    }

    //在这里处理任务执行中的状态，如进度进度条的刷新
    @Download.onTaskRunning
    protected void running(DownloadTask task) {
        if (task.getKey().equals(url)) {
            int p = task.getPercent();
            downTv.setText("下载中" + p + "%");
        }
//        int p = task.getPercent();	//任务进度百分比
//        String speed = task.getConvertSpeed();	//转换单位后的下载速度，单位转换需要在配置文件中打开
//        String speed1 = task.getSpeed(); //原始byte长度速度
    }

    @Download.onTaskComplete
    void taskComplete(DownloadTask task) {
        //在这里处理任务完成的状态
        MyLoge.addLoge("DownloadTask", task.getFilePath());
        if (task.getKey().equals(url)) {
            displayFile(task.getFilePath());
        }
    }

    private void collect() {
        SendcollectBean bean = new SendcollectBean();
        bean.setBid(bid);
        MyEasyHttp.json(getContext(), "collect/collect", bean, GetBaseBean.class, new MyEasyHttp.OnResult<GetBaseBean>() {
            @Override
            public void onSuccess(GetBaseBean response) {
                if (response.getStatus() == 200) {
                    getCollect();
                }
                MyToast.addToast(response.getMessage());
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    private void deleteCollect() {
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("cid", cid);
        MyEasyHttp.post(getContext(), "collect/deleteCollect", maps, GetBaseBean.class, new MyEasyHttp.OnResult<GetBaseBean>() {
            @Override
            public void onSuccess(GetBaseBean response) {
                if (response.getStatus() == 200) {
                    getCollect();
                }
                MyToast.addToast(response.getMessage());
            }

            @Override
            public void onError(String e) {

            }
        });
    }

    private void getCollect() {
        HashMap<String, Object> maps = new HashMap<>();
        maps.put("bid", bid);
        MyEasyHttp.post(getContext(), "collect/getCollectState", maps, GetCollectStateBean.class, new MyEasyHttp.OnResult<GetCollectStateBean>() {
            @Override
            public void onSuccess(GetCollectStateBean response) {
                if (response.getStatus() == 200) {
                    cid = response.getObj().getCid();
                    isColl = response.getObj().getType() == 2;
                    collImg.setImageResource(isColl ? R.drawable.particulars_icon_collect_yellow : R.drawable.particulars_icon_collect_ash);
                }
            }

            @Override
            public void onError(String e) {

            }
        });
    }
}
