package com.ebay.manager.system.resource.entity;

public class UserThreadLocal {
    private static final ThreadLocal<UserHeader> userHeaderThreadLocal = new ThreadLocal<>();
    public static void set(UserHeader userHeader){
        userHeaderThreadLocal.set(userHeader);
    }
    public static UserHeader get(){
        return userHeaderThreadLocal.get();
    }
    public static void remove(){
        userHeaderThreadLocal.remove();
    }
}
