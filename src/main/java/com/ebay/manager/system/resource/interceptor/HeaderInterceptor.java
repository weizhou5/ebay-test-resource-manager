package com.ebay.manager.system.resource.interceptor;

import com.alibaba.fastjson.JSON;
import com.ebay.manager.system.resource.common.Constant;
import com.ebay.manager.system.resource.common.MsgCode;
import com.ebay.manager.system.resource.entity.UserHeader;
import com.ebay.manager.system.resource.entity.UserThreadLocal;
import com.ebay.manager.system.resource.execption.BaseException;
import com.ebay.manager.system.resource.execption.HeaderException;
import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;


@Component
public class HeaderInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try{
            String userId = getHeader(request, Constant.USER_ID);
            String role = getHeader(request, Constant.ROLE);
            String accountName = getHeader(request, Constant.ACCOUNT_NAME);
            UserHeader userHeader = new UserHeader();
            userHeader.setAccountName(accountName);
            userHeader.setRole(role);
            userHeader.setUserId(userId);
            UserThreadLocal.set(userHeader);
        }catch (Exception e){
            e.printStackTrace();
            throw new HeaderException(MsgCode.HEADER_DATA_ILLEGAL);
        }
        return true;

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
    public String getHeader(HttpServletRequest request, String key){
        String value = request.getHeader(key);
        if(StringUtils.isEmpty(value)){
            return "";
        }
        byte[] decodedBytes = Base64.getDecoder().decode(value);
        value = new String(decodedBytes);
        return value;
    }
}
