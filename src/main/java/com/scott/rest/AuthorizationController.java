package com.scott.rest;

import cn.hutool.core.util.IdUtil;
import com.scott.utils.RedisUtils;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
