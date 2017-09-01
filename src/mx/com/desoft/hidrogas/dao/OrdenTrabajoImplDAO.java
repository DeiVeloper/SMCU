package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.hibernate.Criteria;
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
		if	(ordenTrabajoDTO.getFechaRegistro() != Constantes.NULL)	{
			criteria.add(Restrictions.eq("fechaRegistro", ordenTrabajoDTO.getFechaRegistro()));
		}

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

		return (List<OrdenTrabajo>)criteria.list();
	}

}
