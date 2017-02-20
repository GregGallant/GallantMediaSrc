package gallantmedia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="contact")
public class Contact implements Serializable
{
   private static final long serialVersionUID = 1L;

   @Id
   @Column(name="id")
   private int id;

   @Column(name="email")
   private String email;

   @Column(name="firstname")
   private String filename;

   @Column(name="lastname")
   private String lastname;

   @Column(name="website")
   private String website;

   @Column(name="description", columnDefinition="Text")
   private String description;

   @Column(name="created_at")
   private Date created_at;

   @Column(name="updated_at")
   private Date updated_at;

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

   public Date getCreated_at() {
      return created_at;
   }

   public void setCreated_at(Date created_at) {
      this.created_at = created_at;
   }

   public Date getUpdated_at() {
      return updated_at;
   }

   public void setUpdated_at(Date updated_at) { this.updated_at = updated_at; }

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
       this.email = email;
   }

   public String getFilename() {
       return filename;
   }

    public void setFilename(String filename) {
        this.filename = filename;
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
}
