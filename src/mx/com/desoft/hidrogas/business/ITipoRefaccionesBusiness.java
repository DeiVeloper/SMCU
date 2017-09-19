package mx.com.desoft.hidrogas.business;

import java.io.Serializable;
import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.property.TipoRefaccionProperty;

public interface ITipoRefaccionesBusiness extends Serializable {

	void guardarTipoRefaccion(CatTipoRefaccionesDTO catTipoRefaccionesDTO);

	List<TipoRefaccionProperty> getTipoRefaccionByView(CatTipoRefaccionesDTO catTipoRefaccionesDTO);

	void eliminarTipoRefaccionById(Integer id);

}
