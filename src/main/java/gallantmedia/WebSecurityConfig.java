package gallantmedia;

import gallantmedia.services.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private CustomerRepository userRepository;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new GallantUserDetailsService(userRepository);
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web
                .ignoring()
                .antMatchers("/","/contact", "/register","/news","/newsjson","/newslinks","/newssportsjson","/newsfashionjson","/newsentertainmentjson","/newsusjson","/newsus","/error","/resources/**","/supernews", "/newsorder")
                .antMatchers("/css/**","/fonts/**","/bootstrap/**","/images/**","/js/**")
        ;

        //http.exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests()
                .antMatchers("/","/contact","/register","/news","/newsjson","/newslinks","/newssportsjson","/newsfashionjson","/newsentertainmentjson","/newsusjson","/newsus","/error","/resources/**","/supernews", "/newsorder").permitAll()
                .antMatchers("/css/**","/fonts/**","/bootstrap/**","/images/**","/js/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/loginsuccess")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .permitAll()

        ;
        http.headers().httpStrictTransportSecurity();
        //http.exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception
    {
        Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
        logger.info("==> WebSecurityConfig start -- parameter CustomerRepository userRepository: " + this.userRepository);

//        builder.inMemoryAuthentication()
 //               .withUser("username").password("password").roles("USER");
        builder.userDetailsService(new GallantUserDetailsService(this.userRepository));
    }
/*
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) authentication;
        String username = String.valueOf(auth.getPrincipal());
        String password = String.valueOf(auth.getCredentials());

        logger.info("username:" + username);
        logger.info("password:" + password);

        return null;  //what do i return?
    }
 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
        logger.info("Hello from password encoder");
        // TODO: Maybe switch to Argonii
        return new BCryptPasswordEncoder();
    }

}