/*    */ package WEB-INF.classes.gob.regionancash.uti.ejb;
/*    */ 
/*    */ import gob.regionancash.uti.ejb.UtiServiceFacadeLocal;
/*    */ import gob.regionancash.uti.jpa.UtiService;
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.ejb.EJB;
/*    */ import javax.ejb.Stateless;
/*    */ import javax.persistence.EntityManager;
/*    */ import javax.persistence.Query;
/*    */ import org.isobit.app.ejb.UserFacadeLocal;
/*    */ import org.isobit.app.jpa.User;
/*    */ import org.isobit.directory.ejb.CompanyFacadeLocal;
/*    */ import org.isobit.util.AbstractFacade;
/*    */ import org.isobit.util.XUtil;
/*    */ 
/*    */ @Stateless
/*    */ public class UtiServiceFacade
/*    */   extends AbstractFacade<UtiService> implements UtiServiceFacadeLocal {
/*    */   @EJB
/*    */   private UserFacadeLocal userFacade;
/*    */   @EJB
/*    */   private CompanyFacadeLocal drtPersonaJuridicaFacade;
/*    */   
/*    */   public List<UtiService> load(int first, int pageSize, String sortField, Map<String, Object> filters) {
/* 27 */     Object filter = XUtil.isEmpty(filters.get("filter"), null);
/* 28 */     Object dependency = XUtil.isEmpty(filters.get("dependency"), null);
/* 29 */     Object company = XUtil.isEmpty(filters.get("entity"), null);
/* 30 */     List<Query> ql = new ArrayList<>();
/*    */ 
/*    */ 
/*    */     
/* 34 */     User u = this.userFacade.getCurrentUser();
/* 35 */     EntityManager em = getEntityManager();
/* 36 */     System.out.println(em.createQuery("SELECT c.dependencyId FROM Contract c WHERE c.active=1 AND c.peopleId=:people")
/* 37 */         .setParameter("people", u.getIdDir()).getResultList());
/*    */     
/*    */     String sql;
/* 40 */     ql.add(em.createQuery("SELECT o " + (sql = "FROM UtiService o " + ((dependency instanceof String) ? "JOIN o.dependency d " : "") + "WHERE 1=1 " + ((company != null) ? " AND (o.entity LIKE :company)" : "") + ((dependency != null) ? (" AND " + ((dependency instanceof String) ? "UPPER(CONCAT(d.tipo.nombre,d.nombre)) LIKE :dependency" : (((dependency instanceof org.isobit.directory.jpa.Dependency) ? "o.dependency" : "o.dependency.id") + "=:dependency"))) : "")) + "  ORDER BY 1 ASC"));
/*    */ 
/*    */     
/* 43 */     if (pageSize > 0) {
/* 44 */       ((Query)ql.get(0)).setFirstResult(first).setMaxResults(pageSize);
/* 45 */       ql.add(em.createQuery("SELECT COUNT(o) " + sql));
/*    */     } 
/* 47 */     for (Query q : ql) {
/* 48 */       if (company != null) {
/* 49 */         q.setParameter("company", (company instanceof org.isobit.directory.jpa.Company) ? company : ("%" + company + "%"));
/*    */       }
/* 51 */       if (dependency != null) {
/* 52 */         if (dependency instanceof String) {
/* 53 */           q.setParameter("dependency", "%" + ((String)dependency).toUpperCase() + "%"); continue;
/*    */         } 
/* 55 */         q.setParameter("dependency", (dependency instanceof org.isobit.directory.jpa.Dependency) ? dependency : Integer.valueOf(XUtil.intValue(dependency)));
/*    */       } 
/*    */     } 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 62 */     if (pageSize > 0) {
/* 63 */       filters.put("size", ((Query)ql.get(1)).getSingleResult());
/*    */     }
/* 65 */     return ((Query)ql.get(0)).getResultList();
/*    */   }
/*    */ 
/*    */   
/*    */   public void edit(UtiService entity) {
/* 70 */     if (entity.getId() == null) {
/* 71 */       create(entity);
/*    */     } else {
/* 73 */       super.edit(entity);
/*    */     } 
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public UtiService load(Object id) {
/* 83 */     UtiService s = (UtiService)find((id instanceof UtiService) ? ((UtiService)id).getId() : id.toString());
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 94 */     return s;
/*    */   }
/*    */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/ejb/UtiServiceFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */