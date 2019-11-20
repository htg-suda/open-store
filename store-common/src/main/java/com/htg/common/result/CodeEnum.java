package com.htg.common.result;

public enum CodeEnum {
    SUCCESS(0, "操作成功"),
    ERROR(-1, "操作失败"),
    DATA_BASE_ERROR(-2, "数据操作失败"),
    STATUS_VALUE_ERROR(-3, "设置状态值异常"),
    PARAM_ERROR(-1000, "参数异常"),
    USER_NOT_EXIST(-2001, "用户不存在"),
    USERNAMRE_HAS_EXIST(-2002, "用户名已存在"),
    USER_HAS_FREED(-2003, "用户已经冻结"),
    TEL_HAS_EXIST(-2004, "手机号已经存在"),
    TEL_NOT_EXIST(-2005, "手机号码不存在"),
    EMAIL_NOT_EXIST(-2006, "邮箱不存在"),
    EMAIL_HAS_EXIST(-2007, "邮箱已经存在"),

    PARENT_MENU_NOT_EXIST(-2007, "父菜单不存在"),

    MENU_PARENT_NOT_BUTTON(-2009, "不能按钮添加子菜单"),
    BUTTON_TYPE_ONLY(-2010, "父菜单是菜单,子菜单只能是按钮类型"),
    PARENT_TYPE_CAN_NOT_BE_BUTTON(-2011, "不能为按钮类型添加子菜单"),
    PARENT_TYPE_ERROR(-2012, "父菜单类型异常"),
    MENU_NOT_EXIST(-2013, "菜单不存在"),
    ROLE_NOT_EXIST(-2014, "角色不存在"),
    ROLE_HAS_EXIST(-2015, "角色已经存在,不能重复添加该角色");

    private int code;
    private String msg;

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
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
}
