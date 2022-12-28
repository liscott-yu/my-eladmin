package com.scott.rest;

import cn.hutool.core.util.IdUtil;
import com.scott.service.dto.AuthUserDto;
import com.scott.utils.RedisUtils;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * project name  my-eladmin-backend1
 * filename  AuthorizationController
 * @author liscott
 * @date 2022/12/28 16:56
 * description  身份 认证(登录）
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    /**
     * lombok 使用 @RequiredArgsConstructor 替代 @Autowired
     */
    private final RedisUtils redisUtils;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUserDto, HttpServletRequest request) throws Exception {
        // 查询 Redis 中的 验证码
        String code = (String) redisUtils.get(authUserDto.getUuid());
        // 清除redis 中的 验证码
        redisUtils.del(authUserDto.getUuid());
        if(StringUtils.isBlank(code)){
            throw new Exception("验证码为空或已过期");
        }
        if(StringUtils.isBlank(authUserDto.getCode()) || !authUserDto.getCode().equalsIgnoreCase(code)){
            throw new Exception("验证码错误");
        }
        // 已通过 验证码 验证
        return ResponseEntity.ok("验证码校验通过，但是还没有进行认证和授权");
    }

    @GetMapping(value = "/code")
    public ResponseEntity<Object> getCode() {
        // 获取 算术验证码
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        captcha.setLen(2);
        String uuid = "code-key-" + IdUtil.simpleUUID();
        String captchaValue = captcha.text();
        // 保存
        redisUtils.set(uuid, captchaValue, 2L, TimeUnit.MINUTES);
        // 验证码 信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2){{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseEntity.ok(imgResult);
    }
}
