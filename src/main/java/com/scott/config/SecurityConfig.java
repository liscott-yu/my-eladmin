package com.scott.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * project name  my-eladmin-backend1
 * filename  SecurityConfig
 *
 * @author liscott
 * @date 2022/12/28 17:57
 * description
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and().authorizeRequests()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/code").permitAll()
                .anyRequest().authenticated()
                .and().cors().configurationSource(corsConfigurationSource())
                .and().csrf().disable();
    }

    /**
     * 由于非简单请求都要首先发送一个预检请求(request),而预检请求并不会携带认证信息，所以预检请求就有被Spring Security拦截的可能。
     * 因此通过@CrossOrigin注解或者重写addCorsMappings方法配置跨域就会失效。
     * 如果使用CorsFilter配置的跨域，只要过滤器优先级高于SpringSecurity过滤器就不会有问题，反之同样会出现问题。
     */
    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
