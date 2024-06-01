package com.ebay.manager.system.resource.config;

import com.ebay.manager.system.resource.interceptor.HeaderInterceptor;
import com.ebay.manager.system.resource.interceptor.UserAccessInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.annotation.Resource;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
    @Resource
    HeaderInterceptor headerInterceptor;
    @Resource
    UserAccessInterceptor userAccessInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(headerInterceptor);
        registry.addInterceptor(userAccessInterceptor);
        super.addInterceptors(registry);
    }
}
