package mx.com.desoft.hidrogas.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.model.ListaRefacciones;
import mx.com.desoft.hidrogas.model.SeguimientoOrden;
import mx.com.desoft.hidrogas.util.Constantes;
import mx.com.desoft.hidrogas.util.DateUtil;

@Repository
public class SeguimientoOrdenImplDAO extends HibernateImplDAO<SeguimientoOrden, Integer> implements ISeguimientoOrdenDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ListaRefacciones> getListaRefaccionesByFolioTipo(int folio, int tipo) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(ListaRefacciones.class);
		if(folio > Constantes.CERO) {
			criteria.createAlias("ordenTrabajo", "ordenTrabajo");
			criteria.add(Restrictions.eq("ordenTrabajo.folio", folio));
		}
		if(tipo > Constantes.CERO) {
			criteria.createAlias("catTipoListaRefaccion", "catTipoListaRefaccion");
			criteria.add(Restrictions.eq("catTipoListaRefaccion.tipoRefaccionId", tipo));
		}
		return (List<ListaRefacciones>)criteria.list();
	}

	@Override
	public SeguimientoOrden getSeguimientoByFolio(int folio) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(SeguimientoOrden.class);
		if(folio > Constantes.CERO) {
			criteria.createAlias("ordenTrabajo", "ordenTrabajo");
			criteria.add(Restrictions.eq("ordenTrabajo.folio", folio));
		}
		return (SeguimientoOrden)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SeguimientoOrden> getOrdenesConReparacionMayor() {
		List<SeguimientoOrden> listaOrdenes = new ArrayList<>();
		Criteria criteria = HibernateUtil.openSession().createCriteria(SeguimientoOrden.class);
		criteria.add(Restrictions.le("fechaReparaMayor", DateUtil.plusDaysToDate(new Date(), 7L)));
		criteria.createAlias("ordenTrabajo", "ordenTrabajo");
		criteria.add(Restrictions.eq("ordenTrabajo.catEstatusOrden.estatusOrdenId", Constantes.ESTATUS_ORDEN_ACTIVA));
		listaOrdenes = (List<SeguimientoOrden>)criteria.list();
		return listaOrdenes;
	}
}
