package com.htg.common.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/* 为空的属性就不去JSON 序列化 */
@JsonInclude(Include.NON_NULL)
public class CommonResult<T> {
    private int code;
    private String msg;
    private T data;

    private CommonResult() {

    }

    private CommonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private CommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /* success  不参与JSON 序列化 */
    @JsonIgnore
    public boolean isSuccess() {
        if (this.getCode() == CodeEnum.SUCCESS.getCode()) {
            return true;
        }
        return false;
    }

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> CommonResult<T> success(String msg) {

        return new CommonResult(CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> CommonResult<T> success(int code, String msg, T data) {

        return new CommonResult(code, msg, data);
    }


    public static <T> CommonResult<T> error(String msg) {

        return new CommonResult(CodeEnum.ERROR.getCode(), msg);
    }


    public static <T> CommonResult<T> error(int code, String msg) {

        return new CommonResult(code, msg);
    }

    public static <T> CommonResult<T> error(int code, String msg, T data) {

        return new CommonResult(code, msg, data);
    }

    public static <T> CommonResult<T> error(CodeEnum codeEnum) {

        return new CommonResult(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static <T> CommonResult<T> error(CodeEnum codeEnum, T data) {

        return new CommonResult(codeEnum.getCode(), codeEnum.getMsg(), data);
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "CommonResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


}
