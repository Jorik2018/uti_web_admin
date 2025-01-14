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
/*     */ import javax.validation.constraints.Size;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "uti_incident")
/*     */ public class Incident
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   @Basic(optional = false)
/*     */   @Column(name = "id")
/*     */   private Integer id;
/*     */   @Column(name = "datetime")
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date datetime;
/*     */   @Size(max = 150)
/*     */   @Column(name = "place")
/*     */   private String place;
/*     */   @Size(max = 400)
/*     */   @Column(name = "affected")
/*     */   private String affected;
/*     */   @Size(max = 400)
/*     */   @Column(name = "ti_affected")
/*     */   private String tiAffected;
/*     */   @Size(max = 400)
/*     */   @Column(name = "description")
/*     */   private String description;
/*     */   @Size(max = 500)
/*     */   @Column(name = "evidence")
/*     */   private String evidence;
/*     */   @Size(max = 500)
/*     */   @Column(name = "type")
/*     */   private String type;
/*     */   @Size(max = 200)
/*     */   @Column(name = "demonstration_type")
/*     */   private String demonstrationType;
/*     */   @Size(max = 500)
/*     */   @Column(name = "damage")
/*     */   private String damage;
/*     */   @Size(max = 500)
/*     */   @Column(name = "aggressor")
/*     */   private String aggressor;
/*     */   @Size(max = 500)
/*     */   private String motivation;
/*     */   @Size(max = 500)
/*     */   private String tracking;
/*     */   @Column(name = "created_date")
/*     */   @Temporal(TemporalType.TIMESTAMP)
/*     */   private Date createdDate;
/*     */   @Column(name = "created_by")
/*     */   private Integer createdBy;
/*     */   private boolean canceled;
/*     */   
/*     */   public Incident() {}
/*     */   
/*     */   public Incident(Integer id) {
/*  73 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/*  77 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/*  81 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Date getDatetime() {
/*  85 */     return this.datetime;
/*     */   }
/*     */   
/*     */   public void setDatetime(Date datetime) {
/*  89 */     this.datetime = datetime;
/*     */   }
/*     */   
/*     */   public String getPlace() {
/*  93 */     return this.place;
/*     */   }
/*     */   
/*     */   public void setPlace(String place) {
/*  97 */     this.place = place;
/*     */   }
/*     */   
/*     */   public String getAffected() {
/* 101 */     return this.affected;
/*     */   }
/*     */   
/*     */   public void setAffected(String affected) {
/* 105 */     this.affected = affected;
/*     */   }
/*     */   
/*     */   public String getTiAffected() {
/* 109 */     return this.tiAffected;
/*     */   }
/*     */   
/*     */   public void setTiAffected(String tiAffected) {
/* 113 */     this.tiAffected = tiAffected;
/*     */   }
/*     */   
/*     */   public String getDescription() {
/* 117 */     return this.description;
/*     */   }
/*     */   
/*     */   public void setDescription(String description) {
/* 121 */     this.description = description;
/*     */   }
/*     */   
/*     */   public String getEvidence() {
/* 125 */     return this.evidence;
/*     */   }
/*     */   
/*     */   public void setEvidence(String evidence) {
/* 129 */     this.evidence = evidence;
/*     */   }
/*     */   
/*     */   public String getType() {
/* 133 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setType(String type) {
/* 137 */     this.type = type;
/*     */   }
/*     */   
/*     */   public String getDemonstrationType() {
/* 141 */     return this.demonstrationType;
/*     */   }
/*     */   
/*     */   public void setDemonstrationType(String demonstrationType) {
/* 145 */     this.demonstrationType = demonstrationType;
/*     */   }
/*     */   
/*     */   public String getDamage() {
/* 149 */     return this.damage;
/*     */   }
/*     */   
/*     */   public void setDamage(String damage) {
/* 153 */     this.damage = damage;
/*     */   }
/*     */   
/*     */   public String getAggressor() {
/* 157 */     return this.aggressor;
/*     */   }
/*     */   
/*     */   public void setAggressor(String aggressor) {
/* 161 */     this.aggressor = aggressor;
/*     */   }
/*     */   
/*     */   public String getMotivation() {
/* 165 */     return this.motivation;
/*     */   }
/*     */   
/*     */   public void setMotivation(String motivation) {
/* 169 */     this.motivation = motivation;
/*     */   }
/*     */   
/*     */   public String getTracking() {
/* 173 */     return this.tracking;
/*     */   }
/*     */   
/*     */   public void setTracking(String tracking) {
/* 177 */     this.tracking = tracking;
/*     */   }
/*     */   
/*     */   public Date getCreatedDate() {
/* 181 */     return this.createdDate;
/*     */   }
/*     */   
/*     */   public void setCreatedDate(Date createdDate) {
/* 185 */     this.createdDate = createdDate;
/*     */   }
/*     */   
/*     */   public Integer getCreatedBy() {
/* 189 */     return this.createdBy;
/*     */   }
/*     */   
/*     */   public void setCreatedBy(Integer createdBy) {
/* 193 */     this.createdBy = createdBy;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getCanceled() {
/* 199 */     return this.canceled;
/*     */   }
/*     */   
/*     */   public void setCanceled(boolean canceled) {
/* 203 */     this.canceled = canceled;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 208 */     int hash = 0;
/* 209 */     hash += (this.id != null) ? this.id.hashCode() : 0;
/* 210 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 216 */     if (!(object instanceof gob.regionancash.uti.jpa.Incident)) {
/* 217 */       return false;
/*     */     }
/* 219 */     gob.regionancash.uti.jpa.Incident other = (gob.regionancash.uti.jpa.Incident)object;
/* 220 */     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
/* 221 */       return false;
/*     */     }
/* 223 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 228 */     return "gob.regionancash.uti.jpa.Incident[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/jpa/Incident.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */