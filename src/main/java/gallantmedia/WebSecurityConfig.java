package gallantmedia;

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
                .antMatchers("/newsentertainmentjson","/newsbusinessjson").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/loginsuccess")
                .loginProcessingUrl("/loginprocess")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
            .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService()
    {
        UserDetails user = User.withDefaultPasswordEncoder().username("ggallant")
                                         .password("test").roles("USER").build();

        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // TODO: Maybe switch to Argonii
        return new BCryptPasswordEncoder();
    }
}