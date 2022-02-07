package com.example.woc.interceptor;


import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import com.example.woc.exception.UserException;
import com.example.woc.exception.UserMightDeficiencyException;
import com.example.woc.exception.UserNotLoginException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class UserInterceptor implements HandlerInterceptor {

    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws UserException {
        //拦截用户权限不足时的操作
        Cookie[] cookies= request.getCookies();
        HttpSession session= request.getSession();
        for(Cookie cookie:cookies){
            if(cookie.getName().equals("uid")){
                 String uid=cookie.getValue();
                 Account account= (Account) session.getAttribute(uid);
                 if(account.getRole()==1) return true;
                 else throw new UserMightDeficiencyException();
            }
        }
//        response.sendRedirect("/user/login");
        throw new UserNotLoginException();
//        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
