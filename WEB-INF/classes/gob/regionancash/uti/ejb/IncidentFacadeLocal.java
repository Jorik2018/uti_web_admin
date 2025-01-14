package WEB-INF.classes.gob.regionancash.uti.ejb;

import gob.regionancash.uti.jpa.Incident;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;
import org.isobit.util.AbstractFacadeLocal;

@Local
public interface IncidentFacadeLocal extends AbstractFacadeLocal {
  List<Incident> load(int paramInt1, int paramInt2, String paramString, Map<String, Object> paramMap);
  
  void create(Incident paramIncident);
  
  void edit(Incident paramIncident);
  
  void remove(Incident paramIncident);
  
  Incident find(Object paramObject);
  
  List<Incident> findAll();
  
  List<Incident> findRange(int[] paramArrayOfint);
  
  long count();
  
  void remove(List<Incident> paramList);
  
  Incident load(Object paramObject);
}


/* Location:              /Users/ealarcop/Projects/java/uti_web_admin.war!/WEB-INF/classes/gob/regionancash/uti/ejb/IncidentFacadeLocal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */