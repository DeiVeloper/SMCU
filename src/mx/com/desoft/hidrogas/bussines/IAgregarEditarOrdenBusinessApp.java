package mx.com.desoft.hidrogas.bussines;

import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
//import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;

public interface IAgregarEditarOrdenBusinessApp {

	void AgregarOrden();

	List<CatTipoNecesidadDTO> buscarTiposNecesidad ();

	void guardarOrden(OrdenTrabajoDTO ordenTrabajoTO);

}
