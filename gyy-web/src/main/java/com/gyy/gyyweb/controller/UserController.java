package com.gyy.gyyweb.controller;


import com.gyy.gyyweb.pojo.PageBean;
import com.gyy.gyyweb.pojo.Result;
import com.gyy.gyyweb.pojo.User;
import com.gyy.gyyweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //查询全部

    @GetMapping("/list")
    public Result list(){
        List<User> users = userService.list();
        return Result.success(users);
    }

    //新增
    @PostMapping("/add")
    public Result add(@RequestBody User user){
        userService.add(user);
        return Result.success();
    }

    //删除
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id){
        userService.delete(id);
        return Result.success();
    }

    //修改
    @PutMapping("/update")
    public Result update(@RequestBody User user){
        userService.update(user);
        return Result.success();
    }


    //根据id查询
    @GetMapping("/find/{id}")
    public Result getUserById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    //条件动态分页查询
    @GetMapping("/list1")
    public Result page(@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "10")Integer pageSize,String name,String phone,Integer sex){
            PageBean pageBean = userService.page(page,pageSize,name,phone,sex);
            return Result.success(pageBean);
    }



    //登录校验
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        User user1 = userService.login(user);
        if(user1 != null){
            return Result.success(user1);
        }
        else return Result.error("账号或密码错误");
    }

}
