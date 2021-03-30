//package web.security;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//import web.dao.UserDao;
//import web.model.User;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class AuthProviderImpl implements AuthenticationProvider {
//
//    @Autowired
//    private UserDao userDao;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//       String userName = authentication.getName();
//       User user = userDao.getUserByName(userName);
//       if(user == null) {
//           throw new UsernameNotFoundException("user not found");
//       }
//       String password = authentication.getCredentials().toString();
//       if (!password.equals(user.getPassword())){
//           throw new BadCredentialsException("bad credentials");
//        }
//
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        return new UsernamePasswordAuthenticationToken(user, null, authorities);
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return authentication.equals(UsernamePasswordAuthenticationToken.class);
//    }
//}
