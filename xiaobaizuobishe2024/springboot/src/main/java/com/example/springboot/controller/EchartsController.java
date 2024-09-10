package com.example.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.springboot.common.Result;
import com.example.springboot.entity.Shuju;
import com.example.springboot.entity.User;
import com.example.springboot.mapper.ShujuMapper;
import com.example.springboot.service.IShujuService;
import com.example.springboot.service.IUserService;
import org.apache.poi.sl.draw.binding.CTVector3D;
import org.apache.xmlbeans.DelegateXmlObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/echarts")
public class EchartsController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IShujuService shujuService;

    @Resource
    private ShujuMapper shujuMapper;



    @GetMapping("/example")
    public Result get(){
        Map<String,Object> map = new HashMap<>();
        map.put("x", CollUtil.newArrayList("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"));
        map.put("y",CollUtil.newArrayList(150, 230, 224, 218, 135, 147, 260));
        return Result.success(map);
    }

    @GetMapping("/members")
    public Result members(){
        List<User> list = userService.list();
        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        for (User user : list) {
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch(quarter){
                case Q1: q1 +=1; break;
                case Q2: q2 +=1; break;
                case Q3: q3 +=1; break;
                case Q4: q4 +=1; break;
                default: break;
            }
        }
        return Result.success(CollUtil.newArrayList(q1,q2,q3,q4));
    }

    @GetMapping
    public Object shujumembers(){

    return shujuMapper.selectList(Wrappers.lambdaQuery());
}


}
