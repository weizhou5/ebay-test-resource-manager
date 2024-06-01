package com.ebay.manager.system.resource.service;

import com.ebay.manager.system.resource.dto.ResourceAccessDTO;

public interface ResourceService {

    boolean addUser(ResourceAccessDTO resourceAccessDTO);

    boolean isAccess(String userId, String resource);
}
