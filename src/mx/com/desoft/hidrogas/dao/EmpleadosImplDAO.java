package mx.com.desoft.hidrogas.dao;

import java.util.List;

//import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.Empleado;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class EmpleadosImplDAO extends HibernateImplDAO<Empleado, Integer> implements EmpleadosDAO {

	private static final String NOMINA_EMPLEADO = "nominaEmpleado";
	private static final String CAT_TIPO_EMPLEADO = "catTipoEmpleado";
	private static final String CAT_TIPO_EMPLEADO_DESCRIPCION = "catTipoEmpleado.descripcion";
	@Override
	public Boolean getUsuarioByCredential(Integer usuario, String password) {
		 Criteria criteria = HibernateUtil.openSession().createCriteria(Empleado.class);
		 criteria.add(Restrictions.eq(NOMINA_EMPLEADO, usuario)).add(Restrictions.eq("password", password));
		 criteria.createAlias(CAT_TIPO_EMPLEADO, CAT_TIPO_EMPLEADO);
		 String [] restricciones = {Constantes.ADMINISTRADOR,Constantes.MECANICO,Constantes.JEFETALLER}; 
		 criteria.add(Restrictions.in(CAT_TIPO_EMPLEADO_DESCRIPCION,restricciones));
		 return criteria.uniqueResult() != Constantes.NULL ? true : false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> getEmpleadoByVista(EmpleadoDTO empleadoDTO) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(Empleado.class);
		if	(empleadoDTO.getNominaEmpleado() != Constantes.NULL )	{
			criteria.add(Restrictions.eq(NOMINA_EMPLEADO, empleadoDTO.getNominaEmpleado()));
		}

		if	(empleadoDTO.getNombreEmpleado() != Constantes.NULL && !empleadoDTO.getNombreEmpleado().isEmpty())	{
			criteria.add(Restrictions.eq(NOMINA_EMPLEADO, empleadoDTO.getNombreEmpleado()));
		}

		if	(empleadoDTO.getDescripcionTipoEmpleado() != Constantes.NULL)	{
			criteria.createAlias(CAT_TIPO_EMPLEADO, CAT_TIPO_EMPLEADO);
			criteria.add(Restrictions.eq(CAT_TIPO_EMPLEADO_DESCRIPCION, empleadoDTO.getDescripcionTipoEmpleado()));
		}

		if	(empleadoDTO.getEconomicoId() != Constantes.NULL)	{
			criteria.createAlias("economico", "economico");
			criteria.add(Restrictions.eq("economico.economicoId", empleadoDTO.getEconomicoId()));
		}
		criteria.addOrder(Order.asc(NOMINA_EMPLEADO));

		return (List<Empleado>)criteria.list();
	}


	public Empleado getEmpleadoByNomina(int nomina) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(Empleado.class);
		if(nomina > 0) {
			criteria.add(Restrictions.eq(NOMINA_EMPLEADO, nomina));
		}
		return (Empleado)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Empleado> getAllOperadores() {
		Criteria criteria = HibernateUtil.openSession().createCriteria(Empleado.class);
		criteria.createAlias(CAT_TIPO_EMPLEADO, CAT_TIPO_EMPLEADO);
		criteria.add(Restrictions.eq(CAT_TIPO_EMPLEADO_DESCRIPCION, Constantes.OPERADOR));
		return (List<Empleado>)criteria.list();
	}

}
