package com.htg.common.result;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/* 为空的属性就不去JSON 序列化 */
@JsonInclude(Include.NON_NULL)
public class CommonList<T> {
    private int code;
    private String msg;
    private List<T> data;

    private CommonList() {

    }

    private CommonList(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private CommonList(int code, String msg, List<T> data) {
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

    public static <T> CommonList<T> success(List<T> data) {
        return new CommonList(CodeEnum.SUCCESS.getCode(), CodeEnum.SUCCESS.getMsg(), data);
    }

    public static <T> CommonList<T> success(String msg) {

        return new CommonList(CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> CommonList<T> success(int code, String msg, List<T> data) {

        return new CommonList(code, msg, data);
    }


    public static <T> CommonList<T> error(String msg) {

        return new CommonList(CodeEnum.ERROR.getCode(), msg);
    }


    public static <T> CommonList<T> error(int code, String msg) {

        return new CommonList(code, msg);
    }

    public static <T> CommonList<T> error(int code, String msg, List<T> data) {

        return new CommonList(code, msg, data);
    }

    public static <T> CommonList<T> error(CodeEnum codeEnum) {

        return new CommonList(codeEnum.getCode(), codeEnum.getMsg());
    }

    public static <T> CommonList<T> error(CodeEnum codeEnum, List<T> data) {

        return new CommonList(codeEnum.getCode(), codeEnum.getMsg(), data);
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

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
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
