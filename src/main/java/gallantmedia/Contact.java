package gallantmedia;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="contact")
public class Contact implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name="id")
   @GeneratedValue(strategy= GenerationType.AUTO)
   private int id;

   @NotNull
   @Column(name="email")
   private String email;

   @NotNull
   @Column(name="firstname")
   private String firstname;

   @NotNull
   @Column(name="lastname")
   private String lastname;

   @Column(name="website")
   private String website;

   @NotNull
   @Size(min=4,max=2500)
   @Column(name="description", columnDefinition="Text")
   private String description;

   @Column(name="created")
   private Date created;

   @Column(name="updated")
   private Date updated;

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
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

   public void setUpdated(Date updated) { this.updated = updated; }

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
       this.email = email;
   }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
}
