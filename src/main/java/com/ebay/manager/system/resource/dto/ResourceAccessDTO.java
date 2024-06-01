package com.ebay.manager.system.resource.dto;

import java.util.List;

public class ResourceAccessDTO {
    private String userId;
    List<String> endpoint;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(List<String> endpoint) {
        this.endpoint = endpoint;
    }
}
