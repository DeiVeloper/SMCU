package mx.com.desoft.hidrogas.dao;

import java.util.List;

//import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.Empleado;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class EmpleadosImplDAO extends HibernateImplDAO<Empleado, Integer> implements EmpleadosDAO {

//	 private static final Logger log = Logger.getLogger(EmpleadosImplDAO.class);

	@Override
	public Boolean getUsuarioByCredential(Integer usuario, String password) {
		 Criteria criteria = HibernateUtil.openSession().createCriteria(Empleado.class);
		 criteria.add(Restrictions.eq("nominaEmpleado", usuario)).add(Restrictions.eq("password", password));
		 criteria.createAlias("catTipoEmpleado", "catTipoEmpleado");
		 criteria.add(Restrictions.or(Restrictions.eq("catTipoEmpleado.descripcion", Constantes.ADMINISTRADOR),
				 Restrictions.eq("catTipoEmpleado.descripcion", Constantes.MECANICO)));
		 return criteria.uniqueResult() != Constantes.NULL ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> getEmpleadoByVista(EmpleadoDTO empleadoDTO) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(Empleado.class);
		if	(empleadoDTO.getNominaEmpleado() != Constantes.NULL )	{
			criteria.add(Restrictions.eq("nominaEmpleado", empleadoDTO.getNominaEmpleado()));
		}

		if	(empleadoDTO.getNombreEmpleado() != Constantes.NULL && !empleadoDTO.getNombreEmpleado().isEmpty())	{
			criteria.add(Restrictions.eq("nombreEmpleado", empleadoDTO.getNombreEmpleado()));
		}

		if	(empleadoDTO.getDescripcionTipoEmpleado() != Constantes.NULL)	{
			criteria.createAlias("catTipoEmpleado", "catTipoEmpleado");
			criteria.add(Restrictions.eq("catTipoEmpleado.descripcion", empleadoDTO.getDescripcionTipoEmpleado()));
		}

		if	(empleadoDTO.getEconomicoId() != Constantes.NULL)	{
			criteria.createAlias("economico", "economico");
			criteria.add(Restrictions.eq("economico.economicoId", empleadoDTO.getEconomicoId()));
		}

		return (List<Empleado>)criteria.list();
	}


	public Empleado getEmpleadoByNomina(int nomina) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(Empleado.class);
		if(nomina > 0) {
			criteria.add(Restrictions.eq("nominaEmpleado", nomina));
		}
		return (Empleado)criteria.uniqueResult();
	}

}
