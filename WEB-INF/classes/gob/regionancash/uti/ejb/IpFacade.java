/*     */ package WEB-INF.classes.gob.regionancash.uti.ejb;
/*     */ 
/*     */ import gob.regionancash.uti.ejb.IpFacadeLocal;
/*     */ import gob.regionancash.uti.jpa.UtiIp;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.ejb.EJB;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.Query;
/*     */ import org.isobit.app.X;
/*     */ import org.isobit.app.ejb.UserFacadeLocal;
/*     */ import org.isobit.app.jpa.User;
/*     */ import org.isobit.directory.jpa.People;
/*     */ import org.isobit.util.AbstractFacade;
/*     */ import org.isobit.util.XUtil;
/*     */ 
/*     */ @Stateless
/*     */ public class IpFacade
/*     */   extends AbstractFacade<UtiIp>
/*     */   implements IpFacadeLocal {
/*     */   @EJB
/*     */   private UserFacadeLocal userFacade;
/*     */   
/*     */   public List<UtiIp> load(int first, int pageSize, String sortField, Map<String, Object> filters) {
/*  28 */     Object filter = XUtil.isEmpty(filters.get("filter"), null);
/*  29 */     Object user = XUtil.isEmpty(filters.get("user"), null);
/*  30 */     Object category = filters.get("category");
/*  31 */     Object code = XUtil.isEmpty(filters.get("code"), null);
/*  32 */     Object name = XUtil.isEmpty(filters.get("name"), null);
/*  33 */     Object macId = XUtil.isEmpty(filters.get("macId"), null);
/*     */     
/*  35 */     Object position = XUtil.isEmpty(filters.get("position"), null);
/*  36 */     Object device = XUtil.isEmpty(filters.get("device"), null);
/*  37 */     Object siaf = XUtil.isEmpty(filters.get("siaf"), null);
/*  38 */     Object userSiaf = XUtil.isEmpty(filters.get("userSiaf"), null);
/*  39 */     Object patrimonialCode = XUtil.isEmpty(filters.get("patrimonialCode"), null);
/*     */     
/*  41 */     Object description = XUtil.isEmpty(filters.get("description"), null);
/*     */     
/*  43 */     Object empty = XUtil.isEmpty(filters.get("empty"), null);
/*  44 */     Object dependency = XUtil.isEmpty(filters.get("dependency"), null);
/*  45 */     List<Query> ql = new ArrayList<>();
/*     */     
/*  47 */     EntityManager em = getEntityManager();
/*  48 */     String SORTER = (String)XUtil.isEmpty(filters.get("SORTER"), null);
/*     */     
/*  50 */     String sort = "o.n3,n4,o.code DESC";
/*  51 */     if (SORTER != null) {
/*  52 */       String[] s = SORTER.split("-");
/*  53 */       if ("updated".equals(s[0])) {
/*  54 */         sort = "o.updated " + s[1];
/*  55 */       } else if ("code".equals(s[0])) {
/*  56 */         sort = "o.n3 " + s[1] + ",n4 " + s[1] + ",o.code " + s[1];
/*     */       } 
/*     */     } 
/*     */     String sql;
/*  60 */     ql.add(em.createQuery("SELECT o,s.fullName " + (
/*     */ 
/*     */ 
/*     */           
/*  64 */           sql = "FROM UtiIp o LEFT JOIN People s ON s.id=o.peopleId LEFT JOIN Dependency d ON d.id=o.dependencyId LEFT JOIN d.type t WHERE o.canceled=0 " + ((category != null) ? ("".equals(category.toString().trim()) ? " AND (trim(o.category)='' OR o.category IS NULL) " : " AND UPPER(o.category) LIKE :category") : "") + ((filter != null) ? " AND (UPPER(o.category) LIKE :filter OR UPPER(o.code) LIKE :filter OR UPPER(o.name) LIKE :filter OR UPPER(o.siaf) LIKE :filter OR UPPER(o.macId) LIKE :filter OR UPPER(o.workgroup) LIKE :filter OR UPPER(o.patrimonialCode) LIKE :filter OR UPPER(o.userSiaf) LIKE :filter OR UPPER(o.position) LIKE :filter OR UPPER(o.antivirus) LIKE :filter OR UPPER(o.restriction) LIKE :filter OR UPPER(o.description) LIKE :filter OR UPPER(o.device) LIKE :filter OR UPPER(t.name) LIKE :filter OR UPPER(d.name) LIKE :filter OR UPPER(o.stabilizer) LIKE :filter OR UPPER(o.stabilizerCode) LIKE :filter OR UPPER(o.stabilizerDescription) LIKE :filter OR UPPER(o.printerCode) LIKE :filter OR UPPER(o.printerDescription) LIKE :filter OR UPPER(o.printerSerie) LIKE :filter OR UPPER(o.printerModel) LIKE :filter OR UPPER(o.deviceSerie) LIKE :filter OR UPPER(o.deviceModel) LIKE :filter)" : "") + ((code != null) ? " AND UPPER(o.code) LIKE :code" : "") + ((name != null) ? " AND UPPER(o.name) LIKE :name" : "") + ((siaf != null) ? " AND UPPER(o.siaf) LIKE :siaf" : "") + ((macId != null) ? " AND UPPER(o.macId) LIKE :macId" : "") + ((patrimonialCode != null) ? " AND UPPER(o.patrimonialCode) LIKE :patrimonialCode" : "") + ((userSiaf != null) ? " AND UPPER(o.userSiaf) LIKE :userSiaf" : "") + ((position != null) ? " AND UPPER(o.position) LIKE :position" : "") + ((description != null) ? " AND UPPER(o.description) LIKE :description" : "") + ((device != null) ? " AND UPPER(o.device) LIKE :device" : "") + ((dependency != null) ? " AND UPPER(CONCAT(t.name,d.name)) LIKE :dependency" : "")) + " ORDER BY " + sort));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     if (pageSize > 0) {
/*  81 */       ((Query)ql.get(0)).setFirstResult(first).setMaxResults(pageSize);
/*  82 */       ql.add(em.createQuery("SELECT COUNT(o) " + sql));
/*     */     } 
/*  84 */     for (Query q : ql) {
/*  85 */       if (filter != null) {
/*  86 */         q.setParameter("filter", "%" + filter.toString().toUpperCase().replaceAll("\\s+", "%").replaceAll("-", "%").replaceAll("[:]", "%").replaceAll("[.]", "%") + "%");
/*     */       }
/*  88 */       if (dependency != null) {
/*  89 */         q.setParameter("dependency", "%" + dependency.toString().toUpperCase().replace(" ", "%") + "%");
/*     */       }
/*  91 */       if (category != null && !"".equals(category)) {
/*  92 */         q.setParameter("category", "" + category.toString().toUpperCase().replace(" ", "%") + "");
/*     */       }
/*  94 */       if (code != null) {
/*  95 */         q.setParameter("code", "%" + code.toString().toUpperCase().replace(" ", "%") + "%");
/*     */       }
/*  97 */       if (name != null) {
/*  98 */         q.setParameter("name", "%" + name.toString().toUpperCase().replace(" ", "%") + "%");
/*     */       }
/* 100 */       if (siaf != null) {
/* 101 */         q.setParameter("siaf", "%" + siaf.toString().toUpperCase().replace(" ", "%") + "%");
/*     */       }
/* 103 */       if (macId != null) {
/* 104 */         System.out.println("%" + macId.toString().toUpperCase().replaceAll("\\s+", "%").replaceAll("-", "%").replaceAll("[.]", "%") + "%");
/* 105 */         q.setParameter("macId", "%" + macId.toString().toUpperCase().replaceAll("\\s+", "%").replaceAll("-", "%").replaceAll("[.]", "%") + "%");
/*     */       } 
/* 107 */       if (patrimonialCode != null) {
/* 108 */         q.setParameter("patrimonialCode", "%" + patrimonialCode.toString().toUpperCase().replaceAll("\\s+", "%") + "%");
/*     */       }
/*     */       
/* 111 */       if (userSiaf != null) {
/* 112 */         q.setParameter("userSiaf", "%" + userSiaf.toString().toUpperCase().replaceAll("\\s+", "%") + "%");
/*     */       }
/* 114 */       if (position != null) {
/* 115 */         q.setParameter("position", "%" + position.toString().toUpperCase().replaceAll("\\s+", "%") + "%");
/*     */       }
/* 117 */       if (description != null) {
/* 118 */         q.setParameter("description", "%" + description.toString().toUpperCase().replaceAll("\\s+", "%") + "%");
/*     */       }
/* 120 */       if (device != null) {
/* 121 */         q.setParameter("device", "%" + device.toString().toUpperCase().replace(" ", "%") + "%");
/*     */       }
/*     */       
/* 124 */       if (user != null) {
/* 125 */         q.setParameter("user", user);
/*     */       }
/*     */     } 
/* 128 */     if (pageSize > 0) {
/* 129 */       filters.put("size", ((Query)ql.get(1)).getSingleResult());
/*     */     }
/* 131 */     return AbstractFacade.getColumn(((Query)ql.get(0)).getResultList(), (AbstractFacade.RowAdapter)new Object(this));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public UtiIp find(Object id) {
/* 143 */     UtiIp u = (UtiIp)super.find(id);
/* 144 */     Map<Object, Object> ext = new HashMap<>();
/* 145 */     u.setExt(ext);
/* 146 */     EntityManager em = getEntityManager();
/* 147 */     People p = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 167 */     return u;
/*     */   }
/*     */ 
/*     */   
/*     */   public void edit(UtiIp entity) {
/* 172 */     User user = this.userFacade.getCurrentUser();
/* 173 */     if (entity.getCode() != null) {
/* 174 */       String[] sc = entity.getCode().split("\\.");
/* 175 */       if (sc.length != 4) {
/* 176 */         throw new RuntimeException("El ip=" + entity.getCode() + " no es una direccion valida.");
/*     */       }
/* 178 */       entity.setCode(Integer.parseInt(sc[0]) + "." + 
/* 179 */           Integer.parseInt(sc[1]) + "." + 
/* 180 */           Integer.parseInt(sc[2]) + "." + 
/* 181 */           Integer.parseInt(sc[3]));
/* 182 */       entity.setN3(Integer.valueOf(Integer.parseInt(sc[2])));
/* 183 */       entity.setN4(Integer.valueOf(Integer.parseInt(sc[3])));
/*     */     } 
/*     */     
/* 186 */     if (!entity.isCanceled()) if (XUtil.intValue(getEntityManager().createQuery("SELECT COUNT(*) FROM UtiIp u WHERE u.canceled=0 AND u.code=:code AND NOT u.id=:id")
/* 187 */           .setParameter("id", Integer.valueOf((entity.getId() != null) ? entity.getId().intValue() : 0))
/* 188 */           .setParameter("code", entity.getCode())
/* 189 */           .getSingleResult()) > 0) {
/* 190 */         throw new RuntimeException("El ip=" + entity.getCode() + " ya existe.");
/*     */       }
/*     */     
/* 193 */     if (user != null && entity.getUser() == null) {
/* 194 */       entity.setUser(user.getIdDir());
/*     */     }
/* 196 */     if (entity.getCreation() == null) {
/* 197 */       entity.setCreation(X.getServerDate());
/*     */     }
/* 199 */     if (entity.getId() == null) {
/* 200 */       create(entity);
/*     */     } else {
/* 202 */       entity.setUpdated(X.getServerDate());
/* 203 */       super.edit(entity);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List getCategoryList() {
/* 209 */     return getEntityManager().createQuery("SELECT DISTINCT o.category FROM UtiIp o ORDER BY o.category").getResultList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void disable(List<Integer> l) {
/* 215 */     getEntityManager().createQuery("UPDATE UtiIp u SET u.active=(CASE WHEN u.active=1 THEN CAST(0 as integer) ELSE CAST(1 as integer) END) WHERE u.id IN :id")
/* 216 */       .setParameter("id", l).executeUpdate();
/*     */   }
/*     */ 
/*     */   
/*     */   public void limpiar(UtiIp entity) {
/* 221 */     entity.setCreation(null);
/* 222 */     entity.setUpdated(null);
/* 223 */     entity.setName(null);
/* 224 */     entity.setPosition(null);
/* 225 */     entity.setDeviceModel(null);
/* 226 */     entity.setPatrimonialCode(null);
/* 227 */     entity.setDeviceSerie(null);
/* 228 */     entity.setMacId(null);
/* 229 */     entity.setDescription(null);
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/ejb/IpFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */