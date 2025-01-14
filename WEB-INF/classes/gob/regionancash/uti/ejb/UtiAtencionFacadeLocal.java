package WEB-INF.classes.gob.regionancash.uti.ejb;

import gob.regionancash.uti.jpa.UtiAtencion;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.isobit.util.AbstractFacadeLocal;

@Local
public interface UtiAtencionFacadeLocal extends AbstractFacadeLocal {
  List<UtiAtencion> load(int paramInt1, int paramInt2, String paramString, Map<String, Object> paramMap);
  
  void create(UtiAtencion paramUtiAtencion);
  
  void edit(UtiAtencion paramUtiAtencion);
  
  void remove(UtiAtencion paramUtiAtencion);
  
  UtiAtencion find(Object paramObject);
  
  List<UtiAtencion> findAll();
  
  List<UtiAtencion> findRange(int[] paramArrayOfint);
  
  long count();
  
  void remove(List<UtiAtencion> paramList);
}


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/ejb/UtiAtencionFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */