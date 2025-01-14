/*    */ package WEB-INF.classes.gob.regionancash.uti.jpa;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ import javax.validation.constraints.Size;
/*    */ import javax.xml.bind.annotation.XmlRootElement;
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "document_category")
/*    */ @XmlRootElement
/*    */ public class DocumentCategory
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   private Integer id;
/*    */   @Size(max = 100)
/*    */   private String name;
/*    */   
/*    */   public Integer getId() {
/* 27 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 31 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 35 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 39 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 44 */     int hash = 0;
/* 45 */     hash += (this.id != null) ? this.id.hashCode() : 0;
/* 46 */     return hash;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 52 */     if (!(object instanceof gob.regionancash.uti.jpa.DocumentCategory)) {
/* 53 */       return false;
/*    */     }
/* 55 */     gob.regionancash.uti.jpa.DocumentCategory other = (gob.regionancash.uti.jpa.DocumentCategory)object;
/* 56 */     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
/* 57 */       return false;
/*    */     }
/* 59 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 64 */     return "gob.regionancash.uti.jpa.Document[ id=" + this.id + " ]";
/*    */   }
/*    */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/jpa/DocumentCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */