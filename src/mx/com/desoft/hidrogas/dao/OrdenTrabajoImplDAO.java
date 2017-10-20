package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class OrdenTrabajoImplDAO extends HibernateImplDAO<OrdenTrabajo, Integer> implements IOrdenTrabajoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenTrabajo> getOrdenByVista(OrdenTrabajoDTO ordenTrabajoDTO) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(OrdenTrabajo.class);
		if(ordenTrabajoDTO.getFolio() != Constantes.CERO) {
			criteria.add(Restrictions.eq("folio", ordenTrabajoDTO.getFolio()));
		}
		if(ordenTrabajoDTO.getFechaRegistro() != Constantes.NULL) {
			criteria.add(Restrictions.eq("fechaRegistro", ordenTrabajoDTO.getFechaRegistro()));
		}
		if(ordenTrabajoDTO.getTipoNecesidadId() != Constantes.NULL) {
			criteria.createAlias("catTipoNecesidad", "catTipoNecesidad");
			criteria.add(Restrictions.eq("catTipoNecesidad.tipoNecesidadId", ordenTrabajoDTO.getTipoNecesidadId()));
		}
		if(ordenTrabajoDTO.getNominaRegistro() != Constantes.CERO) {
			criteria.createAlias("empleado2", "empleado2");
			criteria.add(Restrictions.eq("empleado2.nominaEmpleado", ordenTrabajoDTO.getNominaRegistro()));
		}
		if(ordenTrabajoDTO.getEstatusOrden() != Constantes.CERO) {
			criteria.createAlias("catEstatusOrden", "catEstatusOrden");
			criteria.add(Restrictions.eq("catEstatusOrden.estatusOrdenId", ordenTrabajoDTO.getEstatusOrden()));
		}
		if(ordenTrabajoDTO.getEconomicoId() != Constantes.CERO) {
			criteria.createAlias("economico", "economico");
			criteria.add(Restrictions.eq("economico.economicoId", ordenTrabajoDTO.getEconomicoId()));
		}

		return (List<OrdenTrabajo>)criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenTrabajo> getIncidenciasOrden(OrdenTrabajoDTO ordenTrabajoDTO) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(OrdenTrabajo.class);
		criteria.add(Restrictions.between("fechaRegistro", ordenTrabajoDTO.getFechaInicio(), ordenTrabajoDTO.getFechaFin()));
		if	(ordenTrabajoDTO.getTipoNecesidadId() != Constantes.NULL)	{
			criteria.createAlias("catTipoNecesidad", "catTipoNecesidad");
			criteria.add(Restrictions.eq("catTipoNecesidad.tipoNecesidadId", ordenTrabajoDTO.getTipoNecesidadId()));
		}

		if	(ordenTrabajoDTO.getNominaRegistro() != Constantes.CERO)	{
			criteria.createAlias("empleado2", "empleado2");
			criteria.add(Restrictions.eq("empleado2.nominaEmpleado", ordenTrabajoDTO.getNominaRegistro()));
		}

		if	(ordenTrabajoDTO.getEstatusOrden() != Constantes.CERO)	{
			criteria.createAlias("catEstatusOrden", "catEstatusOrden");
			criteria.add(Restrictions.eq("catEstatusOrden.estatusOrdenId", ordenTrabajoDTO.getEstatusOrden()));
		}
		if	(ordenTrabajoDTO.getEconomicoId() != Constantes.CERO)	{
			criteria.createAlias("economico", "economico");
			criteria.add(Restrictions.eq("economico.economicoId", ordenTrabajoDTO.getEconomicoId()));
		}
		criteria.addOrder(Order.asc("folio"));

		return criteria.list() != null ? (List<OrdenTrabajo>)criteria.list() : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> getOrdenByTipoNecesidad(OrdenTrabajoDTO ordenTrabajoDTO) {
		StringBuilder consulta = new StringBuilder();
		consulta.append("SELECT ordenTrabajo.economico.economicoId, catTipoNecesidad.descripcion, count(*) FROM OrdenTrabajo ordenTrabajo, CatTipoNecesidad catTipoNecesidad");
				consulta.append(" where ordenTrabajo.catTipoNecesidad.tipoNecesidadId = catTipoNecesidad.tipoNecesidadId");
				consulta.append(" group by ordenTrabajo.economico.economicoId, catTipoNecesidad.descripcion");
		Query query = HibernateUtil.openSession().createQuery(consulta.toString());
		List<Object[]> listDatos = query.list();
		return listDatos != null ? listDatos : null;
	}

}
