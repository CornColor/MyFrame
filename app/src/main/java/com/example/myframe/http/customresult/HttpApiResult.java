package com.example.myframe.http.customresult;

import com.zhouyou.http.model.ApiResult;

/**
 * created by dan
 * 按照自己的服务器返回的JSON数据格式编写返回code  msg  data
 */
public class HttpApiResult<T> extends ApiResult<T> {
    //默认的格式
//    private int code;
//    private String msg;
//    private T data;

    private int my_code;
    private String my_msg;
    private T data;

    public int getMy_code() {
        return my_code;
    }

    public void setMy_code(int my_code) {
        this.my_code = my_code;
    }

    public String getMy_msg() {
        return my_msg;
    }

    public void setMy_msg(String my_msg) {
        this.my_msg = my_msg;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }
}
