package mx.com.desoft.hidrogas.dao;

import java.io.Serializable;
import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.CatTipoNecesidad;

public interface CatTipoNecesidadDAO extends HibernateDAO<CatTipoNecesidad, Long>, Serializable {

	List<CatTipoNecesidad> getTipoNecesidadByView(CatTipoNecesidadDTO catTipoNecesidadDTO);
}
