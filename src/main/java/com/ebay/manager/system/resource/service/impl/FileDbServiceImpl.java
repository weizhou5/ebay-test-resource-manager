package com.ebay.manager.system.resource.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ebay.manager.system.resource.service.FileDbService;
import com.ebay.manager.system.resource.utils.FileUtils;
import com.ebay.manager.system.resource.utils.StringLockManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class FileDbServiceImpl implements FileDbService {
    @Override
    public boolean update(String userId, List<String> endpoints) {
        ReentrantLock lock = StringLockManager.getLock(userId);
        try{
            lock.lock();
            String file = String.format("%s.txt", userId);
            if(FileUtils.isExist(file)){
                String s = FileUtils.readFile(file);
                List<String> historyEndpoints = JSONArray.parseArray(s, String.class);
                mergeList(historyEndpoints, endpoints);
            }
            return FileUtils.OverwriteFile(file, JSON.toJSONString(endpoints));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }finally {
            StringLockManager.unlock(userId);
        }
    }
    public void mergeList(List<String> source, List<String> target){
        for (String s : source) {
            if(!target.contains(s)){
                target.add(s);
            }
        }
    }

    @Override
    public boolean isExistResourceAccess(String userId, String resource) {
        String file = String.format("%s.txt", userId);
        if(!FileUtils.isExist(file)){
            return false;
        }
        String content = FileUtils.readFile(file);
        List<String> endpoint = JSONArray.parseArray(content, String.class);
        if(endpoint.contains(resource)){
            return true;
        }else {
            return false;
        }
    }
}
