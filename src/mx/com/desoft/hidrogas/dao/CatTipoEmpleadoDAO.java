package mx.com.desoft.hidrogas.dao;

import java.util.List;

import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.CatTipoEmpleado;

public interface CatTipoEmpleadoDAO extends HibernateDAO<CatTipoEmpleado, Integer> {

	public List<CatTipoEmpleado> consultarAdministrador();
}
