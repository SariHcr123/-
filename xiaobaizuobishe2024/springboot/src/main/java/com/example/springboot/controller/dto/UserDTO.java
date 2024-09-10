package com.example.springboot.controller.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

/*
 * 接收前端登录请求的参数
 */

@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
    private String token;
}
