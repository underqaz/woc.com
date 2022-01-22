package com.example.woc.mapper;

import com.example.woc.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: 風楪fy
 * @create: 2022-01-15 01:22
 **/
@Mapper
@Repository
public interface UserMapper {
    //示例
    void test(@Param("value") String test);

    Integer deleteByName(@Param("username") String username);

    Integer selectAllNums();

    String selectByName(@Param("username")String username);

    void insert(Account account);

    Account findAccount(@Param("username") String username);
}
