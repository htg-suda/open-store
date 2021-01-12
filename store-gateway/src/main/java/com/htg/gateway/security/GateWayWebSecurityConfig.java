package com.htg.gateway.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.ReactiveAuthenticationManagerAdapter;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.RedirectServerLogoutSuccessHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import reactor.core.publisher.Mono;

import java.net.URI;

/**
 * @author liuhao
 * @date 2020/4/10
 */
@Slf4j
@EnableWebFluxSecurity
public class GateWayWebSecurityConfig {

    private ReactiveAuthenticationManagerAdapter authenticationManagerAdapter;

    @Autowired
    private PermissionAuthorizationManager permissionAuthorizationManager;

    @Autowired
    private AuthGlobalFilter authGlobalFilter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private String[] getIgnoreUrls() {
        String[] ignoreUrls = {
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/**/**/api-docs",
                "/auth/oauth/token"
        };
        return ignoreUrls;
    }


    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
                .pathMatchers(getIgnoreUrls()).permitAll()
                .and()
                .authorizeExchange().pathMatchers("/**").access(permissionAuthorizationManager)
                .anyExchange().authenticated()
                .and()
               // .addFilterBefore(authGlobalFilter, SecurityWebFiltersOrder.FORM_LOGIN)
        ;
        //   http.oauth2ResourceServer().jwt();
        http.csrf().disable();
        return http.build();
    }

    @Bean
    public Converter<Jwt, ? extends Mono<? extends AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix(AuthConstant.AUTHORITY_PREFIX);
        jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(AuthConstant.AUTHORITY_CLAIM_NAME);
        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return new ReactiveJwtAuthenticationConverterAdapter(jwtAuthenticationConverter);
    }


    public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
        RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
        successHandler.setLogoutSuccessUrl(URI.create(uri));
        return successHandler;
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
        //内存中缓存权限数据
        User.UserBuilder userBuilder = User.builder();
        UserDetails admin = userBuilder.username("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN").build();
        // 输出加密密码
        String encodePassword = passwordEncoder.encode("123456");
        log.info("encodePassword:" + encodePassword);
        return new MapReactiveUserDetailsService(admin);
    }

}