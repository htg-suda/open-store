package com.htg.common.exception;

import com.htg.common.result.CodeEnum;

/* 业务逻辑异常 */
public class BizExcp extends RuntimeException {
    private CodeEnum codeEnum;

    public BizExcp(CodeEnum codeEnum) {
        this.codeEnum = codeEnum;
    }

    public Integer getCode() {
        return codeEnum.getCode();
    }

    public String getMsg() {
        return codeEnum.getMsg();
    }

    @Override
    public String toString() {
        return "BizExcp{" +
                "codeEnum=" + codeEnum +
                '}';
    }
}
