package com.ebay.manager.system.resource.execption;

import com.ebay.manager.system.resource.common.MsgCode;

public class BaseException extends RuntimeException {
    private int code;

    public BaseException(MsgCode msgCode) {
        super(msgCode.getMsg());
        this.code = msgCode.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
