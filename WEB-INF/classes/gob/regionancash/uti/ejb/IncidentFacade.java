/*     */ package WEB-INF.classes.gob.regionancash.uti.ejb;
/*     */ 
/*     */ import gob.regionancash.uti.ejb.IncidentFacadeLocal;
/*     */ import gob.regionancash.uti.jpa.Incident;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.ejb.EJB;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.Query;
/*     */ import javax.ws.rs.client.ClientBuilder;
/*     */ import org.isobit.app.X;
/*     */ import org.isobit.app.ejb.UserFacadeLocal;
/*     */ import org.isobit.app.jpa.User;
/*     */ import org.isobit.util.AbstractFacade;
/*     */ import org.isobit.util.XUtil;
/*     */ 
/*     */ 
/*     */ @Stateless
/*     */ public class IncidentFacade
/*     */   extends AbstractFacade<Incident>
/*     */   implements IncidentFacadeLocal
/*     */ {
/*     */   @EJB
/*     */   private UserFacadeLocal userFacade;
/*     */   private static String UPLOAD_DIR;
/*     */   
/*     */   public List<Incident> load(int first, int pageSize, String sortField, Map<String, Object> filters) {
/*  31 */     Object type = XUtil.isEmpty(filters.get("type"), null);
/*  32 */     Object year = XUtil.isEmpty(filters.get("year"), null);
/*  33 */     Object all = XUtil.isEmpty(filters.get("all"), null);
/*  34 */     Object dependencyId = XUtil.isEmpty(filters.get("dependencyId"), null);
/*  35 */     List<Object> dependencyList = new ArrayList();
/*  36 */     EntityManager em = getEntityManager();
/*  37 */     User u = this.userFacade.getCurrentUser();
/*  38 */     if (dependencyId != null) {
/*  39 */       dependencyList.add(dependencyId);
/*     */     }
/*     */     
/*  42 */     if (all == null && !this.userFacade.access("ADMIN_UTI_DOCUMENT")) {
/*  43 */       dependencyList.add(Integer.valueOf(0));
/*  44 */       dependencyList.addAll(em.createQuery("SELECT c.dependencyId FROM Contract c WHERE c.canceled=0 AND c.active=1 AND c.peopleId=:people")
/*  45 */           .setParameter("people", u.getIdDir()).getResultList());
/*     */     } 
/*  47 */     Object query = XUtil.isEmpty(filters.get("query"), null);
/*  48 */     Object name = XUtil.isEmpty(filters.get("name"), null);
/*  49 */     if (query != null) {
/*  50 */       name = query;
/*     */     }
/*  52 */     Object description = XUtil.isEmpty(filters.get("description"), null);
/*  53 */     List<Query> ql = new ArrayList<>();
/*     */     String sql;
/*  55 */     ql.add(em.createQuery("SELECT o " + (sql = "FROM Incident o WHERE o.canceled=0 " + ((type != null) ? " AND o.documentTypeId=:type" : "") + ((year != null) ? " AND o.year=:year" : "") + ((name != null) ? " AND UPPER(o.name) LIKE :name" : "") + ((description != null) ? " AND UPPER(o.description) LIKE :description" : "") + "  ORDER BY o.id DESC")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  62 */     if (pageSize > 0) {
/*  63 */       ((Query)ql.get(0)).setFirstResult(first).setMaxResults(pageSize);
/*  64 */       ql.add(em.createQuery("SELECT COUNT(o) " + sql));
/*     */     } 
/*  66 */     for (Query q : ql) {
/*  67 */       if (type != null) {
/*  68 */         q.setParameter("type", type);
/*     */       }
/*  70 */       if (year != null) {
/*  71 */         q.setParameter("year", Integer.valueOf(XUtil.intValue(year)));
/*     */       }
/*  73 */       if (name != null) {
/*  74 */         q.setParameter("name", "%" + name.toString().replaceAll("\\s+", "%").toUpperCase() + "%");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  80 */       if (description != null) {
/*  81 */         q.setParameter("description", "%" + description.toString().replaceAll("\\s+", "%").toUpperCase() + "%");
/*     */       }
/*     */     } 
/*  84 */     if (pageSize > 0) {
/*  85 */       filters.put("size", ((Query)ql.get(1)).getSingleResult());
/*     */     }
/*  87 */     return ((Query)ql.get(0)).getResultList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getUploadDir() {
/*  93 */     if (UPLOAD_DIR == null)
/*     */     {
/*     */       
/*  96 */       UPLOAD_DIR = (String)ClientBuilder.newClient().target("http://localhost:" + X.getRequest().getLocalPort() + "/api/system/upload-dir").request().get(String.class);
/*     */     }
/*  98 */     return UPLOAD_DIR;
/*     */   }
/*     */ 
/*     */   
/*     */   public void edit(Incident entity) {
/* 103 */     User user = this.userFacade.getCurrentUser();
/*     */ 
/*     */ 
/*     */     
/* 107 */     if (entity.getCreatedDate() == null) {
/* 108 */       entity.setCreatedDate(X.getServerDate());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 117 */     if (entity.getId() == null) {
/*     */       
/* 119 */       create(entity);
/*     */     } else {
/* 121 */       super.edit(entity);
/*     */     } 
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
/*     */   public Incident load(Object id) {
/* 206 */     EntityManager em = getEntityManager();
/* 207 */     Incident s = (Incident)find((id instanceof Incident) ? ((Incident)id).getId() : id);
/* 208 */     if (s != null) {
/* 209 */       HashMap<Object, Object> hashMap = new HashMap<>();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 218 */     return s;
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/ejb/IncidentFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */