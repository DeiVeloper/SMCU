package mx.com.desoft.hidrogas.dao;

import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;

public interface AgregarEditarOrdenDAO extends HibernateDAO<OrdenTrabajo, Long> {

	List<Economico> buscarEconomicos ();

	List<CatTipoNecesidadDTO> buscarTiposNecesidad ();

}
