package mx.com.desoft.hidrogas.business;

import java.io.Serializable;
import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.property.TipoNecesidadProperty;

public interface ITipoNecesidadBusiness extends Serializable {

	void guardarTipoNecesidad(CatTipoNecesidadDTO catTipoNecesidadDTO);

	List<TipoNecesidadProperty> getTipoNecesidadByView(CatTipoNecesidadDTO catTipoNecesidadDTO);

	void eliminarTipoNecesidadById(Integer id);
}
