/*     */ package WEB-INF.classes.gob.regionancash.uti.rest;
/*     */ 
/*     */ import gob.regionancash.uti.ejb.DocumentFacadeLocal;
/*     */ import gob.regionancash.uti.jpa.Document;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ import javax.ws.rs.WebApplicationException;
/*     */ import javax.ws.rs.client.ClientBuilder;
/*     */ import javax.ws.rs.core.Response;
/*     */ import org.isobit.app.X;
/*     */ import org.isobit.app.ws.AbstractFacadeREST;
/*     */ import org.isobit.directory.jpa.Dependency;
/*     */ import org.isobit.util.XFile;
/*     */ import org.isobit.util.XUtil;
/*     */ 
/*     */ @Stateless
/*     */ @Path("document")
/*     */ @Consumes({"application/json;charset=UTF-8"})
/*     */ @Produces({"application/json;charset=UTF-8"})
/*     */ public class DocumentFacadeREST extends AbstractFacadeREST<Document> {
/*     */   @EJB
/*     */   private DocumentFacadeLocal documentFacade;
/*     */   
/*     */   @POST
/*     */   public Object create2(Document entity) {
/*  44 */     this.documentFacade.edit(entity);
/*  45 */     return entity;
/*     */   }
/*     */   private static String UPLOAD_DIR;
/*     */   @PUT
/*     */   @Path("{id}")
/*     */   public void edit(@PathParam("id") Integer id, Document entity) {
/*  51 */     this.documentFacade.edit(entity);
/*     */   }
/*     */   
/*     */   @DELETE
/*     */   @Path("{id}")
/*     */   public void remove(@PathParam("id") Integer id) {
/*  57 */     Document d = this.documentFacade.find(id);
/*  58 */     d.setCanceled(true);
/*  59 */     remove(d);
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("{id}")
/*     */   public Document find(@PathParam("id") Integer id) {
/*  65 */     return this.documentFacade.load(id);
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("dependency/{uid}")
/*     */   public List findByUser(@PathParam("uid") Integer id) {
/*  71 */     return this.documentFacade.findByUser(id);
/*     */   }
/*     */ 
/*     */   
/*     */   @GET
/*     */   public List<Document> findAll() {
/*  77 */     return super.findAll();
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
/*     */   @GET
/*     */   @Path("{from}/{to}")
/*     */   public Object findRange(@PathParam("from") Integer from, @PathParam("to") Integer to, @QueryParam("query") String query, @QueryParam("name") String name, @QueryParam("description") String description, @QueryParam("dependencyId") Integer dependencyId, @QueryParam("documentTypeId") String documentTypeId, @QueryParam("all") Integer all) {
/*  92 */     HashMap<Object, Object> m = new HashMap<>();
/*  93 */     m.put("query", query);
/*  94 */     m.put("name", name);
/*  95 */     m.put("description", description);
/*  96 */     m.put("dependencyId", dependencyId);
/*  97 */     m.put("all", all);
/*     */     
/*  99 */     if (documentTypeId != null) {
/* 100 */       m.put("documentTypeId", toList(documentTypeId, o -> o));
/*     */     }
/*     */     
/* 103 */     m.put("data", this.documentFacade.load(from.intValue(), to.intValue(), null, m));
/* 104 */     return m;
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
/* 118 */     HashMap<Object, Object> m = new HashMap<>();
/* 119 */     m.put("type", type);
/* 120 */     m.put("year", year);
/* 121 */     m.put("name", name);
/* 122 */     m.put("all", all);
/* 123 */     m.put("description", description);
/* 124 */     m.put("data", this.documentFacade.load(from.intValue(), to.intValue(), null, m));
/* 125 */     return m;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getUploadDir() {
/* 134 */     if (UPLOAD_DIR == null)
/*     */     {
/*     */       
/* 137 */       UPLOAD_DIR = (String)ClientBuilder.newClient().target("http://localhost:" + X.getRequest().getLocalPort() + "/api/system/upload-dir").request().get(String.class);
/*     */     }
/* 139 */     return UPLOAD_DIR;
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("count")
/*     */   @Produces({"text/plain"})
/*     */   public String countREST() {
/* 146 */     return String.valueOf(count());
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("category")
/*     */   public List getCategoryList() {
/* 152 */     return this.documentFacade.getCategoryList();
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Path("find")
/*     */   public Object exportFile2(Map params) throws IOException {
/* 158 */     String[] code = params.get("code").toString().split("\\.");
/* 159 */     String documentTypeId = (String)params.get("documentTypeId");
/* 160 */     int code_ = XUtil.intValue(code[0]);
/* 161 */     String clave = code[1];
/* 162 */     Document d = this.documentFacade.load(Integer.valueOf(code_));
/* 163 */     if (!clave.equals(d.getCode()) || d.getDocumentTypeId() == null || 
/* 164 */       !d.getDocumentTypeId().equals(documentTypeId)) {
/* 165 */       throw new WebApplicationException("Clave no valida!!");
/*     */     }
/* 167 */     return d;
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("download/{code}")
/*     */   public Response exportFile(@PathParam("code") String ccode) throws IOException {
/* 173 */     String[] code = ccode.split("\\.");
/* 174 */     int id = XUtil.intValue(code[0]);
/* 175 */     String clave = code[1];
/* 176 */     Document doc = this.documentFacade.load(Integer.valueOf(id));
/* 177 */     if (doc == null || (!XUtil.isEmpty(doc.getCode()) && !clave.equals(doc.getCode()))) {
/* 178 */       throw new RuntimeException("Clave no valida!!");
/*     */     }
/* 180 */     Dependency dependency = doc.getDependency();
/* 181 */     String dep = null;
/* 182 */     String fileName = XFile.simplifyFileName(doc.getName());
/* 183 */     fileName = fileName.replace("/", "-").toUpperCase();
/* 184 */     if (dependency != null) {
/* 185 */       dep = (String)XUtil.isEmpty(dependency.getAcronym(), dependency.getFullName());
/*     */     }
/* 187 */     if (dep == null && doc.getDocumentTypeId() == null && !doc.isMain()) {
/* 188 */       dep = (String)this.documentFacade.getMainAcronym(doc);
/*     */     }
/*     */ 
/*     */     
/* 192 */     File folder = new File(getUploadDir() + "/document/" + doc.getYear() + ((dep != null) ? ("/" + dep) : "") + "/" + doc.getDocumentTypeId());
/* 193 */     File initialFile = new File(folder.getCanonicalPath(), fileName + "." + doc.getExtension());
/* 194 */     if (!initialFile.exists()) {
/*     */       
/* 196 */       folder = new File(getUploadDir() + "/document/" + doc.getYear() + "/" + ((dep != null) ? ("/" + dep) : ""));
/* 197 */       initialFile = new File(folder.getCanonicalPath(), fileName + "." + doc.getExtension());
/*     */     } 
/* 199 */     if (!initialFile.exists()) {
/*     */       
/* 201 */       folder = new File(getUploadDir() + "/document/" + doc.getYear() + "/" + doc.getDocumentTypeId());
/* 202 */       initialFile = new File(folder.getCanonicalPath(), fileName + "." + doc.getExtension());
/* 203 */       if (!initialFile.exists()) {
/* 204 */         throw new RuntimeException("El archivo '" + initialFile + "' no existe");
/*     */       }
/*     */     } 
/*     */     
/* 208 */     InputStream is2 = new FileInputStream(initialFile);
/* 209 */     return Response.ok(output -> {
/*     */           try {
/*     */             ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */             
/*     */             byte[] buffer = new byte[4096];
/*     */             
/*     */             int len;
/*     */             while ((len = is2.read(buffer, 0, buffer.length)) != -1) {
/*     */               output.write(buffer, 0, len);
/*     */             }
/*     */             output.flush();
/*     */             is2.close();
/* 221 */           } catch (IOException e) {
/*     */             
/*     */             throw new WebApplicationException("File Not Found !!", e);
/*     */           } 
/* 225 */         }"application/octet-stream").header("content-disposition", "attachment; filename = " + fileName + "." + doc.getExtension())
/* 226 */       .build();
/*     */   }
/*     */ 
/*     */   
/*     */   @POST
/*     */   @Path("download")
/*     */   @Produces({"application/octet-stream"})
/*     */   public Response exportFile(Map params) throws IOException {
/* 234 */     return exportFile(params.get("code").toString());
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/rest/DocumentFacadeREST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */