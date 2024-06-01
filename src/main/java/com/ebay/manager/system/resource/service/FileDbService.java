package com.ebay.manager.system.resource.service;

import java.util.List;

public interface FileDbService {
    boolean update(String userId, List<String> endpoint);

    boolean isExistResourceAccess(String userId, String resource);
}
