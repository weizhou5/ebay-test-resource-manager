package com.ebay.manager.system.resource.controller;
import com.ebay.manager.system.resource.dto.ResourceAccessDTO;
import com.ebay.manager.system.resource.entity.UserHeader;
import com.ebay.manager.system.resource.entity.UserThreadLocal;
import com.ebay.manager.system.resource.service.ResourceService;
import com.ebay.manager.system.resource.vo.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResourceController {

    @Autowired
    ResourceService resourceService;

    @PostMapping(value = "/admin/addUser")
    public Response<String> addUser(@RequestBody ResourceAccessDTO resourceAccessDTO){
        boolean success = resourceService.addUser(resourceAccessDTO);
        if(success){
            return Response.SUCCESS("");
        }else {
            return Response.FAILED("");
        }
    }

    @GetMapping(value = "/user/{resource}")
    public Response<String> queryResource(@PathVariable String resource){
        UserHeader userHeader = UserThreadLocal.get();
        if(userHeader == null){
            return Response.FAILED("do not have this access");
        }
        String userId =userHeader.getUserId();
        boolean success = resourceService.isAccess(userId, resource);
       if(success){
           return Response.SUCCESS("have this access");
       }else {
           return Response.FAILED("do not have this access");
       }
    }
}
