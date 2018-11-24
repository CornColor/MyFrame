package com.example.myframe.eventbus;

/**
 * created by dan
 * 事件对象
 */
public class MessageEvent {
    private Integer code;
    private String msg;

    public MessageEvent(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
