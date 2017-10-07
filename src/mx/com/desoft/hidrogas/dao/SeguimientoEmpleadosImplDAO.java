package mx.com.desoft.hidrogas.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.model.SeguimientosEmpleado;
import mx.com.desoft.hidrogas.model.SeguimientosEmpleadoPK;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class SeguimientoEmpleadosImplDAO extends HibernateImplDAO<SeguimientosEmpleado, SeguimientosEmpleadoPK> implements ISeguimientoEmpleadosDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<SeguimientosEmpleado> getListaEmpleadosBySeguimiento(int seguimiento) {
		List<SeguimientosEmpleado> listaEmpleados = new ArrayList<>();
		Criteria criteria = HibernateUtil.openSession().createCriteria(SeguimientosEmpleado.class);
		if(seguimiento > Constantes.CERO) {
			criteria.add(Restrictions.eq("id.idSeguimiento", seguimiento));
			listaEmpleados = (List<SeguimientosEmpleado>)criteria.list();
		}
		return listaEmpleados;
	}

}
