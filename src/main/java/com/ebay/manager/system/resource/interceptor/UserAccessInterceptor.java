package com.ebay.manager.system.resource.interceptor;

import com.ebay.manager.system.resource.common.Constant;
import com.ebay.manager.system.resource.common.MsgCode;
import com.ebay.manager.system.resource.execption.BaseException;
import com.ebay.manager.system.resource.execption.HeaderException;
import com.ebay.manager.system.resource.service.FileDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Base64;
import java.util.regex.Pattern;


@Component
public class UserAccessInterceptor implements HandlerInterceptor {
    String patternString = "^/user/.*";
    Pattern userReosurceUrlPattern = Pattern.compile(patternString);
    @Autowired
    FileDbService fileDbService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = getHeader(request, Constant.USER_ID);
        String role = getHeader(request, Constant.ROLE);
        String accountName = getHeader(request, Constant.ACCOUNT_NAME);
        if(StringUtils.isEmpty(userId) || StringUtils.isEmpty(role) || StringUtils.isEmpty(accountName)){
            throw new BaseException(MsgCode.HEADER_INVALID);
        }
        String uri = request.getRequestURI();
        if("/admin/addUser".equals(uri) && Constant.ADMIN_ROLE.equals(role)){
            return true;
        }else if("/admin/addUser".equals(uri)){
            throw new BaseException(MsgCode.NON_ADMIN);
        }
        if(userReosurceUrlPattern.matcher(uri).matches()){
            String[] split = uri.split("/");
            String resource = "";
            if(split.length == 3){
                resource = split[2];
            }
            if(fileDbService.isExistResourceAccess(userId,resource)){
                return true;
            }else {
                throw new BaseException(MsgCode.NOT_ACCESS);
            }
        }
        return true;

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
