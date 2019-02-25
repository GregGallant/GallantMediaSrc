package gallantmedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import javax.sql.DataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;

import org.springframework.context.annotation.Primary;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{


    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
            .authorizeRequests()
                .antMatchers("/","/news","/newsjson","/newslinks","/newssportsjson","/newsfashionjson","/newsentertainmentjson","/newsusjson","/newsus","/error","/resources/**","/supernews", "/newsorder").permitAll()
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
        builder.userDetailsService(new GallantUserDetailsService());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
        logger.info("Hello from password encoder");
        // TODO: Maybe switch to Argonii
        return new BCryptPasswordEncoder();
    }

}