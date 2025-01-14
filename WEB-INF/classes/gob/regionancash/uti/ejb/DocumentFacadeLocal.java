package WEB-INF.classes.gob.regionancash.uti.ejb;

import gob.regionancash.uti.jpa.Document;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.isobit.util.AbstractFacadeLocal;

@Local
public interface DocumentFacadeLocal extends AbstractFacadeLocal {
  List<Document> load(int paramInt1, int paramInt2, String paramString, Map<String, Object> paramMap);
  
  void create(Document paramDocument);
  
  void edit(Document paramDocument);
  
  void remove(Document paramDocument);
  
  Document find(Object paramObject);
  
  List<Document> findAll();
  
  List<Document> findRange(int[] paramArrayOfint);
  
  long count();
  
  void remove(List<Document> paramList);
  
  Document load(Object paramObject);
  
  List getCategoryList();
  
  Object getMainAcronym(Document paramDocument);
  
  List findByUser(Integer paramInteger);
}


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/ejb/DocumentFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */