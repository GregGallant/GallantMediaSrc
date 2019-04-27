package gallantmedia.models;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.sql.Date;
import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Entity
@Table(name="user")
public class Customer implements Serializable
{

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="email")
    @Email
    private String email;

    @Column(name="password")
    private String password;

    private String confirmpassword;

    @Column(name="role")
    private String role;

    @Column(name="status")
    private int status;

    @Column(name="created")
    private Date created;

    @Column(name="updated")
    private Date updated;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    /* Special method for loadUserByUsername */
    public boolean isEnabled() {
        if (this.getStatus() == 0) {
            return false;
        }

        return true;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    // TODO: Code structring, registration completion
    // TODO: encrypted password tests
    // TODO: Role configuration
    public String buildCustomer(Set cs)
    {
        String key;
        String[] value;
        String customerReturn = null;
        ObjectMapper om = new ObjectMapper();

        List<String> cret = new ArrayList<>();
        Iterator it = cs.iterator();

        while(it.hasNext()) {
            Map.Entry m = (Map.Entry) it.next();

            key = (String) m.getKey();
            value = (String[]) m.getValue();

            StringBuilder sb = new StringBuilder();
            for (String evalue : value) {
                sb.append(evalue);
            }
            String cval = sb.toString();
            cret.add(cval);

            switch(key) {
                case("email"):
                    this.setEmail(cval);
                    break;
                case("firstname"):
                    this.setFirstName(cval);
                    break;
                case("lastname"):
                    this.setLastName(cval);
                    break;
                case("password"):
                    this.setPassword(cval);
                    break;
            }
        }

        // Password matching
        if (!confirmPassword(this.getPassword(), this.getConfirmpassword()))
        {
            return "Passwords don't match error here...";
        }

        try {
            customerReturn = om.writeValueAsString(cret);
        } catch (JsonGenerationException e) {
            System.out.println("jge01");
            e.printStackTrace();
        } catch (JsonMappingException e) {
            System.out.println("jge02");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("jge03");
            e.printStackTrace();
        }

        return customerReturn;
    }

    private boolean confirmPassword(String password, String confirmPassword)
    {
         return password.equals(confirmPassword);
    }

}
