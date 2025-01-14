/*    */ package WEB-INF.classes.gob.regionancash.uti.rest;
/*    */ import gob.regionancash.uti.rest.DocumentFacadeREST;
/*    */ import gob.regionancash.uti.rest.IpFacadeREST;
/*    */ import gob.regionancash.uti.rest.UtiAtencionFacadeREST;
/*    */ import java.util.Set;
/*    */ import org.isobit.app.rest.CORSFilter;
/*    */ 
/*    */ @ApplicationPath("api")
/*    */ public class ApplicationConfig extends Application {
/*    */   public Set<Class<?>> getClasses() {
/* 11 */     Set<Class<?>> resources = new HashSet<>();
/* 12 */     addRestResourceClasses(resources);
/* 13 */     return resources;
/*    */   }
/*    */   
/*    */   private void addRestResourceClasses(Set<Class<?>> resources) {
/* 17 */     resources.add(DocumentFacadeREST.class);
/* 18 */     resources.add(IncidentFacadeREST.class);
/* 19 */     resources.add(IpFacadeREST.class);
/* 20 */     resources.add(UtiAtencionFacadeREST.class);
/* 21 */     resources.add(CORSFilter.class);
/* 22 */     resources.add(ExceptionHandler.class);
/* 23 */     resources.add(InstallServlet.class);
/*    */   }
/*    */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/rest/ApplicationConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */