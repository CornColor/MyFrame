package com.example.myframe.http.customresult;

import com.zhouyou.http.callback.CallBack;
import com.zhouyou.http.callback.CallBackProxy;

/**
 * created by dan
 * 请求返回
 */
public  class ApiHttpCallBack {
    //可以根据自己的业务需求更改 HttpApiResult
    public static  <T>CallBackProxy<HttpApiResult<T>, T> createCallBackProxy(CallBack<T> callBack) {
      CallBackProxy<HttpApiResult<T>,T>  callBackProxy = new CallBackProxy<HttpApiResult<T>, T>(callBack){
        };
        return callBackProxy;
    }
}
