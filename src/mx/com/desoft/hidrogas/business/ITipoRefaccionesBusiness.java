package mx.com.desoft.hidrogas.business;

import java.io.Serializable;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;

import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.property.TipoRefaccionProperty;

public interface ITipoRefaccionesBusiness extends Serializable {

	void guardarTipoRefaccion(CatTipoRefaccionesDTO catTipoRefaccionesDTO);

	List<TipoRefaccionProperty> getTipoRefaccionByView(CatTipoRefaccionesDTO catTipoRefaccionesDTO);

	void eliminarTipoRefaccionById(Integer id);

	void guardarRefaccion(CatTipoRefaccionesDTO catTipoRefaccionesDTO);

	List<TipoRefaccionProperty> getRefaccionByView(CatTipoRefaccionesDTO catTipoRefaccionesDTO);

	void eliminarRefaccionById(Integer id) throws ConstraintViolationException;

}
