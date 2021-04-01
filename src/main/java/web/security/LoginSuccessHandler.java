package web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {



        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if(roles.contains("ROLE_ADMIN")) {
            httpServletResponse.sendRedirect("/admin");
        }
        else if ((roles.contains("ROLE_USER"))) {
            httpServletResponse.sendRedirect("/hello");
        }
        else {
            httpServletResponse.sendRedirect("/");
        }
    }

//        User user = (User) authentication.getPrincipal();
//
//        List<String> roleList = new ArrayList<>();
//        for(Role role:user.getRoles()){
//            roleList.add(role.getName());
//        }
//        if(roleList.contains("ROLE_ADMIN")){
//            httpServletResponse.sendRedirect("/admin");
//        }
//        else if (roleList.contains("ROLE_USER")){
//            httpServletResponse.sendRedirect("/hello");
//        }
//        else
//            httpServletResponse.sendRedirect("/");
//    }


}
