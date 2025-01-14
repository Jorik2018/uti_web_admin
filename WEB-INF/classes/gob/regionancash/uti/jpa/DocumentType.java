/*    */ package WEB-INF.classes.gob.regionancash.uti.jpa;
/*    */ 
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.Table;
/*    */ import javax.validation.constraints.Size;
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "document_type")
/*    */ public class DocumentType
/*    */   implements Serializable
/*    */ {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Basic(optional = false)
/*    */   @Column(name = "id")
/*    */   private Integer id;
/*    */   @Size(max = 5)
/*    */   @Column(name = "code")
/*    */   private String code;
/*    */   @Size(max = 45)
/*    */   @Column(name = "name")
/*    */   private String name;
/*    */   
/*    */   public DocumentType() {}
/*    */   
/*    */   public DocumentType(Integer id) {
/* 34 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Integer getId() {
/* 38 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 42 */     this.id = id;
/*    */   }
/*    */   
/*    */   public String getCode() {
/* 46 */     return this.code;
/*    */   }
/*    */   
/*    */   public void setCode(String code) {
/* 50 */     this.code = code;
/*    */   }
/*    */   
/*    */   public String getName() {
/* 54 */     return this.name;
/*    */   }
/*    */   
/*    */   public void setName(String name) {
/* 58 */     this.name = name;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 63 */     int hash = 0;
/* 64 */     hash += (this.id != null) ? this.id.hashCode() : 0;
/* 65 */     return hash;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 71 */     if (!(object instanceof gob.regionancash.uti.jpa.DocumentType)) {
/* 72 */       return false;
/*    */     }
/* 74 */     gob.regionancash.uti.jpa.DocumentType other = (gob.regionancash.uti.jpa.DocumentType)object;
/* 75 */     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
/* 76 */       return false;
/*    */     }
/* 78 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 83 */     return "gob.regionancash.uti.jpa.DocumentType[ id=" + this.id + " ]";
/*    */   }
/*    */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/jpa/DocumentType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */