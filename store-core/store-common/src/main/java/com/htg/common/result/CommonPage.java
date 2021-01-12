package com.htg.common.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/* 为空的属性就不去JSON 序列化 */
@JsonInclude(Include.NON_NULL)
public class CommonPage<T> {
    private int code;
    private String msg;
    private RespPage<T> data;

    private CommonPage() {

    }

    private CommonPage(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private CommonPage(int code, String msg, RespPage<T> data) {
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

    public static <T> CommonPage<T> success(RespPage<T> data) {
        return new CommonPage(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> CommonPage<T> success(String msg) {

        return new CommonPage(CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> CommonPage<T> success(int code, String msg, RespPage<T> data) {

        return new CommonPage(code, msg, data);
    }


    public static <T> CommonPage<T> error(String msg) {

        return new CommonPage(CodeEnum.ERROR.getCode(), msg);
    }


    public static <T> CommonPage<T> error(int code, String msg) {

        return new CommonPage(code, msg);
    }

    public static <T> CommonPage<T> error(int code, String msg, RespPage<T> data) {

        return new CommonPage(code, msg, data);
    }

    public static <T> CommonPage<T> error(CodeEnum codeEnum) {

        return new CommonPage(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static <T> CommonPage<T> error(CodeEnum codeEnum, RespPage<T> data) {

        return new CommonPage(codeEnum.getCode(), codeEnum.getMsg(), data);
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

    public RespPage<T> getData() {
        return data;
    }

    public void setData(RespPage<T> data) {
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
