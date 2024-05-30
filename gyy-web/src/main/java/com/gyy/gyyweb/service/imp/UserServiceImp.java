package com.gyy.gyyweb.service.imp;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.gyy.gyyweb.mapper.UserMapper;
import com.gyy.gyyweb.pojo.PageBean;
import com.gyy.gyyweb.pojo.User;
import com.gyy.gyyweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserMapper userMapper;

    //登录校验

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    //查询全部
    @Override
    public List<User> list() {
        return userMapper.list();
    }


    //新增
    @Override
    public void add(User user) {
        userMapper.add(user);
    }

    //删除
    @Override
    public void delete(Integer id) {
        userMapper.delete(id);
    }

    //更新
    @Override
    public void update(User user) {
        userMapper.update(user);
    }


    //根据id查询
    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public PageBean page(Integer page, Integer pageSize, String name,String phone,Integer sex) {
        PageHelper.startPage(page,pageSize);
        List<User> users = userMapper.page(name,phone,sex);
        Page<User> p = (Page<User>)users;
        return new PageBean((int)p.getTotal(),p.getResult());
    }
}
