package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class EconomicoImplDAO extends HibernateImplDAO<Economico, Integer> implements EconomicoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Economico> getEconomicosByView(EconomicoDTO economicoDTO) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(Economico.class);
		if(economicoDTO.getEconomicoId() != Constantes.NULL) {
			criteria.add(Restrictions.eq("economicoId", economicoDTO.getEconomicoId()));
		}

		if(economicoDTO.getFechaRegistro() != Constantes.NULL) {
			criteria.add(Restrictions.eq("fechaRegistro", economicoDTO.getFechaRegistro()));
		}
		criteria.addOrder(Order.asc("economicoId"));
		return (List<Economico>)criteria.list();
	}

}
