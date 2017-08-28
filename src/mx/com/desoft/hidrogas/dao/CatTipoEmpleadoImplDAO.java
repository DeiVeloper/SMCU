package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.CatTipoEmpleado;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class CatTipoEmpleadoImplDAO extends HibernateImplDAO<CatTipoEmpleado, Integer> implements CatTipoEmpleadoDAO {

	@SuppressWarnings("unchecked")
	public List<CatTipoEmpleado> consultarAdministrador() {
		Criteria crit = HibernateUtil.openSession().createCriteria(CatTipoEmpleado.class);
		crit.add(Restrictions.eq("descripcion", Constantes.ADMINISTRADOR));
		List<CatTipoEmpleado> cats = crit.list();
		return cats;
	}
}
