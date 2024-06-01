package com.ebay.manager.system.resource.utils;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StringLockManager {
    private static final ConcurrentHashMap<String, ReentrantLock> lockMap = new ConcurrentHashMap<>();

    public static ReentrantLock getLock(String key) {
        return lockMap.computeIfAbsent(key, k -> new ReentrantLock());
    }
    public static void unlock(String key){
        if(lockMap.containsKey(key)){
            ReentrantLock reentrantLock = lockMap.get(key);
            lockMap.remove(key);
            reentrantLock.unlock();
        }
    }
}
