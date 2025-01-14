/*     */ package WEB-INF.classes.gob.regionancash.uti.ejb;
/*     */ 
/*     */ import gob.regionancash.uti.ejb.UtiAtencionFacadeLocal;
/*     */ import gob.regionancash.uti.jpa.UtiAtencion;
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
/*     */ import org.isobit.directory.jpa.Dependency;
/*     */ import org.isobit.directory.jpa.People;
/*     */ import org.isobit.util.AbstractFacade;
/*     */ import org.isobit.util.XMap;
/*     */ import org.isobit.util.XUtil;
/*     */ 
/*     */ @Stateless
/*     */ public class UtiAtencionFacade
/*     */   extends AbstractFacade<UtiAtencion> implements UtiAtencionFacadeLocal {
/*     */   @EJB
/*     */   private UserFacadeLocal userFacade;
/*     */   
/*     */   public List<UtiAtencion> load(int first, int pageSize, String sortField, Map<String, Object> filters) {
/*  29 */     Object filter = XUtil.isEmpty(filters.get("filter"), null);
/*  30 */     Object user = XUtil.isEmpty(filters.get("user"), null);
/*  31 */     Object empty = XUtil.isEmpty(filters.get("empty"), null);
/*  32 */     Object dependency = XUtil.isEmpty(filters.get("dependency"), null);
/*  33 */     Object description = XUtil.isEmpty(filters.get("description"), null);
/*  34 */     List<Query> ql = new ArrayList<>();
/*     */     
/*  36 */     EntityManager em = getEntityManager();
/*     */     String sql;
/*  38 */     ql.add(em.createQuery("SELECT o,s.fullName,c.fullName,CONCAT(t.name,' ',d.name) " + (sql = "FROM UtiAtencion o LEFT JOIN People s ON s.id=o.peopleId LEFT JOIN Dependency d ON d.id=o.dependencyId LEFT JOIN d.type t LEFT JOIN People c ON c.id=o.clientId WHERE o.canceled=0 " + ((user != null) ? (" AND (o.clientId=:user " + ((empty != null) ? " OR o.peopleId IS NULL " : "") + " OR o.peopleId=:user OR o.user=:user)") : "") + ((description != null) ? " AND UPPER(o.description) LIKE :description" : "") + ((dependency != null) ? " AND UPPER(CONCAT(t.name,d.name)) LIKE :dependency" : "")) + " ORDER BY 1 DESC"));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  43 */     if (pageSize > 0) {
/*  44 */       ((Query)ql.get(0)).setFirstResult(first).setMaxResults(pageSize);
/*  45 */       ql.add(em.createQuery("SELECT COUNT(o) " + sql));
/*     */     } 
/*  47 */     for (Query q : ql) {
/*  48 */       if (filter != null) {
/*  49 */         q.setParameter("filter", "%" + filter.toString().toUpperCase().replace(" ", "%") + "%");
/*     */       }
/*  51 */       if (dependency != null) {
/*  52 */         q.setParameter("dependency", "%" + dependency.toString().toUpperCase().replace(" ", "%") + "%");
/*     */       }
/*  54 */       if (description != null) {
/*  55 */         q.setParameter("description", "%" + description.toString().toUpperCase().replace(" ", "%") + "%");
/*     */       }
/*  57 */       if (user != null) {
/*  58 */         q.setParameter("user", user);
/*     */       }
/*     */     } 
/*  61 */     if (pageSize > 0) {
/*  62 */       filters.put("size", ((Query)ql.get(1)).getSingleResult());
/*     */     }
/*  64 */     return AbstractFacade.getColumn(((Query)ql.get(0)).getResultList(), (AbstractFacade.RowAdapter)new Object(this));
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
/*     */   public UtiAtencion find(Object id) {
/*  76 */     UtiAtencion u = (UtiAtencion)super.find(id);
/*  77 */     Map<Object, Object> ext = new HashMap<>();
/*  78 */     u.setExt(ext);
/*  79 */     EntityManager em = getEntityManager();
/*  80 */     People p = null;
/*  81 */     if (u.getClientId() != null) {
/*  82 */       p = (People)em.find(People.class, u.getClientId());
/*  83 */       em.detach(p);
/*  84 */       p.setDocument(null);
/*  85 */       ext.put("client", new XMap(new Object[] { "people", p }));
/*     */     } 
/*  87 */     if (u.getDependencyId() != null) {
/*  88 */       Dependency d = (Dependency)em.find(Dependency.class, u.getDependencyId());
/*  89 */       System.out.println(d.getType());
/*  90 */       ext.put("dependency", d);
/*     */     } 
/*  92 */     if (u.getPeopleId() != null) {
/*  93 */       p = (People)em.find(People.class, u.getPeopleId());
/*  94 */       if (p != null) {
/*  95 */         em.detach(p);
/*  96 */         p.setDocument(null);
/*  97 */         ext.put("server", p);
/*     */       } 
/*     */     } 
/* 100 */     return u;
/*     */   }
/*     */ 
/*     */   
/*     */   public void edit(UtiAtencion entity) {
/* 105 */     User user = this.userFacade.getCurrentUser();
/* 106 */     if (user != null && entity.getUser() == null) {
/* 107 */       entity.setUser(user.getIdDir());
/*     */     }
/* 109 */     if (entity.getPeopleId() != null && entity.getStartDate() == null) {
/* 110 */       entity.setStartDate(X.getServerDate());
/*     */     }
/* 112 */     if (entity.getId() == null) {
/* 113 */       create(entity);
/*     */     } else {
/* 115 */       super.edit(entity);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/ejb/UtiAtencionFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */