package com.example.demo.JSONAndConfig;

public class JsonResult<T> {
    private T data;
    private long code;
    private String msg;

    private Object totalNum;

    public Object getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Object totalNum) {
        this.totalNum = totalNum;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 若没有数据返回，默认状态码为 0，提示信息为“操作成功！”
     */
    public JsonResult() {
        this.code = 0;
        this.msg = "操作成功！";
    }

    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     * @param code
     * @param msg
     */
    public JsonResult(long code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    /**
     * 有数据返回时，状态码为 0，默认提示信息为“操作成功！”
     * @param data
     */
    public JsonResult(T data) {
        this.data = data;
        this.code = 0;
        this.msg = "操作成功！";
    }

    /**
     * 有数据返回，状态码为 0，人为指定提示信息
     * @param data
     * @param msg
     */
    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = 0;
        this.msg = msg;
    }
    // 省略 get 和 set 方法

    /**
     * 有数据返回，code记录查询时间，必填
     */
    public JsonResult(T data,String msg,long code,Object totalNum){
        this.totalNum=totalNum;
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    /**
     * 可选
     * @param data
     * @param msg
     * @param code
     */
    public JsonResult(T data,String msg,long code){
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

}
