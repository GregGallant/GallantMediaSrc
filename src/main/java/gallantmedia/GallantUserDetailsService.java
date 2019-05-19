package gallantmedia;

import gallantmedia.models.Customer;
import gallantmedia.services.customer.CustomerRepository;
import gallantmedia.services.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class GallantUserDetailsService implements UserDetailsService
{
    private static List<UserObject> users = new ArrayList<>();

    private CustomerRepository customerRepository;

    @Autowired
    public GallantUserDetailsService(CustomerRepository customerRepository)
    {
        Logger logger = LoggerFactory.getLogger(GallantUserDetailsService.class);
        logger.info("==> UserDetailsService start -- parameter CustomerRepository userRepository: " + customerRepository);
        this.customerRepository = customerRepository;

        String pw_hash = BCrypt.hashpw("testing", BCrypt.gensalt());

        users.add(new UserObject("ggallant", pw_hash, "ADMIN"));
        users.add(new UserObject("greg", pw_hash, "ADMIN"));
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Logger logger = LoggerFactory.getLogger(GallantUserDetailsService.class);
        logger.info("====D LoadUserByUsername with new customerRepo happening...: " + customerRepository);
        try {
            final Customer customer = customerRepository.findUserByEmail(username);
            if (customer != null) {

                //PasswordEncoder encoder = new BCryptPasswordEncoder();
                //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
                //String password = encoder.encode(customer.getPassword());
                //String password = encoder.encode(customer.getPassword());
                String password = customer.getPassword();
                logger.info("8=======D~  Customer.getEmail: " + customer.getEmail());
                return User.withUsername(customer.getEmail()).accountLocked(!customer.isEnabled()).password(password).roles(customer.getRole()).build();
            }
        } catch(Exception ex) {
            logger.error("8====D LoadUserByUsername ERROR...");
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