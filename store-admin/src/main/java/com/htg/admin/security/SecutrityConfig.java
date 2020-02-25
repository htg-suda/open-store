package com.htg.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@EnableWebSecurity
public class SecutrityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthProvider customAuthProvider;

    /*
   * 注意AccessDeniedHandler与AuthenticationEntryPoint的区别:
       AccessDeniedHandler: 已授权的用户请求权限之外的资源时会交给这个类处理.
       AuthenticationEntryPoint: 未授权的用户请求非公共资源时会交给这个类处理.
   * */
    @Autowired
    protected MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    protected MyAuthenticationEntryPoint myAuthenticationEntryPoint;

    @Autowired
    private AuthFilter authFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  跨域 请求的理解
        // https://www.jianshu.com/p/89a377c52b48
        // https://www.jianshu.com/p/b55086cbd9af
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = http.authorizeRequests();
        registry.requestMatchers(CorsUtils::isPreFlightRequest).permitAll();   //让Spring security放行所有preflight request

        // super.configure(http);
        http.csrf().disable();
        http.cors();
        http.authorizeRequests()
                /* 允许登陆接口 */
                .antMatchers("/admin/user/login").permitAll()
                /* 允许添加管理员 */
                .antMatchers("/admin/user/login").permitAll()

                /* swagger start */
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                /* 对管理员开放接口 */
                .antMatchers("/*").hasRole("ADMIN")
                /* swagger end*/
                .anyRequest().authenticated() // 其他任何接口都需要认证
                .and()
                .exceptionHandling().authenticationEntryPoint(myAuthenticationEntryPoint).accessDeniedHandler(myAccessDeniedHandler)
                .and().addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class);
        /* 禁用缓存 */
        // http.headers().cacheControl();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //加入自定义的安全认证
        auth.authenticationProvider(customAuthProvider);
    }

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder getPassword() {
        return new BCryptPasswordEncoder();
    }
}
