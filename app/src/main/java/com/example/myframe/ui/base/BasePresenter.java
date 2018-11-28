package com.example.myframe.ui.base;

import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * created by dan
 */
public class BasePresenter<T>{
    private WeakReference<T> view;

    /**
     * 绑定回调对象
     * @param t
     */
    public void attachView(T t){
        view = new WeakReference<T>(t);
    }

    /**
     * 获取绑定的对象
     * @return
     */
    public T getView(){
        if(view != null){
            T t = view.get();
            if(t == null) throw new NullPointerException("presenter not binding callback !");
            return view.get();
        }else {
            throw new NullPointerException("presenter not binding callback !");
        }
    }

    /**
     * 销毁
     */
    public void removeAttach(){
          if(view!=null){
              view.clear();
              view = null;
          }
    }




}
