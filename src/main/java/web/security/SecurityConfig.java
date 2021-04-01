package web.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@ComponentScan("web.security")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetails;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(UserDetailsService userDetails, LoginSuccessHandler loginSuccessHandler) {
        this.userDetails = userDetails;
        this.loginSuccessHandler = loginSuccessHandler;
    }


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder.userDetailsService(userDetails).passwordEncoder(bCryptPasswordEncoder());
    }

    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests().antMatchers("/login").anonymous()
//                .antMatchers("/admin").authenticated()
//                .and().csrf().disable()
//                .formLogin().loginPage("login")
//                .loginProcessingUrl("/login/process")
//                .usernameParameter("username")
//                .and().logout();
        ///

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
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and().csrf().disable();

        http
                .authorizeRequests()
                .antMatchers("/login").anonymous()
                .antMatchers("/admin", "/admin/**")
                .access("hasAuthority('ROLE_ADMIN')")
                .antMatchers("/hello")
                .access("hasAuthority('ROLE_USER')");
  }
}
