package com.example.woc.controller;

import com.example.woc.entity.Account;
import com.example.woc.entity.User;
import com.example.woc.service.UserService;
import com.example.woc.exception.UserException;
import com.example.woc.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **
 * */
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
    public JsonResult<Void> uploadUsername(Account account) throws UserException{
        userService.register(account);
        return new JsonResult<Void>(200,null,"注册成功");
        //todo
//        return new JsonResult(200);
    }

    /**
     * 完成登录功能
     * @param account
     * @return 是否登录成功
     */
    @PostMapping("/login")
    public JsonResult<Boolean> login(Account account,HttpServletResponse response,HttpServletRequest request) throws UserException {
        //todo
        Cookie[] cookies = request.getCookies();
        HttpSession session= request.getSession();
        //看有无cookie
        for(Cookie c:cookies){
            if(c.getName().equals("uid")) {
                String uid = c.getValue();
                userService.load((Account) session.getAttribute(uid));
                return new JsonResult<Boolean>(200,true,"登陆成功");
            }
        }
        //无cookie时
        Account a=userService.load(account);
        if(a!=null){
            String uid= UUID.randomUUID().toString().toUpperCase();
            Cookie cookie=new Cookie("uid",uid);
            cookie.setPath("/");
            response.addCookie(cookie);
            session.setAttribute(uid,a);
        }
        return new JsonResult<Boolean>(200, true, "登陆成功");
    }

    @PostMapping("/test")
    public JsonResult<String> test(User user){

        return new JsonResult<String>(200,userService.selectByName(user.getName()), user.getName());
    }



}


