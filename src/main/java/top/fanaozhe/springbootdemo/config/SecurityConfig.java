package top.fanaozhe.springbootdemo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

  /*  @Bean
    PasswordEncoder PasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }*/

    @Override
    protected  void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("fanaozhe").password("$2a$10$iWyRtvl21h6P.BWL69zZH.8.v/21KGRiflnSxdVOmo3dODhh3F0AW").roles("ADMIN")
                .and()
                .withUser("zhuyandi").password("$2a$10$oGhmitlGJUC/qL5vSqQEuukZd.5j4UO/tmeS8mnMNB8CiUgfXeH8K").roles("USER");
    }

    @Override
    protected void  configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/book/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasRole("USER")
                //其他用户访问必须经过认证；
                .anyRequest()
                .authenticated()
                .and()
                //可自定义用户授权页面
                .formLogin()
                //登录请求处理接口
                .loginProcessingUrl("/login")
                //认证所需要的用户名和密码的参数名
                .usernameParameter("name")
                .passwordParameter("passwd")

                //登录成功处理逻辑
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        Authentication auth) throws IOException, ServletException {
                        Object principal = auth.getPrincipal();
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(200);
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("status",200);
                        map.put("msg",principal);
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })

                //登录失败处理逻辑
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req,
                                                        HttpServletResponse resp,
                                                        AuthenticationException e) throws IOException, ServletException {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        resp.setStatus(401);
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("status",401);
                        if(e instanceof LockedException){
                            map.put("msg","账户被锁定，登录失败！");
                        }else if(e instanceof BadCredentialsException){
                            map.put("msg","账户用户或密码输入错误，登录失败！");
                        }else if(e instanceof DisabledException){
                            map.put("msg","账户被禁用，登录失败！");
                        }else if(e instanceof AccountExpiredException){
                            map.put("msg","账户已过期，登录失败！");
                        }else if(e instanceof CredentialsExpiredException){
                            map.put("msg","密码已过期，登录失败！");
                        }else {
                            map.put("msg","登录失败！");
                        }
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })

                //开启注销登录的配置
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest req,
                                       HttpServletResponse resp,
                                       Authentication auth) {
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req,
                                                HttpServletResponse resp,
                                                Authentication auth) throws IOException, ServletException {
                        //resp.sendRedirect("/login_page");
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        HashMap<String, Object> map = new HashMap<>();
                        map.put("status",555);
                        map.put("msg","登录注销成功");
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(map));
                        out.flush();
                        out.close();

                    }
                })

                //表示和登录相关的接口都能不需要认证即可访问
                .permitAll()

                .and()
                .csrf()
                .disable();
    }
}
