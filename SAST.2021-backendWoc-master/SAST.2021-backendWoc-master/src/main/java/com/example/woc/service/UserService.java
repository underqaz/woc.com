package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
import com.example.woc.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.UUID;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    //示例
    public void test(String test) {
        userMapper.test(test);
    }

    public void register(Account account) throws UserException{
      String username = userMapper.selectByName(account.getUsername());
       if(username!=null&&username.equals(account.getUsername())){
           System.out.println("用户名已存在");
           throw new UsernameDuplicateException();
       }else if(username==null){
           String salt= UUID.randomUUID().toString().toUpperCase();
           account.setSalt(salt);
           account.setRole(0);
           account.setPassword(encrypt(salt,account.getPassword()));
           if(userMapper.insert(account)!=1) throw new InsertException();
           System.out.println("注册成功");
       }
    }

    public String encrypt(String salt,String password){
//        DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        for(int i=0;i<3;i++){
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }

    public Account load(Account account) throws UserException {
        String username = userMapper.selectByName(account.getUsername());
        if (username == null) {
            throw new UserNotFoundException();
        } else {
            Account a = userMapper.findAccount(username);
            String password = encrypt(a.getSalt(), account.getPassword());
            if (!a.getPassword().equals(password)) {
                throw new PasswordNotMatchException();
            } else {
//                return new JsonResult<Boolean>(200, true, "登陆成功");
                return a;
            }
//        }
//        if (username!=null) {
//            Account a = userMapper.findAccount(username);
//            String password = encrypt(a.getSalt(), account.getPassword());
//            if (a.getPassword().equals(password)) {
//                System.out.println("登陆成功！");
//                return new JsonResult<Boolean>(200,true,"登陆成功");
//            }
//            else {
//                System.out.println("密码错误");
//                return new JsonResult<Boolean>(300, false, "密码错误");
//            }
//        }else {
//            System.out.println("用户名不存在");
//            return new JsonResult<Boolean>(400, false, "用户名不存在");
//        }
        }
    }

    public Integer getAmount(){
        return userMapper.selectAllNums();
    }

    public Integer deleteByName(String username){
       return userMapper.deleteByName(username);
    }

    public String selectByName(String username){
        return userMapper.selectByName(username);
    }
}
