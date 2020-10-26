package top.fanaozhe.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import top.fanaozhe.springbootdemo.entity.Ruser;
import top.fanaozhe.springbootdemo.mapper.RuserMapper;

/**
 * @author faz
 * @create 2020-10-24-14:24
 */
@Service
public class RuserService implements UserDetailsService {
    @Autowired
    RuserMapper ruserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Ruser user = ruserMapper.findUserByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("账户不存在！");
        }
        user.setRoles(ruserMapper.getUserRolesByUid(user.getId()));
        return user;
    }
}
