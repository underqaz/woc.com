package com.example.woc.userExceptionHandler;

import com.example.woc.exception.*;
import com.example.woc.utils.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public JsonResult<Void> handler(UserException e){
        JsonResult<Void> result = new JsonResult<Void>();
        if (e instanceof UsernameDuplicateException) {
            result.setState(400);
            result.setMessage("用户名重复");
        } else if (e instanceof UserNotFoundException) {
            result.setState(401);
            result.setMessage("用户未找到");
        } else if (e instanceof PasswordNotMatchException) {
            result.setState(402);
            result.setMessage("密码不匹配");
        } else if (e instanceof UserMightDeficiencyException){
            result.setState(403);
            result.setMessage("用户权限不足");
        } else if (e instanceof UserNotLoginException){
            result.setState(404);
            result.setMessage("请先登录");
        } else if (e instanceof InsertException) {
            result.setState(500);
            result.setMessage("异常错误");
        }
        return result;
    }
}
