package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.CatTipoListaRefaccion;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class CatTipoRefaccionesImplDAO extends HibernateImplDAO<CatTipoListaRefaccion, Integer> implements CatTipoRefaccionesDAO {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<CatTipoListaRefaccion> getTipoRefaccionByView(CatTipoRefaccionesDTO catTipoRefaccionesDTO) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(CatTipoListaRefaccion.class);
		if(catTipoRefaccionesDTO.getDescripcion() != Constantes.NULL && !catTipoRefaccionesDTO.getDescripcion().isEmpty()){
			criteria.add(Restrictions.like("descripcion", catTipoRefaccionesDTO.getDescripcion()));
		}
		criteria.addOrder(Order.asc("tipoRefaccionId"));
		return (List<CatTipoListaRefaccion>) criteria.list();
	}
}