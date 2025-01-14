/*     */ package WEB-INF.classes.gob.regionancash.uti.ejb;
/*     */ 
/*     */ import gob.regionancash.uti.ejb.DocumentFacadeLocal;
/*     */ import gob.regionancash.uti.jpa.Document;
/*     */ import gob.regionancash.uti.jpa.DocumentCategory;
/*     */ import gob.regionancash.uti.jpa.DocumentRef;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.ejb.EJB;
/*     */ import javax.ejb.Stateless;
/*     */ import javax.persistence.EntityManager;
/*     */ import javax.persistence.NoResultException;
/*     */ import javax.persistence.Query;
/*     */ import javax.ws.rs.client.ClientBuilder;
/*     */ import org.isobit.app.X;
/*     */ import org.isobit.app.ejb.UserFacadeLocal;
/*     */ import org.isobit.app.jpa.User;
/*     */ import org.isobit.directory.jpa.Dependency;
/*     */ import org.isobit.util.AbstractFacade;
/*     */ import org.isobit.util.RandomUtil;
/*     */ import org.isobit.util.XFile;
/*     */ import org.isobit.util.XUtil;
/*     */ 
/*     */ @Stateless
/*     */ public class DocumentFacade extends AbstractFacade<Document> implements DocumentFacadeLocal {
/*     */   @EJB
/*     */   private UserFacadeLocal userFacade;
/*     */   private static String UPLOAD_DIR;
/*     */   
/*     */   public List<Document> load(int first, int pageSize, String sortField, Map<String, Object> filters) {
/*  35 */     Object type = XUtil.isEmpty(filters.get("type"), null);
/*  36 */     Object year = XUtil.isEmpty(filters.get("year"), null);
/*  37 */     Object all = XUtil.isEmpty(filters.get("all"), null);
/*  38 */     Object dependencyId = XUtil.isEmpty(filters.get("dependencyId"), null);
/*  39 */     List<Object> dependencyList = new ArrayList();
/*  40 */     EntityManager em = getEntityManager();
/*  41 */     User u = this.userFacade.getCurrentUser();
/*  42 */     if (dependencyId != null) {
/*  43 */       dependencyList.add(dependencyId);
/*     */     }
/*     */ 
/*     */     
/*  47 */     if (all == null && !this.userFacade.access("ADMIN_UTI_DOCUMENT")) {
/*  48 */       dependencyList.add(Integer.valueOf(0));
/*  49 */       dependencyList.addAll(em.createQuery("SELECT c.dependencyId FROM Contract c WHERE c.canceled=0 AND c.active=1 AND c.peopleId=:people")
/*  50 */           .setParameter("people", u.getIdDir()).getResultList());
/*  51 */       dependencyList.addAll(em.createNativeQuery("SELECT c.dependency_Id FROM dru_user_location c WHERE c.uid=:uid")
/*  52 */           .setParameter("uid", u.getUid()).getResultList());
/*     */     } 
/*  54 */     System.out.println("dependencyList=" + dependencyList);
/*  55 */     Object query = XUtil.isEmpty(filters.get("query"), null);
/*     */     
/*  57 */     Object documentTypeId = XUtil.isEmpty(filters.get("documentTypeId"), null);
/*     */     
/*  59 */     Object name = XUtil.isEmpty(filters.get("name"), null);
/*  60 */     if (query != null) {
/*  61 */       name = query;
/*     */     }
/*  63 */     Object description = XUtil.isEmpty(filters.get("description"), null);
/*  64 */     List<Query> ql = new ArrayList<>();
/*     */     String sql;
/*  66 */     ql.add(em.createQuery("SELECT o " + (
/*  67 */           sql = "FROM Document o WHERE o.canceled=0 AND o.main=1 " + (!dependencyList.isEmpty() ? " AND o.dependencyId IN :dependencyId" : "") + ((type != null) ? " AND o.documentTypeId=:type" : "") + ((documentTypeId != null) ? " AND o.documentTypeId IN :documentTypeId" : "") + ((year != null) ? " AND o.year=:year" : "") + ((name != null) ? " AND UPPER(o.name) LIKE :name" : "") + ((description != null) ? " AND UPPER(o.description) LIKE :description" : "") + "  ORDER BY o.id DESC")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     if (pageSize > 0) {
/*  76 */       ((Query)ql.get(0)).setFirstResult(first).setMaxResults(pageSize);
/*  77 */       ql.add(em.createQuery("SELECT COUNT(o) " + sql));
/*     */     } 
/*  79 */     for (Query q : ql) {
/*  80 */       if (type != null) {
/*  81 */         q.setParameter("type", type);
/*     */       }
/*  83 */       if (year != null) {
/*  84 */         q.setParameter("year", Integer.valueOf(XUtil.intValue(year)));
/*     */       }
/*  86 */       if (name != null) {
/*  87 */         q.setParameter("name", "%" + name.toString().replaceAll("\\s+", "%").toUpperCase() + "%");
/*     */       }
/*     */       
/*  90 */       if (!dependencyList.isEmpty()) {
/*  91 */         q.setParameter("dependencyId", dependencyList);
/*     */       }
/*  93 */       if (documentTypeId != null) {
/*  94 */         q.setParameter("documentTypeId", documentTypeId);
/*     */       }
/*  96 */       if (description != null) {
/*  97 */         q.setParameter("description", "%" + description.toString().replaceAll("\\s+", "%").toUpperCase() + "%");
/*     */       }
/*     */     } 
/* 100 */     if (pageSize > 0) {
/* 101 */       filters.put("size", ((Query)ql.get(1)).getSingleResult());
/*     */     }
/* 103 */     List<Document> data = ((Query)ql.get(0)).getResultList();
/* 104 */     for (Document s : data) {
/* 105 */       if (s.getCategoryId() != null) {
/* 106 */         DocumentCategory documentCategory = (DocumentCategory)em.find(DocumentCategory.class, s.getCategoryId());
/* 107 */         if (documentCategory != null) {
/* 108 */           s.setCategory(documentCategory.getName());
/*     */         }
/*     */       } 
/*     */     } 
/* 112 */     return data;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private String getUploadDir() {
/* 118 */     if (UPLOAD_DIR == null)
/*     */     {
/*     */       
/* 121 */       UPLOAD_DIR = (String)ClientBuilder.newClient().target("http://localhost:" + X.getRequest().getLocalPort() + "/api/system/upload-dir").request().get(String.class);
/*     */     }
/* 123 */     return UPLOAD_DIR;
/*     */   }
/*     */ 
/*     */   
/*     */   public void edit(Document entity) {
/* 128 */     User user = this.userFacade.getCurrentUser();
/* 129 */     if (entity.getCreatedBy() == null) {
/* 130 */       entity.setCreatedBy(user.getIdDir());
/*     */     }
/* 132 */     if (entity.getCreatedDate() == null) {
/* 133 */       entity.setCreatedDate(X.getServerDate());
/*     */     }
/*     */     
/* 136 */     EntityManager em = getEntityManager();
/* 137 */     Map ext = (Map)entity.getExt();
/* 138 */     String tempFile = (String)ext.get("tempFile");
/* 139 */     if (entity.getDependency() != null) {
/* 140 */       entity.setDependencyId(entity.getDependency().getId());
/*     */     }
/* 142 */     if (entity.getId() == null) {
/* 143 */       if (!"dabi".equalsIgnoreCase(entity.getDocumentTypeId())) {
/* 144 */         entity.setStatus(Character.valueOf('R'));
/*     */       }
/* 146 */       entity.setMain(true);
/* 147 */       entity.setCode(RandomUtil.getCode(8));
/* 148 */       create(entity);
/*     */     } else {
/* 150 */       entity.setUpdatedDate(X.getServerDate());
/* 151 */       entity.setUpdatedBy(user.getIdDir());
/*     */     } 
/* 153 */     if (!XUtil.isEmpty(tempFile)) {
/*     */       try {
/* 155 */         String fileName = XFile.simplifyFileName(entity.getName());
/* 156 */         File f = new File(File.createTempFile("temp-file-name", ".tmp").getParentFile(), tempFile);
/* 157 */         String dep = null;
/* 158 */         fileName = fileName.replace("/", "-").toUpperCase();
/* 159 */         Dependency dependency = entity.getDependency();
/* 160 */         if (dependency != null) {
/* 161 */           dep = (String)XUtil.isEmpty(dependency.getAcronym(), null);
/*     */         }
/* 163 */         File dest = XFile.getFile(getUploadDir() + "/document/" + entity.getYear() + ((dep != null) ? ("/" + dep) : ("/" + entity.getDocumentTypeId())) + "/");
/* 164 */         if (!dest.exists()) {
/* 165 */           throw new RuntimeException("El directorio '" + dest + "' no existe");
/*     */         }
/* 167 */         dest = new File(dest.getCanonicalPath(), fileName + "." + XFile.getFileExtension(f));
/* 168 */         if (XFile.copy(f, dest) == null) {
/* 169 */           throw new RuntimeException("El documento no pudo ser grabado");
/*     */         }
/* 171 */         entity.setExtension(XFile.getFileExtension(f));
/*     */       }
/* 173 */       catch (IOException iOException) {}
/*     */     }
/*     */     
/* 176 */     super.edit(entity);
/* 177 */     for (Object reference : entity.getReferences()) {
/* 178 */       Document document; Map<String, Object> ref = (Map)reference;
/*     */       
/* 180 */       boolean referenced = false;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 185 */       if (ref.containsKey("referenceId")) {
/* 186 */         document = (Document)em.find(Document.class, Integer.valueOf(XUtil.intValue(ref.get("referenceId"))));
/* 187 */         referenced = true;
/*     */       } else {
/* 189 */         document = new Document();
/* 190 */         document.setDependencyId(entity.getDependencyId());
/* 191 */         document.setCreatedBy(user.getIdDir());
/* 192 */         document.setCreatedDate(X.getServerDate());
/* 193 */         document.setYear(entity.getYear());
/*     */       } 
/* 195 */       document.setDocumentTypeId((String)ref.get("documentTypeId"));
/* 196 */       if (XUtil.isEmpty(document.getDocumentTypeId())) {
/* 197 */         document.setDocumentTypeId(entity.getDocumentTypeId());
/*     */       }
/* 199 */       if (ref.containsKey("fileName")) {
/* 200 */         document.setName((String)ref.get("fileName"));
/*     */       }
/* 202 */       if (document.getId() == null) {
/*     */         
/* 204 */         create(document);
/*     */       
/*     */       }
/* 207 */       else if (!referenced) {
/* 208 */         document.setUpdatedDate(X.getServerDate());
/* 209 */         document.setUpdatedBy(user.getIdDir());
/*     */       } 
/* 211 */       if (!ref.containsKey("id")) {
/* 212 */         DocumentRef documentRef = new DocumentRef();
/* 213 */         documentRef.setDocumentId(entity.getId());
/* 214 */         documentRef.setReferenceId(document.getId());
/* 215 */         em.persist(documentRef);
/* 216 */       } else if (XUtil.intValue(ref.get("canceled")) > 0) {
/* 217 */         DocumentRef documentRef = (DocumentRef)em.find(DocumentRef.class, Integer.valueOf(XUtil.intValue(ref.get("id"))));
/* 218 */         em.remove(documentRef);
/*     */         continue;
/*     */       } 
/* 221 */       tempFile = (String)ref.get("tempFile");
/* 222 */       if (tempFile != null) {
/*     */         
/*     */         try {
/* 225 */           String fileName = XFile.simplifyFileName(document.getName());
/* 226 */           File f = new File(File.createTempFile("temp-file-name", ".tmp").getParentFile(), tempFile);
/*     */           
/* 228 */           String dep = null;
/* 229 */           fileName = fileName.replace("/", "-").toUpperCase();
/* 230 */           Dependency dependency = (Dependency)em.find(Dependency.class, document.getDependencyId());
/* 231 */           if (dependency != null) {
/* 232 */             dep = (String)XUtil.isEmpty(dependency.getAcronym(), dependency.getFullName());
/*     */           }
/* 234 */           ref.put("type", reference);
/* 235 */           File dest = XFile.getFile(getUploadDir() + "/document/" + entity.getYear() + ((dep != null) ? ("/" + dep) : "") + "/" + document.getDocumentTypeId());
/* 236 */           if (!dest.exists()) {
/* 237 */             throw new RuntimeException("El directorio '" + dest + "' no existe");
/*     */           }
/* 239 */           dest = new File(dest.getCanonicalPath(), fileName + "." + XFile.getFileExtension(f));
/* 240 */           if (XFile.copy(f, dest) == null) {
/* 241 */             throw new RuntimeException("El documento no pudo ser grabado");
/*     */           }
/* 243 */           document.setExtension(XFile.getFileExtension(f));
/*     */         }
/* 245 */         catch (IOException iOException) {}
/*     */       }
/*     */       
/* 248 */       super.edit(document);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Object getMainAcronym(Document child) {
/* 254 */     EntityManager em = getEntityManager();
/* 255 */     return em.createQuery("SELECT de.acronym FROM DocumentRef dr,Document d,Dependency de WHERE de.id=d.dependencyId AND d.id=dr.documentId AND dr.referenceId=:referenceId")
/* 256 */       .setParameter("referenceId", child.getId()).getSingleResult();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Document load(Object id) {
/* 262 */     EntityManager em = getEntityManager();
/* 263 */     Document s = (Document)find((id instanceof Document) ? ((Document)id).getId() : id);
/* 264 */     if (s != null) {
/* 265 */       if (s.getCategoryId() != null) {
/* 266 */         DocumentCategory documentCategory = (DocumentCategory)em.find(DocumentCategory.class, s.getCategoryId());
/* 267 */         if (documentCategory != null) {
/* 268 */           s.setCategory(documentCategory.getName());
/*     */         }
/*     */       } 
/* 271 */       HashMap<Object, Object> m = new HashMap<>();
/* 272 */       s.setReferences(em.createQuery("SELECT d FROM DocumentRef d WHERE d.documentId=:document")
/* 273 */           .setParameter("document", s.getId())
/* 274 */           .getResultList());
/* 275 */       if (s.getDependencyId() != null) {
/* 276 */         s.setDependency((Dependency)em.find(Dependency.class, s.getDependencyId()));
/*     */       }
/*     */       try {
/* 279 */         s.setDocumentTypeName((String)em.createQuery("SELECT dt.name FROM DocumentType dt WHERE dt.code=:code")
/* 280 */             .setParameter("code", s.getDocumentTypeId()).getSingleResult());
/* 281 */       } catch (NoResultException noResultException) {}
/*     */       
/* 283 */       s.setExt(m);
/*     */     } 
/* 285 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public List getCategoryList() {
/* 290 */     return getEntityManager().createQuery("SELECT o FROM DocumentCategory o ORDER BY o.name").getResultList();
/*     */   }
/*     */ 
/*     */   
/*     */   public List findByUser(Integer id) {
/* 295 */     EntityManager em = getEntityManager();
/* 296 */     List<Integer> dependencyList = new ArrayList();
/* 297 */     dependencyList.add(Integer.valueOf(0));
/* 298 */     User u = (User)em.find(User.class, id);
/* 299 */     if (u.getIdDir() != null) {
/* 300 */       dependencyList.addAll(em.createQuery("SELECT c.dependencyId FROM Contract c WHERE c.canceled=0 AND c.active=1 AND c.peopleId=:people")
/* 301 */           .setParameter("people", u.getIdDir()).getResultList());
/*     */     }
/* 303 */     dependencyList.addAll(em.createNativeQuery("SELECT c.dependency_Id FROM dru_user_location c WHERE c.uid=:uid")
/* 304 */         .setParameter("uid", u.getUid()).getResultList());
/* 305 */     return em.createQuery("SELECT d FROM Dependency d WHERE d.id IN :ids")
/* 306 */       .setParameter("ids", dependencyList)
/* 307 */       .getResultList();
/*     */   }
/*     */ }


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/ejb/DocumentFacade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */