package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.model.Empleado;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class EconomicoImplDAO extends HibernateImplDAO<Economico, Integer> implements EconomicoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> getEconomicosByView(EconomicoDTO economicoDTO) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(Empleado.class);
		if(economicoDTO.getEconomicoId() != Constantes.NULL) {
			criteria.createAlias("economico", "economico");
			criteria.add(Restrictions.eq("economico.economicoId", economicoDTO.getEconomicoId()));
		}
		
		if(economicoDTO.getNombreChofer() != Constantes.NULL && !economicoDTO.getNombreChofer().isEmpty()) {
			criteria.add(Restrictions.like("nombreEmpleado", economicoDTO.getNombreChofer()));
		}
		
		criteria.createAlias("catTipoEmpleado", "catTipoEmpleado");
		criteria.add(Restrictions.eq("catTipoEmpleado.descripcion", Constantes.CHOFER));
		return (List<Empleado>)criteria.list();
	}

}
