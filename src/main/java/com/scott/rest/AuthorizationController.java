package com.scott.rest;

import cn.hutool.core.util.IdUtil;
import com.scott.config.RsaProperties;
import com.scott.config.SecurityProperties;
import com.scott.config.jwt.TokenProvider;
import com.scott.service.dto.AuthUserDto;
import com.scott.service.dto.JwtUserDto;
import com.scott.service.impl.OnlineUserService;
import com.scott.utils.RedisUtils;
import com.scott.utils.RsaUtils;
import com.scott.utils.SecurityUtils;
import com.wf.captcha.ArithmeticCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@Api(tags = "系统：系统授权接口")
public class AuthorizationController {
    /**
     * lombok 使用 @RequiredArgsConstructor 替代 @Autowired
     */
    private final RedisUtils redisUtils;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final SecurityProperties properties;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUserDto, HttpServletRequest request) throws Exception {
        // 解密 密码
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUserDto.getPassword());
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
        //认证授权
        //1、根据前端传来的用户名和密码构造一个UsernamePasswordAuthenticationToken实例
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(authUserDto.getUsername(), password);
        //2、认证UsernamePasswordAuthenticationToken实例，认证成功则返回包含用户信息的Authentication实例
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        //3、设置当前登录用户，这一步是为了可以让其他类或方法通过SecurityContextHolder.getContext().getAuthentication()拿到当前登录的用户
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //4、通过已经认证的Authentication返回UserDetails
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();

        // 封装结果
        Map<String, Object> authInfo = new HashMap<String, Object>(2){{
            put("token", "token");
            put("user", jwtUserDto);
        }};
        return ResponseEntity.ok(authInfo);
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo() {
        return ResponseEntity.ok(SecurityUtils.getCurrentUser());
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

    @ApiOperation("退出登录")
    @DeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        onlineUserService.logout(tokenProvider.getToken(request));
        redisUtils.del();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
