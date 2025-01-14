package WEB-INF.classes.gob.regionancash.uti.ejb;

import gob.regionancash.uti.jpa.UtiIp;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.isobit.util.AbstractFacadeLocal;

@Local
public interface IpFacadeLocal extends AbstractFacadeLocal {
  List<UtiIp> load(int paramInt1, int paramInt2, String paramString, Map<String, Object> paramMap);
  
  void create(UtiIp paramUtiIp);
  
  void edit(UtiIp paramUtiIp);
  
  void remove(UtiIp paramUtiIp);
  
  UtiIp find(Object paramObject);
  
  List<UtiIp> findAll();
  
  List<UtiIp> findRange(int[] paramArrayOfint);
  
  long count();
  
  void remove(List<UtiIp> paramList);
  
  List getCategoryList();
  
  void disable(List<Integer> paramList);
  
  void limpiar(UtiIp paramUtiIp);
}


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/ejb/IpFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */