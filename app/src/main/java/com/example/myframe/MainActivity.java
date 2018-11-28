package com.example.myframe;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

import com.example.myframe.db.User;
import com.example.myframe.http.customresult.ApiHttpCallBack;
import com.example.myframe.http.customresult.HttpApiResult;
import com.example.myframe.ui.base.BaseActivity;
import com.example.myframe.ui.base.WeakReferenceHandle;
import com.example.myframe.ui.dialog.CoustomDialog;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.CallBackProxy;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;
import com.zhouyou.http.model.ApiResult;

import cn.pedant.SweetAlert.SweetAlertDialog;


public class MainActivity extends BaseActivity implements Handler.Callback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void onDelGet() {
        // 使用默认的ApiResult 返回的结果
        EasyHttp.get("请求地址")
                .readTimeOut(30 * 1000)
                .writeTimeOut(30 * 1000)
                .connectTimeout(30 * 1000)
                .execute(new SimpleCallBack<User>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(User user) {

                    }
                });


    }

    /**
     * 自定义的
     */
    private void onCustomGet() {
        EasyHttp.get("请求地址")
                .readTimeOut(30 * 1000)
                .writeTimeOut(30 * 1000)
                .connectTimeout(30 * 1000)
                .execute(ApiHttpCallBack.createCallBackProxy(new SimpleCallBack<User>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(User user) {

                    }
                }));

    }

    /**
     * post请求
     */
    public void onDelPost(View view) {
        EasyHttp.post("v1/app/chairdressing/news/favorite")
                .params("newsId", "552")
                .accessToken(true)
                .timeStamp(true)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String response) {
                        showToast(response);
                    }
                });
    }
    /**
     * post请求
     */
    public void onPost(View view) {
        EasyHttp.post("v1/app/chairdressing/news/favorite")
                .params("newsId", "552")
                .accessToken(true)
                .timeStamp(true)
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        showToast(e.getMessage());
                    }

                    @Override
                    public void onSuccess(String response) {
                        showToast(response);
                    }
                });
    }
    /**
     * post请求
     */
    public void onCustamPost(View view) {
        EasyHttp.post("v1/app/chairdressing/news/favorite")
                .params("newsId", "552")
                .accessToken(true)
                .timeStamp(true)
                .execute(ApiHttpCallBack.createCallBackProxy(new SimpleCallBack<User>() {
                    @Override
                    public void onError(ApiException e) {

                    }

                    @Override
                    public void onSuccess(User user) {

                    }
                }));
    }
    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initPresenter() {

    }


    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }
}
