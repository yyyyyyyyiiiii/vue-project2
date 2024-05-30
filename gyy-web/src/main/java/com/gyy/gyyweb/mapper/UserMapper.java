package com.gyy.gyyweb.mapper;

import com.gyy.gyyweb.pojo.PageBean;
import com.gyy.gyyweb.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface UserMapper {


    @Select("select * from user where account = #{account} and password = #{password}")
    User login(User user);

    //新增
    @Insert("insert into user (account, name, password, age, sex, phone,role_id) values (#{account},#{name},#{password},#{age},#{sex},#{phone},#{roleId}) ")
     void add(User user);


    //查询全部
    @Select("select * from user")
    List<User> list();


    @Delete("delete from user where id = #{id}")
    void delete(Integer id);

    @Update("update user set account = #{account},name = #{name},password = #{password},age = #{age},sex = #{sex},phone = #{phone},role_id = #{roleId},is_valid = #{isValid} where id = #{id}")
    void update(User user);


    //@Select("select  * from user where id = #{id}")
    User getUserById(Integer id);

    List<User> page(@Param("name") String name,@Param("phone") String phone,@Param("sex")Integer sex);
}
