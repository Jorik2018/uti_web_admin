/*     */ package WEB-INF.classes.gob.regionancash.uti.rest;
/*     */ 
/*     */ import gob.regionancash.uti.ejb.IpFacadeLocal;
/*     */ import gob.regionancash.uti.jpa.UtiIp;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.stream.Collectors;
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
/*     */ import javax.ws.rs.client.Entity;
/*     */ import javax.ws.rs.core.Response;
/*     */ import org.isobit.app.X;
/*     */ import org.isobit.app.ejb.UserFacadeLocal;
/*     */ import org.isobit.app.ws.AbstractFacadeREST;
/*     */ import org.isobit.util.XFile;
/*     */ import org.isobit.util.XMap;
/*     */ 
/*     */ @Stateless
/*     */ @Path("ip")
/*     */ @Produces({"application/json;charset=UTF-8"})
/*     */ public class IpFacadeREST
/*     */   extends AbstractFacadeREST<UtiIp> {
/*     */   @EJB
/*     */   private IpFacadeLocal ejbFacade;
/*     */   @EJB
/*     */   private UserFacadeLocal userFacade;
/*     */   
/*     */   @POST
/*     */   @Consumes({"application/json;charset=UTF-8"})
/*     */   public void create(UtiIp entity) {
/*  50 */     this.ejbFacade.edit(entity);
/*     */   }
/*     */   
/*     */   @PUT
/*     */   @Path("{id}")
/*     */   public void edit(@PathParam("id") Integer id, UtiIp entity) {
/*  56 */     this.ejbFacade.edit(entity);
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Path("disable")
/*     */   public void disable(List<Integer> l) {
/*  62 */     this.ejbFacade.disable(l);
/*     */   }
/*     */   
/*     */   @DELETE
/*     */   @Path("{id}")
/*     */   public void remove(@PathParam("id") Integer id) {
/*  68 */     UtiIp a = this.ejbFacade.find(id);
/*  69 */     a.setCanceled(true);
/*  70 */     this.ejbFacade.edit(a);
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Path("{id: \\d+}/finish")
/*     */   public void finish(@PathParam("id") Integer id) {
/*  76 */     UtiIp a = this.ejbFacade.find(id);
/*     */     
/*  78 */     this.ejbFacade.edit(a);
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
/*  90 */     return X.gson.toJson(this.userFacade.getCurrentUser());
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("prepare")
/*     */   public Object prepare() {
/*  96 */     return new UtiIp();
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("{id: \\d+}")
/*     */   public UtiIp find(@PathParam("id") Integer id) {
/* 102 */     return this.ejbFacade.find(id);
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("category")
/*     */   public Object getCategoryList() {
/* 108 */     return this.ejbFacade.getCategoryList().stream().map(s -> {
/*     */           HashMap<Object, Object> m = new HashMap<>();
/*     */           m.put("id", s);
/*     */           m.put("name", s);
/*     */           return m;
/* 113 */         }).collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   @PUT
/*     */   @Path("/limpiar/{id}")
/*     */   public Response limpiarUtiIp(@PathParam("id") Integer entityId) {
/* 119 */     UtiIp entity = this.ejbFacade.find(entityId);
/* 120 */     if (entity == null) {
/* 121 */       return Response.status(Response.Status.NOT_FOUND).build();
/*     */     }
/*     */     
/* 124 */     this.ejbFacade.limpiar(entity);
/*     */     
/* 126 */     return Response.status(Response.Status.OK).build();
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
/*     */   @GET
/*     */   @Path("{from}/{to}")
/*     */   public Object load(@PathParam("from") Integer from, @PathParam("to") Integer to, @QueryParam("category") String category, @QueryParam("code") String code, @QueryParam("name") String name, @QueryParam("position") String position, @QueryParam("device") String device, @QueryParam("macId") String macId, @QueryParam("filter") String filter, @QueryParam("description") String description, @QueryParam("empty") String empty, @QueryParam("siaf") String siaf, @QueryParam("patrimonialCode") String patrimonialCode, @QueryParam("userSiaf") String userSiaf, @QueryParam("SORTER") String SORTER) {
/* 146 */     HashMap<Object, Object> m = new HashMap<>();
/*     */     
/* 148 */     if (category != null) {
/* 149 */       m.put("category", category);
/*     */     }
/* 151 */     if (code != null) {
/* 152 */       m.put("code", code);
/*     */     }
/* 154 */     if (filter != null) {
/* 155 */       m.put("filter", filter);
/*     */     }
/* 157 */     if (name != null) {
/* 158 */       m.put("name", name);
/*     */     }
/* 160 */     if (macId != null) {
/* 161 */       m.put("macId", macId);
/*     */     }
/* 163 */     if (patrimonialCode != null) {
/* 164 */       m.put("patrimonialCode", patrimonialCode);
/*     */     }
/*     */     
/* 167 */     if (position != null) {
/* 168 */       m.put("position", position);
/*     */     }
/* 170 */     if (device != null) {
/* 171 */       m.put("device", device);
/*     */     }
/* 173 */     if (SORTER != null) {
/* 174 */       m.put("SORTER", SORTER);
/*     */     }
/* 176 */     if (description != null) {
/* 177 */       m.put("description", description);
/*     */     }
/* 179 */     if (siaf != null) {
/* 180 */       m.put("siaf", siaf);
/*     */     }
/* 182 */     if (userSiaf != null) {
/* 183 */       m.put("userSiaf", userSiaf);
/*     */     }
/*     */     try {
/* 186 */       m.put("data", this.ejbFacade.load(from.intValue(), to.intValue(), null, m));
/* 187 */     } catch (Exception e) {
/* 188 */       m.put("error", e.toString());
/*     */     } 
/* 190 */     return m;
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Path("count")
/*     */   @Produces({"text/plain"})
/*     */   public String countREST() {
/* 197 */     return String.valueOf(count());
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Path("download")
/*     */   @Produces({"application/octet-stream"})
/*     */   public Response exportFile(Map params) throws IOException {
/* 204 */     String fileName = "data.jao";
/* 205 */     List<Object[]> data = new ArrayList();
/* 206 */     data.add(new Object[] { { "ID", 
/* 207 */             Integer.valueOf(40), Number.class.getName() }, { "CATEGORIA", 
/* 208 */             Integer.valueOf(180), String.class.getName() }, { "CODIGO", 
/* 209 */             Integer.valueOf(180), String.class.getName() }, { "NOMBRE", 
/* 210 */             Integer.valueOf(180), String.class.getName() }, { "CARGO", 
/* 211 */             Integer.valueOf(180), String.class.getName() }, { "ANTIVIRUS", 
/* 212 */             Integer.valueOf(200), String.class.getName() }, { "DESCRIPCION", 
/* 213 */             Integer.valueOf(200), String.class.getName() }, { "RESTRICCION", 
/* 214 */             Integer.valueOf(200), String.class.getName() }, { "MACID", 
/* 215 */             Integer.valueOf(200), String.class.getName() }, { "GRUPO TRABAJO", 
/* 216 */             Integer.valueOf(200), String.class.getName() }, { "EQUIPO", 
/* 217 */             Integer.valueOf(200), String.class.getName() }, { "CODIGO PATRIMONIAL", 
/* 218 */             Integer.valueOf(200), String.class.getName() }, { "USUARIO", 
/* 219 */             Integer.valueOf(200), Number.class.getName() }, { "FECHA CREACION", 
/* 220 */             Integer.valueOf(200), Date.class.getName() }, { "FECHA ACTUALIZACION", 
/* 221 */             Integer.valueOf(200), Date.class.getName() }, { "ESTABILIZADOR CODIGO PATRIMONIAL", 
/* 222 */             Integer.valueOf(200), String.class.getName() }, { "ESTABILIZADOR DESCRIPCION", 
/* 223 */             Integer.valueOf(200), String.class.getName() }, { "IMPRESORA CODIGO PATRIMONIAL", 
/* 224 */             Integer.valueOf(200), String.class.getName() }, { "IMPRESORA MODELO", 
/* 225 */             Integer.valueOf(200), String.class.getName() }, { "IMPRESORA SERIE", 
/* 226 */             Integer.valueOf(200), String.class.getName() }, { "IMPRESORA DESCRIPCION", 
/* 227 */             Integer.valueOf(200), String.class.getName() } });
/*     */     
/* 229 */     List<UtiIp> hm = this.ejbFacade.load(0, 0, null, (Map)new XMap(new Object[] { "anulado", Boolean.valueOf(false), "withStore", Boolean.valueOf(true) }));
/* 230 */     for (UtiIp u : hm) {
/*     */       
/* 232 */       data.add(new Object[] { u
/* 233 */             .getId(), u
/* 234 */             .getCategory(), u
/* 235 */             .getCode(), u
/* 236 */             .getName(), u
/* 237 */             .getPosition(), u
/* 238 */             .getAntivirus(), u
/* 239 */             .getDescription(), u
/* 240 */             .getRestriction(), u
/* 241 */             .getMacId(), u
/* 242 */             .getWorkgroup(), u
/* 243 */             .getDevice(), u
/* 244 */             .getPatrimonialCode(), u
/* 245 */             .getUser(), u
/* 246 */             .getCreation(), u
/* 247 */             .getUpdated(), u
/* 248 */             .getStabilizerCode(), u
/* 249 */             .getStabilizerDescription(), u
/* 250 */             .getPrinterCode(), u
/* 251 */             .getPrinterModel(), u
/* 252 */             .getPrinterSerie(), u
/* 253 */             .getPrinterDescription() });
/*     */     } 
/*     */ 
/*     */     
/* 257 */     XFile.saveObject((new File(File.createTempFile("temp-file-name", ".tmp").getParentFile(), fileName)).getAbsolutePath(), data, new Object[0]);
/*     */ 
/*     */ 
/*     */     
/* 261 */     InputStream is2 = (InputStream)ClientBuilder.newClient().target("http://localhost:" + X.getRequest().getLocalPort() + "/xls/api/export/" + fileName).request().post(Entity.text(""), InputStream.class);
/* 262 */     fileName = "repor.xlsx";
/* 263 */     return Response.ok(output -> {
/*     */           try {
/*     */             ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*     */             byte[] buffer = new byte[4096];
/*     */             int len;
/*     */             while ((len = is2.read(buffer, 0, buffer.length)) != -1) {
/*     */               output.write(buffer, 0, len);
/*     */             }
/*     */             output.flush();
/*     */             is2.close();
/* 273 */           } catch (IOException e) {
/*     */             
/*     */             throw new WebApplicationException("File Not Found !!", e);
/*     */           } 
/* 277 */         }"application/octet-stream").header("content-disposition", "attachment; filename = " + fileName)
/* 278 */       .build();
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/rest/IpFacadeREST.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */