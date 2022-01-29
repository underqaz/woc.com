package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.entity.User;
import com.example.woc.service.UserService;
import com.example.woc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //这是一个示例,以POST方法提交数据
    @PostMapping("/simple")
    public void simple(String test) {
        //按住ctrl键来看看具体调用的这个函数吧
        userService.test(test);
    }

    /**
     * 完成注册功能
     * 选做：对密码进行加密处理
     * @param account
     */
    @PostMapping("/register")
    public void uploadUsername(Account account) {
        userService.register(account);
        //todo
//        return new JsonResult(200);
    }

    /**
     * 完成登录功能
     * @param account
     * @return 是否登录成功
     */
    @PostMapping("/login")
    public Boolean login(Account account) {
        //todo

        return userService.load(account);
    }

    @PostMapping("/test")
    public JsonResult<String> test(User user){
        return new JsonResult<String>(200,userService.selcetByName(user.getName()), user.getName());
    }



}


