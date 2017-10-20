package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.CatTipoNecesidad;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class CatTipoNecesidadImplDAO extends HibernateImplDAO<CatTipoNecesidad, Long> implements CatTipoNecesidadDAO {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<CatTipoNecesidad> getTipoNecesidadByView(CatTipoNecesidadDTO catTipoNecesidadDTO) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(CatTipoNecesidad.class);
		if(catTipoNecesidadDTO.getDescripcion() != Constantes.NULL && !catTipoNecesidadDTO.getDescripcion().isEmpty()){
			criteria.add(Restrictions.like("descripcion", catTipoNecesidadDTO.getDescripcion()));
		}
		criteria.addOrder(Order.asc("tipoNecesidadId"));
		return (List<CatTipoNecesidad>) criteria.list();
	}


}
