package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.dto.RutaDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.hibernate.HibernateUtil;
import mx.com.desoft.hidrogas.model.Ruta;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class RutaImplDAO extends HibernateImplDAO<Ruta, Integer> implements RutaDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Ruta> getRutasByView(RutaDTO rutaDTO) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(Ruta.class);
		if(rutaDTO.getRutaId() != null) {
			criteria.add(Restrictions.eq("rutaId", rutaDTO.getRutaId()));
		}
		criteria.addOrder(Order.asc("rutaId"));
		return (List<Ruta>)criteria.list();
	}

}
