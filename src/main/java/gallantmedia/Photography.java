package gallantmedia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="photography")
public class Photography
{

   @Id
   @Column(name="id")
   private int id;

   @Column(name="filename")
   private String filename;

   @Column(name="category")
   private int category;

   @Column(name="subcat")
   private String subcat;

   @Column(name="title")
   private String title;

   @Column(name="description")
   private String description;

   @Column(name="status")
   private int status;

   @Column(name="year")
   private int year;

   @Column(name="created_at")
   private String created_at;

   @Column(name="updated_at")
   private String updated_at;


   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getFilename() {
      return filename;
   }

   public void setFilename(String filename) {
      this.filename = filename;
   }

   public int getCategory() {
      return category;
   }

   public void setCategory(int category) {
      this.category = category;
   }

   public String getSubcat() {
      return subcat;
   }

   public void setSubcat(String subcat) {
      this.subcat = subcat;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getDescription() {
      return description;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public int getStatus() {
      return status;
   }

   public void setStatus(int status) {
      this.status = status;
   }

   public int getYear() {
      return year;
   }

   public void setYear(int year) {
      this.year = year;
   }

   public String getCreated_at() {
      return created_at;
   }

   public void setCreated_at(String created_at) {
      this.created_at = created_at;
   }

   public String getUpdated_at() {
      return updated_at;
   }

   public void setUpdated_at(String updated_at) {
      this.updated_at = updated_at;
   }
}
