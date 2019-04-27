package gallantmedia;

import gallantmedia.models.Customer;
import gallantmedia.services.customer.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class GallantUserDetailsService implements UserDetailsService
{

    private static List<UserObject> users = new ArrayList<>();

    private CustomerRepository customerRepository;

    /**
     * TODO: Dbase
     */
    public GallantUserDetailsService(CustomerRepository customerRepository)
    {
        this.customerRepository = customerRepository;

        String pw_hash = BCrypt.hashpw("testing", BCrypt.gensalt());

        users.add(new UserObject("ggallant", pw_hash, "ADMIN"));
        users.add(new UserObject("greg", pw_hash, "ADMIN"));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
        logger.info("====D LoadUserByUsername with new customerRepo happening...");

        try {
            final Customer customer = this.customerRepository.findUserByEmail(username);

            if (customer != null) {
                PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                String password = encoder.encode(customer.getPassword());
                return User.withUsername(customer.getEmail()).accountLocked(!customer.isEnabled()).password(password).roles(customer.getRole()).build();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }

        throw new UsernameNotFoundException(username);
        /*
        Optional<UserObject> user = customer.stream()
                .filter(u -> u.name.equals(username))
                .findAny();
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("Customer not found by name: " + username);
        }

        UserObject userObject = user.get();

        return Customer.withUsername(userObject.name)
                .password(userObject.password)
                .roles(userObject.role).build();
        */
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