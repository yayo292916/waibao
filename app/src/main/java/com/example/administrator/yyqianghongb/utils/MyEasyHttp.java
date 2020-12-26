package com.example.administrator.yyqianghongb.utils;


import android.content.Context;

import com.example.administrator.yyqianghongb.user.User;
import com.google.gson.Gson;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.DownloadProgressCallBack;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.HttpHeaders;
import com.zhouyou.http.model.HttpParams;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;

import io.reactivex.disposables.Disposable;
import okhttp3.MediaType;
import okhttp3.RequestBody;


/**
 * Created by 杨勇 on 2019/8/28.
 * QQ邮箱：824343111@qq.com
 */
public class MyEasyHttp {
    private static HttpHeaders headers = new HttpHeaders();

    public static void cancelHttp(Disposable disposable) {
        if (disposable != null) {
            EasyHttp.cancelSubscription(disposable);
        }
    }

    public static <T> Disposable get(String url, final Class<T> tClass, OnResult<T> onResult) {
        MyLoge.addLoge(url, User.getInstance().getHeaderKey() + "   " + User.getInstance().getToken());
        return EasyHttp.get(url + "/")
                .headers(getHttpHeaders())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (onResult != null)
                            onResult.onError(e.toString());
                    }

                    @Override
                    public void onSuccess(String str) {
                        if (str.length() > 2000) {
                            for (int i = 0; i < str.length(); i += 2000) {
                                if (i + 2000 < str.length()) {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, i + 2000));
                                } else {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, str.length()));
                                }
                            }
                        } else {
                            MyLoge.addLoge(url + ">>>第1段", str);
                        }
                        //解析网络数据
                        final T result = new Gson().fromJson(str, tClass);
                        onResult.onSuccess(result);
                    }
                });
    }

    public static <T> Disposable get(Context context, String url, final Class<T> tClass, OnResult<T> onResult) {
        MyLoge.addLoge(url, User.getInstance().getHeaderKey() + "   " + User.getInstance().getToken());
        return EasyHttp.get(url + "/")
                .headers(getHttpHeaders())
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (onResult != null && JudgeActivtiy.isUseable(context))
                            onResult.onError(e.toString());
                    }

                    @Override
                    public void onSuccess(String str) {
                        if (!JudgeActivtiy.isUseable(context)) {
                            return;
                        }
                        if (str.length() > 2000) {
                            for (int i = 0; i < str.length(); i += 2000) {
                                if (i + 2000 < str.length()) {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, i + 2000));
                                } else {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, str.length()));
                                }
                            }
                        } else {
                            MyLoge.addLoge(url + ">>>第1段", str);
                        }
                        //解析网络数据
                        final T result = new Gson().fromJson(str, tClass);
                        onResult.onSuccess(result);
                    }
                });
    }

    public static <T> Disposable post(String url, HashMap<String, Object> map, final Class<T> tClass, OnResult<T> onResult) {
        MyLoge.addLoge(url, User.getInstance().getHeaderKey() + "   " + User.getInstance().getToken());
        MyLoge.addLoge("@@@@", "url=" + url + "   请求参数：" + map.toString() + "  token=" + User.getInstance().getToken());
        return EasyHttp.post(url + "/")
                .headers(getHttpHeaders())
                .params(hashSend(map))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (onResult != null)
                            onResult.onError(e.toString());
                    }

                    @Override
                    public void onSuccess(String str) {
                        if (str.length() > 2000) {
                            for (int i = 0; i < str.length(); i += 2000) {
                                if (i + 2000 < str.length()) {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, i + 2000));
                                } else {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, str.length()));
                                }
                            }
                        } else {
                            MyLoge.addLoge(url + ">>>第1段", str);
                        }
                        //解析网络数据
                        final T result = new Gson().fromJson(str, tClass);
                        onResult.onSuccess(result);
                    }
                });
    }

    public static <T> Disposable post(Context context, String url, HashMap<String, Object> map, final Class<T> tClass, OnResult<T> onResult) {
        MyLoge.addLoge(url, User.getInstance().getHeaderKey() + "   " + User.getInstance().getToken());
        MyLoge.addLoge("@@@@", "url=" + url + "   请求参数：" + map.toString() + "  token=" + User.getInstance().getToken());
        return EasyHttp.post(url + "/")
                .headers(getHttpHeaders())
                .params(hashSend(map))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (onResult != null && JudgeActivtiy.isUseable(context))
                            onResult.onError(e.toString());
                    }

                    @Override
                    public void onSuccess(String str) {
                        if (!JudgeActivtiy.isUseable(context)) {
                            return;
                        }
                        if (str.length() > 2000) {
                            for (int i = 0; i < str.length(); i += 2000) {
                                if (i + 2000 < str.length()) {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, i + 2000));
                                } else {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, str.length()));
                                }
                            }
                        } else {
                            MyLoge.addLoge(url + ">>>第1段", str);
                        }
                        //解析网络数据
                        final T result = new Gson().fromJson(str, tClass);
                        onResult.onSuccess(result);
                    }
                });
    }

    public static <T> Disposable postTimeOut(int timeout, String url, HashMap<String, Object> map, final Class<T> tClass, OnResult<T> onResult) {
        MyLoge.addLoge(url, User.getInstance().getHeaderKey() + "   " + User.getInstance().getToken());
        MyLoge.addLoge("@@@@", "url=" + url + "   请求参数：" + map.toString() + "  token=" + User.getInstance().getToken());
        return EasyHttp.post(url + "/")
                .headers(getHttpHeaders())
                .params(hashSend(map))
                .connectTimeout(2000)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (onResult != null)
                            onResult.onError(e.toString());
                    }

                    @Override
                    public void onSuccess(String str) {
                        if (str.length() > 2000) {
                            for (int i = 0; i < str.length(); i += 2000) {
                                if (i + 2000 < str.length()) {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, i + 2000));
                                } else {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, str.length()));
                                }
                            }
                        } else {
                            MyLoge.addLoge(url + ">>>第1段", str);
                        }
                        //解析网络数据
                        final T result = new Gson().fromJson(str, tClass);
                        onResult.onSuccess(result);
                    }
                });
    }

    public static <T> Disposable postTimeOut(Context context, int timeout, String url, HashMap<String, Object> map, final Class<T> tClass, OnResult<T> onResult) {
        MyLoge.addLoge(url, User.getInstance().getHeaderKey() + "   " + User.getInstance().getToken());
        MyLoge.addLoge("@@@@", "url=" + url + "   请求参数：" + map.toString() + "  token=" + User.getInstance().getToken());
        return EasyHttp.post(url + "/")
                .headers(getHttpHeaders())
                .params(hashSend(map))
                .connectTimeout(2000)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (onResult != null && JudgeActivtiy.isUseable(context))
                            onResult.onError(e.toString());
                    }

                    @Override
                    public void onSuccess(String str) {
                        if (JudgeActivtiy.isUseable(context)) {
                            if (str.length() > 2000) {
                                for (int i = 0; i < str.length(); i += 2000) {
                                    if (i + 2000 < str.length()) {
                                        MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, i + 2000));
                                    } else {
                                        MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, str.length()));
                                    }
                                }
                            } else {
                                MyLoge.addLoge(url + ">>>第1段", str);
                            }
                            //解析网络数据
                            final T result = new Gson().fromJson(str, tClass);
                            onResult.onSuccess(result);
                        }

                    }
                });
    }

    public static <T, K> Disposable json(String url, final K tClassJson, final Class<T> tClass, OnResult<T> onResult) {
        MyLoge.addLoge(url, jsonSend(tClassJson));
        MyLoge.addLoge(url, User.getInstance().getHeaderKey() + "   " + User.getInstance().getToken());
        return EasyHttp.post(url + "/")
                .headers(getHttpHeaders())
                .upJson(jsonSend(tClassJson))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (onResult != null)
                            onResult.onError(e.toString());
                    }

                    @Override
                    public void onSuccess(String str) {
                        if (str.length() > 2000) {
                            for (int i = 0; i < str.length(); i += 2000) {
                                if (i + 2000 < str.length()) {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, i + 2000));
                                } else {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, str.length()));
                                }
                            }
                        } else {
                            MyLoge.addLoge(url + ">>>第1段", str);
                        }
                        //解析网络数据
                        final T result = new Gson().fromJson(str, tClass);
                        onResult.onSuccess(result);
                    }
                });
    }

    public static <T, K> Disposable json(Context context, String url, final K tClassJson, final Class<T> tClass, OnResult<T> onResult) {
        MyLoge.addLoge(url, jsonSend(tClassJson));
        MyLoge.addLoge(url, User.getInstance().getHeaderKey() + "   " + User.getInstance().getToken());
        return EasyHttp.post(url + "/")
                .headers(getHttpHeaders())
                .upJson(jsonSend(tClassJson))
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (onResult != null && JudgeActivtiy.isUseable(context))
                            onResult.onError(e.toString());
                    }

                    @Override
                    public void onSuccess(String str) {
                        if (JudgeActivtiy.isUseable(context)) {
                            if (str.length() > 2000) {
                                for (int i = 0; i < str.length(); i += 2000) {
                                    if (i + 2000 < str.length()) {
                                        MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, i + 2000));
                                    } else {
                                        MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, str.length()));
                                    }
                                }
                            } else {
                                MyLoge.addLoge(url + ">>>第1段", str);
                            }
                            //解析网络数据
                            final T result = new Gson().fromJson(str, tClass);
                            onResult.onSuccess(result);
                        }

                    }
                });
    }

    /**
     * 下载文件
     */
    public static Disposable downLoad(String url, DownloadProgressCallBack<String> downloadProgressCallBack) {
        return EasyHttp.downLoad(url + "/")
                .headers(getHttpHeaders())
                .savePath("/sdcard/tanjie/")
                .execute(downloadProgressCallBack);
//        int progress = (int) (bytesRead * 100 / contentLength);
    }

    public static <T> Disposable onUploadFile(String url, String fileKey, String filePath, final Class<T> tClass, OnResult<T> onResult) {
        File file = new File(filePath);
        RequestBody body = RequestBody.create(MediaType.parse("imgs/image"), file);
        return EasyHttp.post(url)
                .headers(getHttpHeaders())
                .requestBody(body)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        if (onResult != null)
                            onResult.onError(e.toString());
                    }

                    @Override
                    public void onSuccess(String str) {
                        if (str.length() > 2000) {
                            for (int i = 0; i < str.length(); i += 2000) {
                                if (i + 2000 < str.length()) {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, i + 2000));
                                } else {
                                    MyLoge.addLoge(url + ">>>第" + (i / 2000 + 1) + "段", str.substring(i, str.length()));
                                }
                            }
                        } else {
                            MyLoge.addLoge(url + ">>>第1段", str);
                        }
                        //解析网络数据
                        final T result = new Gson().fromJson(str, tClass);
                        onResult.onSuccess(result);
                    }
                });
    }

    private static HttpHeaders getHttpHeaders() {
        headers.put(User.getInstance().getHeaderKey(), "Bearer " + User.getInstance().getToken());
        return headers;
    }

    /**
     * 加密data ver
     */
    private static String encryption(HashMap<String, Object> map) {
        JSONObject jsonData = new JSONObject(map);
        new Gson().toJson(jsonData);
        return jsonData.toString();
    }

    private static HttpParams hashSend(HashMap<String, Object> map) {
        HttpParams paramsData = new HttpParams();
        for (String key : map.keySet()) {
            paramsData.put(key, String.valueOf(map.get(key)));
        }
        return paramsData;
    }

    private static <K> String jsonSend(final K tClassJson) {
        return new Gson().toJson(tClassJson);
    }


    public interface OnResult<T> {
        void onSuccess(T response);

        void onError(String e);
    }
}
