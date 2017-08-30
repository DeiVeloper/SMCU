package mx.com.desoft.hidrogas.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;

@Repository
public class AgregarEditarOrdenImplDAO extends HibernateImplDAO<OrdenTrabajo, Long> implements AgregarEditarOrdenDAO {

	@Override
	public List<Economico> buscarEconomicos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CatTipoNecesidadDTO> buscarTiposNecesidad() {
		return null;
	}

}
