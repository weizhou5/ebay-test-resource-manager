package com.ebay.manager.system.resource.execption;

import com.ebay.manager.system.resource.common.MsgCode;

public class HeaderException extends BaseException {
    public HeaderException(MsgCode msgCode) {
        super(msgCode);
    }
}
