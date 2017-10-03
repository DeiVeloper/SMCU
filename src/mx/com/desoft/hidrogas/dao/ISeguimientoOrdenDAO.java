package mx.com.desoft.hidrogas.dao;

import java.util.List;

import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.ListaRefacciones;
import mx.com.desoft.hidrogas.model.SeguimientoOrden;

public interface ISeguimientoOrdenDAO extends HibernateDAO<SeguimientoOrden, Integer> {

	List<ListaRefacciones> getListaRefaccionesByFolioTipo(int folio, int tipo);

	SeguimientoOrden getSeguimientoByFolio(int folio);

}
