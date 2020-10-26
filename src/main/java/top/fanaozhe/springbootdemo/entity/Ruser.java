package top.fanaozhe.springbootdemo.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author faz
 * @create 2020-10-24-13:51
 */
@Data
public class Ruser implements UserDetails {
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private Boolean locked;
    private List<Role> roles;

    /*获取当前用户对象所拥有的角色信息*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    /*获取当前对象的密码*/
    @Override
    public String getPassword() {
        return password;
    }

    /*获取当前对象的用户*/
    @Override
    public String getUsername() {
        return username;
    }

    /*当前账号是否未过期*/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*当前账号是否未锁定*/
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }
    /*当前账号密码是否未过期*/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /*当前账号是否可用*/
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
