package gallantmedia;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class GallantUserDetailsService implements UserDetailsService
{

    private static List<UserObject> users = new ArrayList<>();

    public GallantUserDetailsService()
    {
        String pw_hash = BCrypt.hashpw("testing", BCrypt.gensalt());

        users.add(new UserObject("ggallant", pw_hash, "ADMIN"));
        users.add(new UserObject("greg", pw_hash, "ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
        logger.info("====D~  LoadUserByUsername");

        Optional<UserObject> user = users.stream()
                .filter(u -> u.name.equals(username))
                .findAny();
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }

        UserObject userObject = user.get();

        return User.withUsername(userObject.name)
                .password(userObject.password)
                .roles(userObject.role).build();

    }

    private static class UserObject {
        private String name;
        private String password;
        private String role;

        public UserObject(String name, String password, String role) {
            this.name = name;
            this.password = password;
            this.role = role;
        }
    }

}