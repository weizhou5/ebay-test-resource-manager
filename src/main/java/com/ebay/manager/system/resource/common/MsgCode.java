package com.ebay.manager.system.resource.common;

public enum MsgCode {
    SUCCESS(200, "success"),
    FAILED(1001, "access failed"),
    HEADER_INVALID(1002, "invalid head"),
    HEADER_DATA_ILLEGAL(1003, "illegal head data"),
    USER_ILLEGAL(1004, "illegal user"),
    NON_ADMIN(1005, "non-admin have no access to this endpoint"),
    NOT_ACCESS(1006, "do not have this access"),
    ;
    int code;
    String msg;

    MsgCode(int code, String msg) {
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
