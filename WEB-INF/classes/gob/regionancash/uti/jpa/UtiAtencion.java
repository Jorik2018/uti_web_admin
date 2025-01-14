/*     */ package WEB-INF.classes.gob.regionancash.uti.jpa;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.persistence.Transient;
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "uti_atencion")
/*     */ public class UtiAtencion implements Serializable {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   @Basic(optional = false)
/*     */   @Column(name = "id")
/*     */   private Integer id;
/*     */   @Column(name = "type_id")
/*     */   private Short typeId;
/*     */   @Column(name = "people_id")
/*     */   private Integer peopleId;
/*     */   @Column(name = "dependecy_id")
/*     */   private Integer dependencyId;
/*     */   @Column(name = "client_id")
/*     */   private Integer clientId;
/*     */   @Column(name = "start_date")
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date startDate;
/*     */   @Column(name = "end_date")
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date endDate;
/*     */   @Column(name = "description")
/*     */   private String description;
/*     */   @Column(name = "satisfaction")
/*     */   private Short satisfaction;
/*     */   @Column(name = "confirmed_by")
/*     */   private Integer confirmedBy;
/*     */   @Column(name = "solution")
/*     */   private String solution;
/*     */   @Column(name = "update_date")
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date updateDate;
/*     */   @Column(name = "user")
/*     */   private Integer user;
/*     */   @Column(name = "canceled")
/*  53 */   private boolean canceled = Boolean.FALSE
/*  54 */     .booleanValue();
/*     */   
/*     */   @Transient
/*     */   private Object ext;
/*     */   
/*     */   public UtiAtencion() {}
/*     */   
/*     */   public UtiAtencion(Integer id) {
/*  62 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  66 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  70 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Short getTypeId() {
/*  74 */     return this.typeId;
/*     */   }
/*     */   
/*     */   public void setTypeId(Short typeId) {
/*  78 */     this.typeId = typeId;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/*  82 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/*  86 */     this.description = description;
/*     */   }
/*     */   
/*     */   public Integer getPeopleId() {
/*  90 */     return this.peopleId;
/*     */   }
/*     */   
/*     */   public void setPeopleId(Integer peopleId) {
/*  94 */     this.peopleId = peopleId;
/*     */   }
/*     */   
/*     */   public Object getExt() {
/*  98 */     return this.ext;
/*     */   }
/*     */   
/*     */   public void setExt(Object ext) {
/* 102 */     this.ext = ext;
/*     */   }
/*     */   
/*     */   public Integer getConfirmedBy() {
/* 106 */     return this.confirmedBy;
/*     */   }
/*     */   
/*     */   public void setConfirmedBy(Integer confirmedBy) {
/* 110 */     this.confirmedBy = confirmedBy;
/*     */   }
/*     */   
/*     */   public Integer getDependencyId() {
/* 114 */     return this.dependencyId;
/*     */   }
/*     */   
/*     */   public void setDependencyId(Integer dependecyId) {
/* 118 */     this.dependencyId = dependecyId;
/*     */   }
/*     */   
/*     */   public Integer getClientId() {
/* 122 */     return this.clientId;
/*     */   }
/*     */   
/*     */   public void setClientId(Integer clientId) {
/* 126 */     this.clientId = clientId;
/*     */   }
/*     */   
/*     */   public Date getStartDate() {
/* 130 */     return this.startDate;
/*     */   }
/*     */   
/*     */   public void setStartDate(Date startDate) {
/* 134 */     this.startDate = startDate;
/*     */   }
/*     */   
/*     */   public Date getEndDate() {
/* 138 */     return this.endDate;
/*     */   }
/*     */   
/*     */   public String getSolution() {
/* 142 */     return this.solution;
/*     */   }
/*     */   
/*     */   public void setSolution(String solution) {
/* 146 */     this.solution = solution;
/*     */   }
/*     */   
/*     */   public void setEndDate(Date endDate) {
/* 150 */     this.endDate = endDate;
/*     */   }
/*     */   
/*     */   public Short getSatisfaction() {
/* 154 */     return this.satisfaction;
/*     */   }
/*     */   
/*     */   public void setSatisfaction(Short satisfaction) {
/* 158 */     this.satisfaction = satisfaction;
/*     */   }
/*     */   
/*     */   public Date getUpdateDate() {
/* 162 */     return this.updateDate;
/*     */   }
/*     */   
/*     */   public void setUpdateDate(Date updateDate) {
/* 166 */     this.updateDate = updateDate;
/*     */   }
/*     */   
/*     */   public Integer getUser() {
/* 170 */     return this.user;
/*     */   }
/*     */   
/*     */   public void setUser(Integer user) {
/* 174 */     this.user = user;
/*     */   }
/*     */   
/*     */   public boolean getCanceled() {
/* 178 */     return this.canceled;
/*     */   }
/*     */   
/*     */   public void setCanceled(boolean canceled) {
/* 182 */     this.canceled = canceled;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 187 */     int hash = 0;
/* 188 */     hash += (this.id != null) ? this.id.hashCode() : 0;
/* 189 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 195 */     if (!(object instanceof gob.regionancash.uti.jpa.UtiAtencion)) {
/* 196 */       return false;
/*     */     }
/* 198 */     gob.regionancash.uti.jpa.UtiAtencion other = (gob.regionancash.uti.jpa.UtiAtencion)object;
/* 199 */     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
/* 200 */       return false;
/*     */     }
/* 202 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 207 */     return "javaapplication1.UtiAtencion[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/jpa/UtiAtencion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */