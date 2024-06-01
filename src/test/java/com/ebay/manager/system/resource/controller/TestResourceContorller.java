package com.ebay.manager.system.resource.controller;

import com.alibaba.fastjson.JSON;
import com.ebay.manager.system.resource.common.Constant;
import com.ebay.manager.system.resource.dto.ResourceAccessDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestResourceContorller {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddUser()throws Exception{
        String userId="123456";
        String role = "admin";
        String accountName = "xxxx";
        String resource = "A";
        ResourceAccessDTO resourceAccessDTO = new ResourceAccessDTO();
        resourceAccessDTO.setUserId(userId);
        List<String> endpoints = new ArrayList<>();
        endpoints.add(resource);
        resourceAccessDTO.setEndpoint(endpoints);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/admin/addUser")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSON.toJSONString(resourceAccessDTO))
                .header(Constant.ROLE, Base64.getEncoder().encodeToString(role.getBytes()))
                .header(Constant.USER_ID, Base64.getEncoder().encodeToString(userId.getBytes()))
                .header(Constant.ACCOUNT_NAME, Base64.getEncoder().encodeToString(accountName.getBytes()))
        )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }

    @Test
    public void testAccess()throws Exception{
        String userId="123456";
        String role = "admin";
        String accountName = "xxxx";
        String resource = "A";

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(String.format("/user/%s", resource))
                .contentType(MediaType.APPLICATION_JSON)
                .header(Constant.ROLE, Base64.getEncoder().encodeToString(role.getBytes()))
                .header(Constant.USER_ID, Base64.getEncoder().encodeToString(userId.getBytes()))
                .header(Constant.ACCOUNT_NAME, Base64.getEncoder().encodeToString(accountName.getBytes()))
        )
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        Assert.assertEquals(200, status);
    }
}
