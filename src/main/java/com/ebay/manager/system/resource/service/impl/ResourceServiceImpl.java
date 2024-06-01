package com.ebay.manager.system.resource.service.impl;

import com.ebay.manager.system.resource.common.MsgCode;
import com.ebay.manager.system.resource.dto.ResourceAccessDTO;
import com.ebay.manager.system.resource.execption.BaseException;
import com.ebay.manager.system.resource.service.FileDbService;
import com.ebay.manager.system.resource.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class ResourceServiceImpl implements ResourceService {
    @Autowired
    FileDbService fileDbService;
    @Override
    public boolean addUser(ResourceAccessDTO resourceAccessDTO) {
        if(StringUtils.isEmpty(resourceAccessDTO.getUserId())){
            throw new BaseException(MsgCode.HEADER_DATA_ILLEGAL);
        }
        return fileDbService.update(resourceAccessDTO.getUserId(), resourceAccessDTO.getEndpoint());
    }

    @Override
    public boolean isAccess(String userId, String resource) {
        if(StringUtils.isEmpty(userId)){
            throw new BaseException(MsgCode.HEADER_DATA_ILLEGAL);
        }
        return fileDbService.isExistResourceAccess(userId, resource);
    }
}
