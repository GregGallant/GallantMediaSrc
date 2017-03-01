package gallantmedia.models;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.*;

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
   @Email
   @Size(min=4,max=500)
   @Column(name="email")
   private String email;

   @NotNull
   @Size(min=1,max=250)
   @Column(name="firstname")
   private String firstname;

   @NotNull
   @Size(min=1,max=250)
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


    /**
     *
     * @param cs
     * @return
     */
    public String buildContact(Set cs)
    {

        String key;
        String[] value;
        String contactReturn = null;
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
                   this.setFirstname(cval);
                   break;
               case("lastname"):
                   this.setLastname(cval);
                   break;
               case("description"):
                   this.setDescription(cval);
                   break;
               case("company"):
                   this.setWebsite(cval);
                   break;
           }
       }

       try {
            contactReturn = om.writeValueAsString(cret);
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

        return contactReturn;
   }

   public Contact buildContactReflexive(Set cs)
   {
       String key;
       String[] value;

       List<String> cret = new ArrayList<>();
       Iterator it = cs.iterator();

       Class params[] = {};
       Object paramsObj[] = {};
       Method thisMethod = null;

       while(it.hasNext()) {
           Map.Entry m = (Map.Entry)it.next();

           key = (String)m.getKey();
           value = (String[])m.getValue();

           Class thisClass = null;
           Object iClass = null;

           try {

               thisClass = Class.forName("Contact");
           } catch(ClassNotFoundException clfe) {
               clfe.printStackTrace();
           }

           String Key = key.substring(0, 1).toUpperCase() + key.substring(1);
           String setMethod = "set" + Key;

           if (thisClass != null) {
               try {
                   iClass = thisClass.newInstance();
                } catch(InstantiationException ie) {
                     ie.printStackTrace();
                } catch(IllegalAccessException iae) {
                     iae.getStackTrace();
                }


               StringBuilder sb = new StringBuilder();
               for (String evalue : value) {
                   sb.append(evalue);
               }
               String stringVal = sb.toString();


               try {
                   iClass = thisClass.newInstance();
               } catch(InstantiationException ie) {
                   ie.printStackTrace();
               } catch(IllegalAccessException iae) {
                   iae.getStackTrace();
               }

               try {
                   thisMethod = thisClass.getDeclaredMethod(Key, params);
               } catch(NoSuchMethodException nme) {
                   nme.printStackTrace();
               }
           }

           if (thisMethod != null) {
               try {
                   thisMethod.invoke(iClass, paramsObj);
               } catch(InvocationTargetException vae) {
                   vae.printStackTrace();
                } catch(IllegalAccessException iae) {
                   iae.getStackTrace();
               }
           }



       }
        return this;
   }

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
