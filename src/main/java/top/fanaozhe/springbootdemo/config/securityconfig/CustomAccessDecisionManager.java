package top.fanaozhe.springbootdemo.config.securityconfig;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author faz
 * @create 2020-10-26-20:02
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication auth, Object object,
                       Collection<ConfigAttribute> ca) throws AccessDeniedException, InsufficientAuthenticationException {
        Collection<? extends GrantedAuthority> auths = auth.getAuthorities();
        for (ConfigAttribute configAttribute:ca){
            if("ROLE_LOGIN".equals(configAttribute.getAttribute())&& auth instanceof UsernamePasswordAuthenticationToken){
                return;
            }
            for(GrantedAuthority authority:auths){
                if(configAttribute.getAttribute().equals(authority.getAuthority())){
                    return;
                }
            }
        }
        throw  new AccessDeniedException("权限不足");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
