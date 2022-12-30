package com.scott.service.impl;

import com.scott.config.SecurityProperties;
import com.scott.service.dto.JwtUserDto;
import com.scott.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * project name  my-eladmin-backend1
 * filename  OnlineUserService
 * @author liscott
 * @date 2022/12/30 17:04
 * description  TODO
 */
@Service
@Slf4j
public class OnlineUserService {

    private final SecurityProperties properties;
    private final RedisUtils redisUtils;

    public OnlineUserService(SecurityProperties properties, RedisUtils redisUtils) {
        this.properties = properties;
        this.redisUtils = redisUtils;
    }

    /**
     * 保存在线用户信息
     *
     * @param jwtUserDto /
     * @param token      /
     * @param request    /
     */
    public void save(JwtUserDto jwtUserDto, String token, HttpServletRequest request) {
        redisUtils.set(properties.getOnlineKey() + token, jwtUserDto, properties.getTokenValidityInSeconds() / 1000);
    }

    /**
     * 查询用户
     * @param key /
     * @return /
     */
    public JwtUserDto getOne(String key) {
        return (JwtUserDto) redisUtils.get(key);
    }

    /**
     * 退出登录
     * @param token /
     */
    public void logout(String token) {
        String key = properties.getOnlineKey() + token;
        redisUtils.del(key);
    }

}
