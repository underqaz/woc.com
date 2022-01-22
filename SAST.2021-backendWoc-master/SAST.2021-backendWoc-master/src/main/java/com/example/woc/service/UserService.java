package com.example.woc.service;

import com.example.woc.entity.Account;
import com.example.woc.mapper.UserMapper;
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

    public void register(Account account){
      String username = userMapper.selectByName(account.getUsername());
       if(username.equals(account.getUsername())){
           System.out.println("用户名已存在");
           return;
       }else{
           String salt= UUID.randomUUID().toString().toUpperCase();
           account.setSalt(salt);
           account.setPassword(encrypt(salt,account.getPassword()));
           userMapper.insert(account);
           System.out.println("注册成功");
       }
    }

    public String encrypt(String salt,String password){
        DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        for(int i=0;i<3;i++){
            password=DigestUtils.md5DigestAsHex((salt+password+salt).getBytes()).toUpperCase();
        }
        return password;
    }

    public boolean load(Account account){
        String password=encrypt(account.getSalt(),account.getPassword());
        Account a=userMapper.findAccount(account.getUsername());
        if(a.getPassword().equals(password)) return true;
        else return false;
    }

    public Integer getAmount(){
        return userMapper.selectAllNums();
    }

    public Integer deleteByName(String username){
       return userMapper.deleteByName(username);
    }
}
