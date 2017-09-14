package mx.com.desoft.hidrogas.dao;

import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.CatTipoListaRefaccion;

@Repository
public class CatTipoRefaccionesImplDAO extends HibernateImplDAO<CatTipoListaRefaccion, Integer> implements CatTipoRefaccionesDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


}
