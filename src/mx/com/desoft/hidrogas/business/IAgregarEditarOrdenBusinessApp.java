package mx.com.desoft.hidrogas.business;

import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
//import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.model.Empleado;

public interface IAgregarEditarOrdenBusinessApp {

	void AgregarOrden();

	List<CatTipoNecesidadDTO> buscarTiposNecesidad ();

	void guardarOrden(OrdenTrabajoDTO ordenTrabajoTO);

	Empleado getEmpleadoByNomina(int nomina);

	boolean eliminaOrden(int folioOrden);

}
