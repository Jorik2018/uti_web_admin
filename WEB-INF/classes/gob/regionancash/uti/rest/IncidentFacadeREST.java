/*     */ package WEB-INF.classes.gob.regionancash.uti.rest;
/*     */ 
/*     */ import gob.regionancash.uti.ejb.IncidentFacadeLocal;
/*     */ import gob.regionancash.uti.jpa.Incident;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
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
/*     */ import javax.ws.rs.client.ClientBuilder;
/*     */ import org.isobit.app.X;
/*     */ import org.isobit.app.ws.AbstractFacadeREST;
/*     */ 
/*     */ @Stateless
/*     */ @Path("incident")
/*     */ @Consumes({"application/json;charset=UTF-8"})
/*     */ @Produces({"application/json;charset=UTF-8"})
/*     */ public class IncidentFacadeREST extends AbstractFacadeREST<Incident> {
/*     */   @EJB
/*     */   private IncidentFacadeLocal documentFacade;
/*     */   
/*     */   @POST
/*     */   public void create(Incident entity) {
/*  32 */     this.documentFacade.edit(entity);
/*     */   }
/*     */   private static String UPLOAD_DIR;
/*     */   @PUT
/*     */   @Path("{id}")
/*     */   public void edit(@PathParam("id") Integer id, Incident entity) {
/*  38 */     this.documentFacade.edit(entity);
/*     */   }
/*     */   
/*     */   @DELETE
/*     */   @Path("{id}")
/*     */   public void remove(@PathParam("id") Integer id) {
/*  44 */     Incident d = this.documentFacade.find(id);
/*  45 */     d.setCanceled(true);
/*  46 */     remove(d);
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("{id}")
/*     */   public Incident find(@PathParam("id") Integer id) {
/*  52 */     return this.documentFacade.load(id);
/*     */   }
/*     */ 
/*     */   
/*     */   @GET
/*     */   public List<Incident> findAll() {
/*  58 */     return super.findAll();
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
/*     */   @GET
/*     */   @Path("{from}/{to}")
/*     */   public Object findRange(@PathParam("from") Integer from, @PathParam("to") Integer to, @QueryParam("query") String query, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("dependencyId") Integer dependencyId, @QueryParam("all") Integer all) {
/*  72 */     HashMap<Object, Object> m = new HashMap<>();
/*  73 */     m.put("query", query);
/*  74 */     m.put("name", name);
/*  75 */     m.put("description", description);
/*  76 */     m.put("dependencyId", dependencyId);
/*  77 */     m.put("all", all);
/*  78 */     m.put("data", this.documentFacade.load(from.intValue(), to.intValue(), null, m));
/*  79 */     return m;
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
/*     */   @GET
/*     */   @Path("{type}/{year}/{from}/{to}")
/*     */   public Object findRange2(@PathParam("from") Integer from, @PathParam("to") Integer to, @PathParam("type") String type, @PathParam("year") Integer year, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("all") Integer all) {
/*  93 */     HashMap<Object, Object> m = new HashMap<>();
/*  94 */     m.put("type", type);
/*  95 */     m.put("year", year);
/*  96 */     m.put("name", name);
/*  97 */     m.put("all", all);
/*  98 */     m.put("description", description);
/*  99 */     m.put("data", this.documentFacade.load(from.intValue(), to.intValue(), null, m));
/* 100 */     return m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getUploadDir() {
/* 109 */     if (UPLOAD_DIR == null)
/*     */     {
/*     */       
/* 112 */       UPLOAD_DIR = (String)ClientBuilder.newClient().target("http://localhost:" + X.getRequest().getLocalPort() + "/api/system/upload-dir").request().get(String.class);
/*     */     }
/* 114 */     return UPLOAD_DIR;
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("count")
/*     */   @Produces({"text/plain"})
/*     */   public String countREST() {
/* 121 */     return String.valueOf(count());
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/rest/IncidentFacadeREST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */