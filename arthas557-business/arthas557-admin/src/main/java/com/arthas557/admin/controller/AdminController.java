package com.arthas557.admin.controller;

import com.arthas557.common.BaseResult;
import com.arthas557.common.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/admin")
@Api(description = "admin swagger测试")
public class AdminController {

    @RequestMapping("/sayAdmin")
    @ApiOperation("查询用户")
    public BaseResult<User > sayAdmin(){
        System.out.println("HELLO222222222222222");
        BaseResult<User> result = new BaseResult<>();
        User user = new User();
        user.setPassword("aaaaaaaaaaa");
        user.setUserName("bbbbbbbbbbbbbb");
        result.setData(user);
        return result;
    }

    @RequestMapping("/jsonException")
    public BaseResult jsonException (@RequestBody User user){
        BaseResult<User> result = new BaseResult<>();
         result.setData(user);
        return result;
    }
}
