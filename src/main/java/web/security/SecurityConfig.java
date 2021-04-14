package web.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@ComponentScan("web.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthProviderImpl authProvider;


//    private final UserDetailsService userDetailsService;


    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

//    @Autowired
//    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsServiceImpl userDetailsService, LoginSuccessHandler loginSuccessHandler) {
//        this.userDetailsService = userDetailsService;
//        this.loginSuccessHandler = loginSuccessHandler;
//    }


    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
          authenticationManagerBuilder.authenticationProvider(authProvider);
    //    authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

                http
                .formLogin()
                 //.loginPage("/login")
                .successHandler(loginSuccessHandler)
                .loginProcessingUrl("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .permitAll();

        http
                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/admin", "/admin/**")
                .access("hasRole('ROLE_ADMIN')")
                .antMatchers("/hello")
                .access("hasRole('ROLE_USER')");
//                .antMatchers("/hello")
//                .access("hasRole('ROLE_ADMIN')");


//        http
//                .formLogin()
//                .successHandler(new LoginSuccessHandler())
//                .loginProcessingUrl("/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .and().csrf().disable();


//        http.logout()
//                .permitAll()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("login?logout")
//                .and().csrf().disable();

//        http
//                .authorizeRequests()
//                .antMatchers("/login").anonymous()
////                .antMatchers("/admin/**").hasRole("ROLE_ADMIN")
//                .antMatchers("hello").hasAnyRole("ROLE_USER", "ROLE_ADMIN");


        
////

//        http
//                .authorizeRequests()
//                .antMatchers("/admin")
//                .access("hasAuthority('ROLE_ADMIN')")
//                .antMatchers("/hello")
//                .access("hasAuthority('ROLE_USER')");
    }
}


