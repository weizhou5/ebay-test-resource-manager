package com.ebay.manager.system.resource.service.impl;

import com.ebay.manager.system.resource.dto.ResourceAccessDTO;
import com.ebay.manager.system.resource.service.FileDbService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.ArrayList;

@RunWith(PowerMockRunner.class)
public class TestResourceServiceImpl {
    @InjectMocks
    ResourceServiceImpl resourceService;
    @Mock
    FileDbService fileDbService;


    @Test
    public void testAddUser(){
        String userId = "123456";
        String resource = "resource A";
        ResourceAccessDTO resourceAccessDTO = new ResourceAccessDTO();
        ArrayList<String> endpoints = new ArrayList<>();
        endpoints.add(resource);
        resourceAccessDTO.setEndpoint(endpoints);
        resourceAccessDTO.setUserId(userId);
        Mockito.when(fileDbService.update(userId, endpoints)).thenReturn(true);
        boolean success = resourceService.addUser(resourceAccessDTO);
        Assert.assertTrue(success);

        Mockito.when(fileDbService.update(userId, endpoints)).thenReturn(false);
        success = resourceService.addUser(resourceAccessDTO);
        Assert.assertFalse(success);
    }

    @Test
    public void testIsAccess(){
        String userId = "123456";
        String resource = "resource A";


        Mockito.when(fileDbService.isExistResourceAccess(userId, resource)).thenReturn(true);
        boolean success = resourceService.isAccess(userId, resource);
        Assert.assertTrue(success);

        Mockito.when(fileDbService.isExistResourceAccess(userId, resource)).thenReturn(false);
        success = resourceService.isAccess(userId, resource);
        Assert.assertFalse(success);

    }
}
