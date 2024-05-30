package com.gyy.gyyweb.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;
    private String account;
    private String name;
    private String password;

     private  int age;

    private int sex;

    private String phone;

    private int roleId;
    private String isValid;

}
