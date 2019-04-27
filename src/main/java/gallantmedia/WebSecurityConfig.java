package gallantmedia;

import gallantmedia.services.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private CustomerRepository userRepository;

    public WebSecurityConfig(CustomerRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests()
                .antMatchers("/","/register","/news","/newsjson","/newslinks","/newssportsjson","/newsfashionjson","/newsentertainmentjson","/newsusjson","/newsus","/error","/resources/**","/supernews", "/newsorder").permitAll()
                .antMatchers("/css/**","/fonts/**","/bootstrap/**","/images/**","/js/**").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                 //.loginProcessingUrl("/loginprocess")
                .loginPage("/login")
                .defaultSuccessUrl("/loginsuccess")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
            .logout()
                .permitAll();

        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception
    {
        builder.userDetailsService(new GallantUserDetailsService(this.userRepository));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
        logger.info("Hello from password encoder");
        // TODO: Maybe switch to Argonii
        return new BCryptPasswordEncoder();
    }

}