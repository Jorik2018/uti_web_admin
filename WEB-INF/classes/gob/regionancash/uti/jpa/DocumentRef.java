/*    */ package WEB-INF.classes.gob.regionancash.uti.jpa;
/*    */ 
/*    */ import gob.regionancash.uti.jpa.Document;
/*    */ import java.io.Serializable;
/*    */ import javax.persistence.Basic;
/*    */ import javax.persistence.Column;
/*    */ import javax.persistence.Entity;
/*    */ import javax.persistence.GeneratedValue;
/*    */ import javax.persistence.GenerationType;
/*    */ import javax.persistence.Id;
/*    */ import javax.persistence.JoinColumn;
/*    */ import javax.persistence.ManyToOne;
/*    */ import javax.persistence.Table;
/*    */ 
/*    */ @Entity
/*    */ @Table(name = "document_ref")
/*    */ public class DocumentRef
/*    */   implements Serializable {
/*    */   private static final long serialVersionUID = 1L;
/*    */   @Id
/*    */   @Basic(optional = false)
/*    */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    */   @Column(name = "id")
/*    */   private Integer id;
/*    */   @Column(name = "document_id")
/*    */   private Integer documentId;
/*    */   @Column(name = "reference_id")
/*    */   private Integer referenceId;
/*    */   @JoinColumn(name = "reference_id", referencedColumnName = "id", insertable = false, updatable = false)
/*    */   @ManyToOne(optional = true)
/*    */   private Document document;
/*    */   
/*    */   public DocumentRef() {}
/*    */   
/*    */   public DocumentRef(Integer id) {
/* 36 */     this.id = id;
/*    */   }
/*    */   
/*    */   public Integer getId() {
/* 40 */     return this.id;
/*    */   }
/*    */   
/*    */   public void setId(Integer id) {
/* 44 */     this.id = id;
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 49 */     int hash = 0;
/* 50 */     hash += (this.id != null) ? this.id.hashCode() : 0;
/* 51 */     return hash;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 57 */     if (!(object instanceof gob.regionancash.uti.jpa.DocumentRef)) {
/* 58 */       return false;
/*    */     }
/* 60 */     gob.regionancash.uti.jpa.DocumentRef other = (gob.regionancash.uti.jpa.DocumentRef)object;
/* 61 */     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
/* 62 */       return false;
/*    */     }
/* 64 */     return true;
/*    */   }
/*    */   
/*    */   public Integer getDocumentId() {
/* 68 */     return this.documentId;
/*    */   }
/*    */   
/*    */   public void setDocumentId(Integer documentId) {
/* 72 */     this.documentId = documentId;
/*    */   }
/*    */   
/*    */   public Integer getReferenceId() {
/* 76 */     return this.referenceId;
/*    */   }
/*    */   
/*    */   public void setReferenceId(Integer referenceId) {
/* 80 */     this.referenceId = referenceId;
/*    */   }
/*    */   
/*    */   public Document getDocument() {
/* 84 */     return this.document;
/*    */   }
/*    */   
/*    */   public void setDocument(Document document) {
/* 88 */     this.document = document;
/*    */   }
/*    */ 
/*    */   
/*    */   public String toString() {
/* 93 */     return "gob.regionancash.uti.jpa.Document[ id=" + this.id + " ]";
/*    */   }
/*    */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/jpa/DocumentRef.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */