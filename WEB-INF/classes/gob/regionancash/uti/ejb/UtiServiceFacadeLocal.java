package WEB-INF.classes.gob.regionancash.uti.ejb;

import gob.regionancash.uti.jpa.UtiService;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.isobit.util.AbstractFacadeLocal;

@Local
public interface UtiServiceFacadeLocal extends AbstractFacadeLocal {
  List<UtiService> load(int paramInt1, int paramInt2, String paramString, Map<String, Object> paramMap);
  
  void create(UtiService paramUtiService);
  
  void edit(UtiService paramUtiService);
  
  void remove(UtiService paramUtiService);
  
  UtiService find(Object paramObject);
  
  List<UtiService> findAll();
  
  List<UtiService> findRange(int[] paramArrayOfint);
  
  long count();
  
  void remove(List<UtiService> paramList);
  
  UtiService load(Object paramObject);
}


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/ejb/UtiServiceFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */