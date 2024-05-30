package com.gyy.gyyweb.service;


import com.gyy.gyyweb.pojo.PageBean;
import com.gyy.gyyweb.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

     User login(User user);

    //查询全部
    public List<User> list();

    //新增
    void add(User user);

    void delete(Integer id);

    void update(User user);

    User getUserById(Integer id);

    PageBean page(Integer page, Integer pageSize, String name,String phone,Integer sex);
}
