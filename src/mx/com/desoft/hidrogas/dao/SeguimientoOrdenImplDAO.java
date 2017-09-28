package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.HibernateUtil;
import mx.com.desoft.hidrogas.model.ListaRefacciones;
import mx.com.desoft.hidrogas.model.SeguimientoOrden;
import mx.com.desoft.hidrogas.util.Constantes;

@Repository
public class SeguimientoOrdenImplDAO extends HibernateImplDAO<SeguimientoOrden, Integer> implements ISeguimientoOrdenDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<ListaRefacciones> getListaRefaccionesByFolioTipo(int folio, int tipo) {
		Criteria criteria = HibernateUtil.openSession().createCriteria(ListaRefacciones.class);
		if(folio > Constantes.CERO) {
			criteria.add(Restrictions.eq("folio", folio));
		}
		if(tipo > Constantes.CERO) {
			criteria.createAlias("catTipoListaRefaccion", "catTipoListaRefaccion");
			criteria.add(Restrictions.eq("catTipoListaRefaccion.tipoRefaccionId", tipo));
		}
		return (List<ListaRefacciones>)criteria.list();
	}

}
