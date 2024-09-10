package com.example.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.springboot.Service.UserService;
import com.example.springboot.SpringbootApplication;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


        @Autowired
        private UserService userService;
        @PostMapping
        public boolean save(@RequestBody User user){

            //新增或者更新
            return userService.saveUser(user);
        }
        //查询所有数据
        @GetMapping
        public List<User> findall() {
            return userService.list();
        }

        @DeleteMapping("/{id}")
        public boolean delete(@PathVariable Integer id){
            return userService.removeById(id);
           }

           @PostMapping("/del/batch")
           public boolean deleteBatch(@RequestBody List<Integer> ids){
            return userService.removeByIds(ids);
           }

           //分页查询
    //接口路径：/user/page
    //@RequestParam接收
    //limit 第一个参数=(rag0-1)*rag1
    //pageSize
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address) {
    IPage<User> page = new Page<>(pageNum,pageSize);
    QueryWrapper<User> queryWrapper = new QueryWrapper<>();
    if (!username.equals(username)){
        queryWrapper.like("username",username);
    }
    if (!"".equals(email)){
        queryWrapper.like("email",email);
    }
    if (!"".equals(address)){
        queryWrapper.like("address",address);
    }
    queryWrapper.orderByDesc("id");
       return userService.page(page,queryWrapper);
    }

}


