/*     */ package WEB-INF.classes.gob.regionancash.uti.jpa;
/*     */ 
/*     */ import gob.regionancash.rh.jpa.Position;
/*     */ import java.io.Serializable;
/*     */ import java.util.Date;
/*     */ import javax.persistence.Basic;
/*     */ import javax.persistence.Column;
/*     */ import javax.persistence.Entity;
/*     */ import javax.persistence.GeneratedValue;
/*     */ import javax.persistence.GenerationType;
/*     */ import javax.persistence.Id;
/*     */ import javax.persistence.JoinColumn;
/*     */ import javax.persistence.Lob;
/*     */ import javax.persistence.ManyToOne;
/*     */ import javax.persistence.Table;
/*     */ import javax.persistence.Temporal;
/*     */ import javax.persistence.TemporalType;
/*     */ import javax.validation.constraints.Size;
/*     */ import org.isobit.directory.jpa.Dependency;
/*     */ import org.isobit.directory.jpa.People;
/*     */ 
/*     */ @Entity
/*     */ @Table(name = "uti_service")
/*     */ public class UtiService
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   @Id
/*     */   @GeneratedValue(strategy = GenerationType.IDENTITY)
/*     */   @Basic(optional = false)
/*     */   @Column(name = "id")
/*     */   private Integer id;
/*     */   @Size(max = 150)
/*     */   @Column(name = "name")
/*     */   private String name;
/*     */   @Size(max = 300)
/*     */   @Column(name = "methods")
/*     */   private String methods;
/*     */   @Size(max = 30)
/*     */   @Column(name = "ruc")
/*     */   private String ruc;
/*     */   @Size(max = 300)
/*     */   @Column(name = "entity")
/*     */   private String entity;
/*     */   @Size(max = 100)
/*     */   @Column(name = "acronym")
/*     */   private String acronym;
/*     */   @Size(max = 100)
/*     */   @Column(name = "status")
/*     */   private String status;
/*     */   @Size(max = 100)
/*     */   @Column(name = "limitations")
/*     */   private String limitations;
/*     */   @Size(max = 100)
/*     */   @Column(name = "access")
/*     */   private String access;
/*     */   @Size(max = 100)
/*     */   @Column(name = "legal_base")
/*     */   private String legalBase;
/*     */   @Size(max = 50)
/*     */   @Column(name = "choose")
/*     */   private String choose;
/*     */   @Lob
/*     */   @Size(max = 65535)
/*     */   @Column(name = "ws_justify")
/*     */   private String wsJustify;
/*     */   @JoinColumn(name = "dependency_id", referencedColumnName = "id_dep")
/*     */   @ManyToOne(optional = true)
/*     */   private Dependency dependency;
/*     */   @JoinColumn(name = "position_id", referencedColumnName = "id")
/*     */   @ManyToOne(optional = true)
/*     */   private Position position;
/*     */   @Column(name = "official_id")
/*     */   private Integer officialId;
/*     */   @Column(name = "date_start_use")
/*     */   @Temporal(TemporalType.DATE)
/*     */   private Date dateStartUse;
/*     */   @Size(max = 100)
/*     */   @Column(name = "app_name")
/*     */   private String appName;
/*     */   @Lob
/*     */   @Size(max = 65535)
/*     */   @Column(name = "app_description")
/*     */   private String appDescription;
/*     */   @Size(max = 100)
/*     */   @Column(name = "tecnology_used")
/*     */   private String tecnologyUsed;
/*     */   @Size(max = 200)
/*     */   @Column(name = "consumer_ip_public")
/*     */   private String consumerIpPublic;
/*     */   @Size(max = 200)
/*     */   @Column(name = "consumer_ip_mac_public")
/*     */   private String consumerIpMacPublic;
/*     */   @Size(max = 200)
/*     */   @Column(name = "entity_net_address")
/*     */   private String entityNetAddress;
/*     */   @Size(max = 200)
/*     */   @Column(name = "entity_net_mask")
/*     */   private String entityNetMask;
/*     */   @Size(max = 200)
/*     */   @Column(name = "criticality_level")
/*     */   private String criticalityLevel;
/*     */   @Size(max = 200)
/*     */   @Column(name = "criticality_justify")
/*     */   private String criticalityJustify;
/*     */   @Size(max = 200)
/*     */   @Column(name = "request_avg_x_hour")
/*     */   private String requestAvgXHour;
/*     */   @Size(max = 200)
/*     */   @Column(name = "use_hours")
/*     */   private String useHours;
/*     */   @Size(max = 200)
/*     */   @Column(name = "request_max_x_hour")
/*     */   private String requestMaxXHour;
/*     */   @Size(max = 200)
/*     */   @Column(name = "max_hour")
/*     */   private String maxHour;
/*     */   @JoinColumn(name = "officer_main_id", referencedColumnName = "id_dir")
/*     */   @ManyToOne(optional = true)
/*     */   private People officerMain;
/*     */   @JoinColumn(name = "officer_busyness_id", referencedColumnName = "id_dir")
/*     */   @ManyToOne(optional = true)
/*     */   private People officerBusyness;
/*     */   @JoinColumn(name = "officer_tecnical_id", referencedColumnName = "id_dir")
/*     */   @ManyToOne(optional = true)
/*     */   private People officerTecnical;
/*     */   
/*     */   public Position getPosition() {
/* 129 */     return this.position;
/*     */   }
/*     */   
/*     */   public void setPosition(Position position) {
/* 133 */     this.position = position;
/*     */   }
/*     */ 
/*     */   
/*     */   public UtiService() {}
/*     */   
/*     */   public UtiService(Integer id) {
/* 140 */     this.id = id;
/*     */   }
/*     */   
/*     */   public Integer getId() {
/* 144 */     return this.id;
/*     */   }
/*     */   
/*     */   public void setId(Integer id) {
/* 148 */     this.id = id;
/*     */   }
/*     */   
/*     */   public String getName() {
/* 152 */     return this.name;
/*     */   }
/*     */   
/*     */   public void setName(String name) {
/* 156 */     this.name = name;
/*     */   }
/*     */   
/*     */   public String getMethods() {
/* 160 */     return this.methods;
/*     */   }
/*     */   
/*     */   public void setMethods(String methods) {
/* 164 */     this.methods = methods;
/*     */   }
/*     */   
/*     */   public String getRuc() {
/* 168 */     return this.ruc;
/*     */   }
/*     */   
/*     */   public void setRuc(String ruc) {
/* 172 */     this.ruc = ruc;
/*     */   }
/*     */   
/*     */   public String getEntity() {
/* 176 */     return this.entity;
/*     */   }
/*     */   
/*     */   public void setEntity(String entity) {
/* 180 */     this.entity = entity;
/*     */   }
/*     */   
/*     */   public String getAcronym() {
/* 184 */     return this.acronym;
/*     */   }
/*     */   
/*     */   public void setAcronym(String acronym) {
/* 188 */     this.acronym = acronym;
/*     */   }
/*     */   
/*     */   public String getStatus() {
/* 192 */     return this.status;
/*     */   }
/*     */   
/*     */   public void setStatus(String status) {
/* 196 */     this.status = status;
/*     */   }
/*     */   
/*     */   public String getLimitations() {
/* 200 */     return this.limitations;
/*     */   }
/*     */   
/*     */   public void setLimitations(String limitations) {
/* 204 */     this.limitations = limitations;
/*     */   }
/*     */   
/*     */   public String getAccess() {
/* 208 */     return this.access;
/*     */   }
/*     */   
/*     */   public void setAccess(String access) {
/* 212 */     this.access = access;
/*     */   }
/*     */   
/*     */   public String getLegalBase() {
/* 216 */     return this.legalBase;
/*     */   }
/*     */   
/*     */   public void setLegalBase(String legalBase) {
/* 220 */     this.legalBase = legalBase;
/*     */   }
/*     */   
/*     */   public String getChoose() {
/* 224 */     return this.choose;
/*     */   }
/*     */   
/*     */   public void setChoose(String choose) {
/* 228 */     this.choose = choose;
/*     */   }
/*     */   
/*     */   public String getWsJustify() {
/* 232 */     return this.wsJustify;
/*     */   }
/*     */   
/*     */   public void setWsJustify(String wsJustify) {
/* 236 */     this.wsJustify = wsJustify;
/*     */   }
/*     */   
/*     */   public Dependency getDependency() {
/* 240 */     return this.dependency;
/*     */   }
/*     */   
/*     */   public void setDependency(Dependency dependency) {
/* 244 */     this.dependency = dependency;
/*     */   }
/*     */   
/*     */   public Integer getOfficialId() {
/* 248 */     return this.officialId;
/*     */   }
/*     */   
/*     */   public void setOfficialId(Integer officialId) {
/* 252 */     this.officialId = officialId;
/*     */   }
/*     */   
/*     */   public Date getDateStartUse() {
/* 256 */     return this.dateStartUse;
/*     */   }
/*     */   
/*     */   public void setDateStartUse(Date dateStartUse) {
/* 260 */     this.dateStartUse = dateStartUse;
/*     */   }
/*     */   
/*     */   public String getAppName() {
/* 264 */     return this.appName;
/*     */   }
/*     */   
/*     */   public void setAppName(String appName) {
/* 268 */     this.appName = appName;
/*     */   }
/*     */   
/*     */   public String getAppDescription() {
/* 272 */     return this.appDescription;
/*     */   }
/*     */   
/*     */   public void setAppDescription(String appDescription) {
/* 276 */     this.appDescription = appDescription;
/*     */   }
/*     */   
/*     */   public String getTecnologyUsed() {
/* 280 */     return this.tecnologyUsed;
/*     */   }
/*     */   
/*     */   public void setTecnologyUsed(String tecnologyUsed) {
/* 284 */     this.tecnologyUsed = tecnologyUsed;
/*     */   }
/*     */   
/*     */   public String getConsumerIpPublic() {
/* 288 */     return this.consumerIpPublic;
/*     */   }
/*     */   
/*     */   public void setConsumerIpPublic(String consumerIpPublic) {
/* 292 */     this.consumerIpPublic = consumerIpPublic;
/*     */   }
/*     */   
/*     */   public String getConsumerIpMacPublic() {
/* 296 */     return this.consumerIpMacPublic;
/*     */   }
/*     */   
/*     */   public void setConsumerIpMacPublic(String consumerIpMacPublic) {
/* 300 */     this.consumerIpMacPublic = consumerIpMacPublic;
/*     */   }
/*     */   
/*     */   public String getEntityNetAddress() {
/* 304 */     return this.entityNetAddress;
/*     */   }
/*     */   
/*     */   public void setEntityNetAddress(String entityNetAddress) {
/* 308 */     this.entityNetAddress = entityNetAddress;
/*     */   }
/*     */   
/*     */   public String getEntityNetMask() {
/* 312 */     return this.entityNetMask;
/*     */   }
/*     */   
/*     */   public void setEntityNetMask(String entityNetMask) {
/* 316 */     this.entityNetMask = entityNetMask;
/*     */   }
/*     */   
/*     */   public String getCriticalityLevel() {
/* 320 */     return this.criticalityLevel;
/*     */   }
/*     */   
/*     */   public void setCriticalityLevel(String criticalityLevel) {
/* 324 */     this.criticalityLevel = criticalityLevel;
/*     */   }
/*     */   
/*     */   public String getCriticalityJustify() {
/* 328 */     return this.criticalityJustify;
/*     */   }
/*     */   
/*     */   public void setCriticalityJustify(String criticalityJustify) {
/* 332 */     this.criticalityJustify = criticalityJustify;
/*     */   }
/*     */   
/*     */   public String getRequestAvgXHour() {
/* 336 */     return this.requestAvgXHour;
/*     */   }
/*     */   
/*     */   public void setRequestAvgXHour(String requestAvgXHour) {
/* 340 */     this.requestAvgXHour = requestAvgXHour;
/*     */   }
/*     */   
/*     */   public String getUseHours() {
/* 344 */     return this.useHours;
/*     */   }
/*     */   
/*     */   public void setUseHours(String useHours) {
/* 348 */     this.useHours = useHours;
/*     */   }
/*     */   
/*     */   public String getRequestMaxXHour() {
/* 352 */     return this.requestMaxXHour;
/*     */   }
/*     */   
/*     */   public void setRequestMaxXHour(String requestMaxXHour) {
/* 356 */     this.requestMaxXHour = requestMaxXHour;
/*     */   }
/*     */   
/*     */   public String getMaxHour() {
/* 360 */     return this.maxHour;
/*     */   }
/*     */   
/*     */   public void setMaxHour(String maxHour) {
/* 364 */     this.maxHour = maxHour;
/*     */   }
/*     */   
/*     */   public People getOfficerMain() {
/* 368 */     return this.officerMain;
/*     */   }
/*     */   
/*     */   public void setOfficerMain(People officerMain) {
/* 372 */     this.officerMain = officerMain;
/*     */   }
/*     */   
/*     */   public People getOfficerBusyness() {
/* 376 */     return this.officerBusyness;
/*     */   }
/*     */   
/*     */   public void setOfficerBusyness(People officerBusyness) {
/* 380 */     this.officerBusyness = officerBusyness;
/*     */   }
/*     */   
/*     */   public People getOfficerTecnical() {
/* 384 */     return this.officerTecnical;
/*     */   }
/*     */   
/*     */   public void setOfficerTecnical(People officerTecnical) {
/* 388 */     this.officerTecnical = officerTecnical;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 393 */     int hash = 0;
/* 394 */     hash += (this.id != null) ? this.id.hashCode() : 0;
/* 395 */     return hash;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 401 */     if (!(object instanceof gob.regionancash.uti.jpa.UtiService)) {
/* 402 */       return false;
/*     */     }
/* 404 */     gob.regionancash.uti.jpa.UtiService other = (gob.regionancash.uti.jpa.UtiService)object;
/* 405 */     if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
/* 406 */       return false;
/*     */     }
/* 408 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 413 */     return "org.ocpsoft.rewrite.servlet.UtiService[ id=" + this.id + " ]";
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/jpa/UtiService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */