package com.example.woc.controller;

import com.example.woc.mapper.UserMapper;
import com.example.woc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 04:19
 **/
@RestController
@RequestMapping("/admin")
public class AdminController {

    //请仿照 User 补充 Admin 的三层架构并完成接口
    @Autowired
    private UserService userService;
    /**
     * 获取当前的账户总数
     * @return
     */
    @GetMapping("/getAmount")
    public Integer getAmountOfAccounts(){


        //todo

        return userService.getAmount();
    }

    /**
     * 根据用户名删除账户
     * @param username
     */
    @GetMapping("/deleteAccount/{username}")
    public Integer deleteAccount(@PathVariable  String username){

        //todo
      return  userService.deleteByName(username);
    }
}
