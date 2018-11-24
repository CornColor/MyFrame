package com.example.myframe;

import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;

import com.example.myframe.http.ApiService;
import com.example.myframe.other.AppManager;
import com.zhouyou.http.EasyHttp;

/**
 * created by dan
 */
public class MyApp extends Application {
   private static MyApp mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        registerLifecycle();
        //初始化网络框架
        initHttp();

    }


    public static MyApp getInstance(){
        return  mInstance;
    }

    /**
     * 初始化网络框架
     */
    public void initHttp(){
        EasyHttp.init(this);
//        EasyHttp.getInstance()
//                .setBaseUrl(ApiService.HOST_URL);
    }

    /**
     * 注册生命周期管理
     */
     private void registerLifecycle(){
         //SDK版本大于等于14
         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
             registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                 @Override
                 public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                     AppManager.getInstance().addActivity(activity);

                 }

                 @Override
                 public void onActivityStarted(Activity activity) {

                 }

                 @Override
                 public void onActivityResumed(Activity activity) {

                 }

                 @Override
                 public void onActivityPaused(Activity activity) {

                 }

                 @Override
                 public void onActivityStopped(Activity activity) {

                 }

                 @Override
                 public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                 }

                 @Override
                 public void onActivityDestroyed(Activity activity) {
                     AppManager.getInstance().finishActivity(activity);

                 }
             });
         }

     }
}