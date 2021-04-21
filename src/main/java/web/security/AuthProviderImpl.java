package web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import web.model.Role;
import web.model.User;
import web.service.UserService;
import java.util.ArrayList;
import java.util.List;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    @Autowired
    UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        User user = userService.getUserByName(name);
        if(name == null) {
            throw new UsernameNotFoundException("Not such user");
        }

        String password = authentication.getCredentials().toString();
        if (password == null || !password.equals(user.getPassword())) {
                throw new BadCredentialsException("Badly, try again");
            }

        List<GrantedAuthority> authorityList = new ArrayList<>();
        for (Role role: user.getRoles()) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new UsernamePasswordAuthenticationToken(user, user.getAuthorities(),  authorityList);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
