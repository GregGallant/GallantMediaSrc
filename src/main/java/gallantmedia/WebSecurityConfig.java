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
                .antMatchers("/","/news","/newsjson","/newslinks","/newsusjson","/newsus","/error","/resources/**","/supernews", "/newsorder").permitAll()
                .antMatchers("/css/**","/bootstrap/**","/images/**","/js/**","/fonts/**","/icons/**", "/chat").permitAll()
                .antMatchers("/newsentertainmentjson","/newsbusinessjson","/newsbuild").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/loginsuccess")
                //.loginProcessingUrl("/loginprocess")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
            .logout()
                .permitAll();

        http.exceptionHandling().accessDeniedPage("/403");
    }
/*
    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
*/
/*
    @Bean
    @ConfigurationProperties("application.properties")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
*/
/*
    @Bean
    @Primary
    @ConfigurationProperties("application.properties")
    public DataSourceProperties dataSourceProperties() {
        return new DataSourceProperties();
    }
*/
/*
    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception
    {
        DataSource dataSource = dataSource();
        Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

        logger.info("configAuthentication: " + auth.toString());

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select user,password FROM user where username=?")
                .authoritiesByUsernameQuery("select user,role FROM user WHERE username=?");
    }
*/
    @Bean
    @Override
    public UserDetailsService userDetailsService()
    {
        Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

        UserDetails user = User.withDefaultPasswordEncoder().username("ggallant")
                                         .password("test").roles("USER").build();

        logger.info("UserDetailsService called UserDetails user: " + user.toString());

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // TODO: Maybe switch to Argonii
        return new BCryptPasswordEncoder();
    }
}