package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.CatTipoRefaccion;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class CatTipoRefaccionImplDAO extends HibernateImplDAO<CatTipoRefaccion, Integer> implements ICatTipoRefaccionDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<CatTipoRefaccion> getTipoRefaccionByView(CatTipoRefaccionesDTO catTipoRefaccionesDTO) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(CatTipoRefaccion.class);
		if(catTipoRefaccionesDTO.getDescripcion() != Constantes.NULL && !catTipoRefaccionesDTO.getDescripcion().isEmpty()){
			criteria.add(Restrictions.like("descripcion", catTipoRefaccionesDTO.getDescripcion()));
		}
		criteria.addOrder(Order.asc("idTipoRefaccion"));
		return (List<CatTipoRefaccion>) criteria.list();
	}

}
