/*     */ package WEB-INF.classes.gob.regionancash.uti.rest;
/*     */ 
/*     */ import gob.regionancash.uti.ejb.UtiAtencionFacadeLocal;
/*     */ import gob.regionancash.uti.jpa.UtiAtencion;
/*     */ import java.util.HashMap;
/*     */ import javax.ejb.EJB;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.ws.rs.Consumes;
/*     */ import javax.ws.rs.DELETE;
/*     */ import javax.ws.rs.GET;
/*     */ import javax.ws.rs.POST;
/*     */ import javax.ws.rs.PUT;
/*     */ import javax.ws.rs.Path;
/*     */ import javax.ws.rs.PathParam;
/*     */ import javax.ws.rs.Produces;
/*     */ import javax.ws.rs.QueryParam;
/*     */ import org.isobit.app.X;
/*     */ import org.isobit.app.ejb.UserFacadeLocal;
/*     */ import org.isobit.app.ws.AbstractFacadeREST;
/*     */ 
/*     */ @Stateless
/*     */ @Path("attention")
/*     */ public class UtiAtencionFacadeREST extends AbstractFacadeREST<UtiAtencion> {
/*     */   @EJB
/*     */   private UtiAtencionFacadeLocal ejbFacade;
/*     */   @EJB
/*     */   private UserFacadeLocal userFacade;
/*     */   
/*     */   @POST
/*     */   @Consumes({"application/json;charset=UTF-8"})
/*     */   public void create(UtiAtencion entity) {
/*  32 */     this.ejbFacade.edit(entity);
/*     */   }
/*     */   
/*     */   @PUT
/*     */   @Path("{id}")
/*     */   @Consumes({"application/json;charset=UTF-8"})
/*     */   public void edit(@PathParam("id") Integer id, UtiAtencion entity) {
/*  39 */     this.ejbFacade.edit(entity);
/*     */   }
/*     */   
/*     */   @DELETE
/*     */   @Path("{id}")
/*     */   public void remove(@PathParam("id") Integer id) {
/*  45 */     UtiAtencion a = this.ejbFacade.find(id);
/*  46 */     a.setCanceled(true);
/*  47 */     this.ejbFacade.edit(a);
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Path("{id: \\d+}/finish")
/*     */   public void finish(@PathParam("id") Integer id) {
/*  53 */     UtiAtencion a = this.ejbFacade.find(id);
/*  54 */     a.setEndDate(X.getServerDate());
/*  55 */     this.ejbFacade.edit(a);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GET
/*     */   @Path("user")
/*     */   @Produces({"text/plain"})
/*     */   public Object user() {
/*  67 */     return X.gson.toJson(this.userFacade.getCurrentUser());
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("prepare")
/*     */   @Produces({"application/json;charset=UTF-8"})
/*     */   public Object prepare() {
/*  74 */     return new UtiAtencion();
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("{id: \\d+}")
/*     */   @Produces({"application/json;charset=UTF-8"})
/*     */   public UtiAtencion find(@PathParam("id") Integer id) {
/*  81 */     return this.ejbFacade.find(id);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @GET
/*     */   @Path("{from}/{to}")
/*     */   @Produces({"application/json;charset=UTF-8"})
/*     */   public Object load(@PathParam("from") Integer from, @PathParam("to") Integer to, @QueryParam("description") String description, @QueryParam("dependency") String dependency, @QueryParam("user") Integer user, @QueryParam("empty") String empty) {
/*  91 */     HashMap<Object, Object> m = new HashMap<>();
/*  92 */     if (user != null) {
/*  93 */       m.put("user", user);
/*     */     }
/*  95 */     if (description != null) {
/*  96 */       m.put("description", description);
/*     */     }
/*  98 */     if (dependency != null) {
/*  99 */       m.put("dependency", dependency);
/*     */     }
/* 101 */     if (empty != null) {
/* 102 */       m.put("empty", empty);
/*     */     }
/*     */     try {
/* 105 */       m.put("data", this.ejbFacade.load(from.intValue(), to.intValue(), null, m));
/* 106 */     } catch (Exception e) {
/* 107 */       m.put("error", e.toString());
/*     */     } 
/* 109 */     return m;
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("count")
/*     */   @Produces({"text/plain"})
/*     */   public String countREST() {
/* 116 */     return String.valueOf(count());
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/rest/UtiAtencionFacadeREST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */