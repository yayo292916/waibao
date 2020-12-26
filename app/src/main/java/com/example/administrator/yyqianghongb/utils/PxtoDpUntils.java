package com.example.administrator.yyqianghongb.utils;

import android.content.Context;

/**
 * Created by Administrator on 2018/8/21 0021.
 */

public class PxtoDpUntils {
    /**
     * dp转换成px
     */
    public static float dp2px(Context context, float dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return dpValue*scale+0.5f;
    }

    public static int  dp2pxInt(Context context, int  dpValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int) (dpValue*scale+0.5f);
    }

    /**
     * px转换成dp
     */
    public static float px2dp(Context context,float pxValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return pxValue/scale+0.5f;
    }

    /**
     * px转换成dp
     */
    public static int px2dpInt(Context context,float pxValue){
        float scale=context.getResources().getDisplayMetrics().density;
        return (int) (pxValue/scale+0.5f);
    }

    /**
     * sp转换成px
     */
    public static float sp2px(Context context,float spValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return spValue*fontScale+0.5f;
    }
    /**
     * sp转换成px
     */
    public static int sp2pxInt(Context context,float spValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue*fontScale+0.5f);
    }
    /**
     * px转换成sp
     */
    public static float px2sp(Context context,float pxValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return pxValue/fontScale+0.5f;
    }
}
