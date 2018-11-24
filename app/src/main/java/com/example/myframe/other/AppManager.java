package com.example.myframe.other;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * created by dan
 * Activity管理
 */
public class AppManager {
    public static Stack<Activity> activityStack;
    private static AppManager instance;

    private AppManager() {

    }

    public synchronized static AppManager getInstance() {
        if (instance == null) {
            synchronized (AppManager.class) {
                if (instance == null) {
                    instance = new AppManager();
                }
            }
        }
        return instance;
    }

    /**
     * 添加activity 到堆栈
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前activity（堆栈中最后一个压入的）
     * @return
     */
    public Activity currentActivity(){
        Activity activity = activityStack.lastElement();
        return  activity;
    }

    /**
     * 结束当前的activity
     */
    public void finishActivity(){
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定的Activity
     * @param activity
     */
    public void finishActivity(Activity activity){
       if(activity != null){
           activityStack.remove(activity);
           activity.finish();
           activity = null;
       }
    }

    /**
     * 结束所有的Activity
     */
    public void finishAllActivity(){
        for (int i = 0;i < activityStack.size();i++){
            if(null!= activityStack.get(i)){
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     * @param context
     */
    public void appExit(Context context){
        try{
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
            System.exit(0);
        }catch (Exception e){

        }

    }
}
