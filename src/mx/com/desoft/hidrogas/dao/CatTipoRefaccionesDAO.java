package mx.com.desoft.hidrogas.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.CatTipoListaRefaccion;

public interface CatTipoRefaccionesDAO extends HibernateDAO<CatTipoListaRefaccion, Integer>, Serializable {

	List<CatTipoListaRefaccion> getTipoRefaccionByView(CatTipoRefaccionesDTO catTipoRefaccionesDTO);
}
