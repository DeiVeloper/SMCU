package mx.com.desoft.hidrogas.dao;

import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.CatTipoRefaccion;

public interface ICatTipoRefaccionDAO extends HibernateDAO<CatTipoRefaccion, Integer> {

	List<CatTipoRefaccion> getTipoRefaccionByView(CatTipoRefaccionesDTO catTipoRefaccionesDTO);
}
