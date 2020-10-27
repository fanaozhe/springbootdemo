package top.fanaozhe.springbootdemo.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import top.fanaozhe.springbootdemo.service.RuserService;

/**
 * @author faz
 * @create 2020-10-24-14:32
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    RuserService ruserService;

  /*  @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    PasswordEncoder PasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //设置角色的继承关系
    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy ="ROLE_dba > ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Override
    protected void  configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(ruserService);
    }

    protected void  configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(cfisms());
                        object.setAccessDecisionManager(cadm());
                        return object;
                    }
                })
                //登录页面
                .and()
                .formLogin()
                .loginProcessingUrl("/login").permitAll()

                .and()
                .csrf().disable();
    }

    @Bean
    CustomFilterInvocationSecurityMetadataSource cfisms(){
        return new CustomFilterInvocationSecurityMetadataSource();
    }
    CustomAccessDecisionManager cadm(){
        return  new CustomAccessDecisionManager();
    }

    /*//基于内存的配置；
    protected void  configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/book/**").hasRole("admin")
                .antMatchers("/student/**").hasRole("dba")
                .antMatchers("/user/**").hasRole("user")
                //其他用户访问必须经过认证；
                .anyRequest()
                .authenticated()
                //登录页面
                .and()
                .formLogin()
                .loginProcessingUrl("/login").permitAll()

                .and()
                .csrf().disable();
    }*/

}
