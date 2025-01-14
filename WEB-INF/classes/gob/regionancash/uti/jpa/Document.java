/*     */ package WEB-INF.classes.gob.regionancash.uti.jpa;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ import javax.validation.constraints.Size;
/*     */ import javax.xml.bind.annotation.XmlRootElement;
/*     */ import org.isobit.app.jpa.DruFile;
/*     */ import org.isobit.directory.jpa.Dependency;
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "document")
/*     */ @XmlRootElement
/*     */ public class Document
/*     */   implements Serializable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @Basic(optional = false)
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   private Integer id;
/*     */   @Column(name = "dependency_id")
/*     */   private Integer dependencyId;
/*     */   @Transient
/*     */   private Dependency dependency;
/*     */   @Size(max = 100)
/*     */   private String name;
/*     */   private String description;
/*     */   @JoinColumn(name = "fid", referencedColumnName = "fid", insertable = false, updatable = false)
/*     */   @ManyToOne(optional = true)
/*     */   private DruFile file;
/*     */   private Integer year;
/*     */   private Integer uid;
/*     */   @Column(name = "category_id")
/*     */   private Integer categoryId;
/*     */   @Transient
/*     */   private String category;
/*     */   @Transient
/*     */   private Object ext;
/*  51 */   private String path = "_";
/*     */   @Column(name = "created_people")
/*     */   private Integer createdBy;
/*     */   @Column(name = "created_date")
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date createdDate;
/*     */   @Column(name = "updated_people")
/*     */   private Integer updatedBy;
/*     */   @Column(name = "updated_date")
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date updatedDate;
/*     */   private Character status;
/*     */   @Size(max = 4)
/*     */   @Column(name = "document_type_id")
/*     */   private String documentTypeId;
/*     */   @Transient
/*     */   private String documentTypeName;
/*     */   @Size(max = 4)
/*     */   private String extension;
/*     */   private String code;
/*     */   @Transient
/*     */   private List references;
/*     */   @Basic
/*     */   private boolean canceled;
/*     */   @Basic
/*     */   private boolean main = false;
/*     */   
/*     */   public boolean isMain() {
/*  79 */     return this.main;
/*     */   }
/*     */   
/*     */   public void setMain(boolean main) {
/*  83 */     this.main = main;
/*     */   }
/*     */   
/*     */   public String getDocumentTypeName() {
/*  87 */     return this.documentTypeName;
/*     */   }
/*     */   
/*     */   public void setDocumentTypeName(String documentTypeName) {
/*  91 */     this.documentTypeName = documentTypeName;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Dependency getDependency() {
/*  97 */     return this.dependency;
/*     */   }
/*     */   
/*     */   public void setDependency(Dependency dependency) {
/* 101 */     this.dependency = dependency;
/*     */   }
/*     */   
/*     */   public Integer getUpdatedBy() {
/* 105 */     return this.updatedBy;
/*     */   }
/*     */   
/*     */   public void setUpdatedBy(Integer updatedBy) {
/* 109 */     this.updatedBy = updatedBy;
/*     */   }
/*     */   
/*     */   public Date getUpdatedDate() {
/* 113 */     return this.updatedDate;
/*     */   }
/*     */   
/*     */   public void setUpdatedDate(Date updatedDate) {
/* 117 */     this.updatedDate = updatedDate;
/*     */   }
/*     */   
/*     */   public List getReferences() {
/* 121 */     return this.references;
/*     */   }
/*     */   
/*     */   public void setReferences(List references) {
/* 125 */     this.references = references;
/*     */   }
/*     */   
/*     */   public String getCode() {
/* 129 */     return this.code;
/*     */   }
/*     */   
/*     */   public void setCode(String code) {
/* 133 */     this.code = code;
/*     */   }
/*     */   
/*     */   public Integer getUid() {
/* 137 */     return this.uid;
/*     */   }
/*     */   
/*     */   public void setUid(Integer uid) {
/* 141 */     this.uid = uid;
/*     */   }
/*     */   
/*     */   public Integer getCategoryId() {
/* 145 */     return this.categoryId;
/*     */   }
/*     */   
/*     */   public void setCategoryId(Integer categoryId) {
/* 149 */     this.categoryId = categoryId;
/*     */   }
/*     */   
/*     */   public String getCategory() {
/* 153 */     return this.category;
/*     */   }
/*     */   
/*     */   public void setCategory(String category) {
/* 157 */     this.category = category;
/*     */   }
/*     */   
/*     */   public Integer getCreatedBy() {
/* 161 */     return this.createdBy;
/*     */   }
/*     */   
/*     */   public void setCreatedBy(Integer createdBy) {
/* 165 */     this.createdBy = createdBy;
/*     */   }
/*     */   
/*     */   public boolean isCanceled() {
/* 169 */     return this.canceled;
/*     */   }
/*     */   
/*     */   public void setCanceled(boolean canceled) {
/* 173 */     this.canceled = canceled;
/*     */   }
/*     */   
/*     */   public Date getCreatedDate() {
/* 177 */     return this.createdDate;
/*     */   }
/*     */   
/*     */   public void setCreatedDate(Date createdDate) {
/* 181 */     this.createdDate = createdDate;
/*     */   }
/*     */   
/*     */   public Integer getDependencyId() {
/* 185 */     return this.dependencyId;
/*     */   }
/*     */   
/*     */   public void setDependencyId(Integer dependencyId) {
/* 189 */     this.dependencyId = dependencyId;
/*     */   }
/*     */   
/*     */   public Object getExt() {
/* 193 */     return this.ext;
/*     */   }
/*     */   
/*     */   public void setExt(Object ext) {
/* 197 */     this.ext = ext;
/*     */   }
/*     */ 
/*     */   
/*     */   public Document() {}
/*     */   
/*     */   public Document(Integer id) {
/* 204 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getExtension() {
/* 208 */     return this.extension;
/*     */   }
/*     */   
/*     */   public String getPath() {
/* 212 */     return this.path;
/*     */   }
/*     */   
/*     */   public void setPath(String path) {
/* 216 */     this.path = path;
/*     */   }
/*     */   
/*     */   public void setExtension(String extension) {
/* 220 */     this.extension = extension;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 224 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 228 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 232 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 236 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 240 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/* 244 */     this.description = description;
/*     */   }
/*     */   
/*     */   public DruFile getFile() {
/* 248 */     return this.file;
/*     */   }
/*     */   
/*     */   public void setFile(DruFile file) {
/* 252 */     this.file = file;
/*     */   }
/*     */   
/*     */   public Integer getYear() {
/* 256 */     return this.year;
/*     */   }
/*     */   
/*     */   public void setYear(Integer year) {
/* 260 */     this.year = year;
/*     */   }
/*     */   
/*     */   public String getDocumentTypeId() {
/* 264 */     return this.documentTypeId;
/*     */   }
/*     */   
/*     */   public void setDocumentTypeId(String documentTypeId) {
/* 268 */     this.documentTypeId = documentTypeId;
/*     */   }
/*     */   
/*     */   public Character getStatus() {
/* 272 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(Character status) {
/* 276 */     this.status = status;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 281 */     int hash = 0;
/* 282 */     hash += (this.id != null) ? this.id.hashCode() : 0;
/* 283 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 289 */     if (!(object instanceof gob.regionancash.uti.jpa.Document)) {
/* 290 */       return false;
/*     */     }
/* 292 */     gob.regionancash.uti.jpa.Document other = (gob.regionancash.uti.jpa.Document)object;
/* 293 */     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
/* 294 */       return false;
/*     */     }
/* 296 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 301 */     return "gob.regionancash.uti.jpa.Document[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/jpa/Document.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */