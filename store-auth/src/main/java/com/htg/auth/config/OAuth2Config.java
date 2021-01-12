package com.htg.auth.config;

import com.htg.auth.service.SimpleUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 认证服务器逻辑实现
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2Config extends AuthorizationServerConfigurerAdapter {

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthorizationServerTokenServices tokenServices;

    @Autowired
    private SimpleUserDetailService userDetailService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

        // http://localhost:6001/oauth/authorize?client_id=client01&response_type=code

        clients.inMemory()  // 内存中分配
                .withClient("client01") //客户端01
                //客户端获取token 时的密钥
                .secret(passwordEncoder.encode("8htgrfex467hg6fuehfhrefhupnu44t4nn"))
                // 允许该客户的授权的类型
                .authorizedGrantTypes("refresh_token", "password")
                .autoApprove(false)
                .scopes("all") // 允许授权的范围
                .and()  //配置第二个用户
                .withClient("client02") // 客户端02
                // 客户端02 获取token 时的密钥
                .secret(passwordEncoder.encode("o9834uiuneruigu09u09u9uhuirefhrbhu"))
                .authorizedGrantTypes("refresh_token", "authorization_code", "password")
                .autoApprove(true)
                .scopes("all");
    }


    @Bean
    public AuthorizationServerTokenServices tokenServices() {
        DefaultTokenServices service = new DefaultTokenServices();
        service.setReuseRefreshToken(true); // 是否重用 reflash_token
        service.setTokenStore(redisTokenStore());  // token 存储策略
        // 设置 token 增强器
        // service.setTokenEnhancer();
        service.setAccessTokenValiditySeconds(7200); // token 有效时间，这里为2个小时
        service.setRefreshTokenValiditySeconds(24 * 3600 * 3); // 设置刷新令牌的过期时间，一般 刷新token 的过期时间要远大于token 的时间
        return service;
    }


    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)  // 密码认证方式时必须认证管理器
                .userDetailsService(userDetailService) // 配置用户服务,在密码认证方式时必须配置
                .tokenServices(tokenServices)
                .allowedTokenEndpointRequestMethods(HttpMethod.POST); // token 的请求方式

    }

    /*
        /oauth/authorize : 授权点，负责认证
        /oauth/token : 令牌点,负责生成令牌
        /oauth/confirm_access : 用户确认授权时访问端点,就是给你一个确认页面，看你是否点确认授权
        /oauth/error : 获取错误信息的
        /oauth/check_token :  校验令牌的
        /oauth/token_key : 提供公有密钥，JWT获取公钥时有用到
    */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients() // 允许进行表单认证
                .checkTokenAccess("isAuthenticated()") /* 访问 /oauth/check_token 需要认证 */
                .tokenKeyAccess("permitAll()");      /* 访问 /oauth/token_key 提供公共密钥 对所有人开放*/
    }


/*
    @Bean
    public TokenStore tokenStore() {
        // token 存储在内存中
        return new InMemoryTokenStore();
    }
*/


    private String STORE_FREFIX="htg_";

    @Bean
    public TokenStore redisTokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix(STORE_FREFIX);
        return tokenStore;
    }

}