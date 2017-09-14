package mx.com.desoft.hidrogas.bussines;

import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;

public interface ICatalogoBusiness {
	
	List<CatTipoEmpleadoDTO> findAllTipoEmpleados();
	
	List<EconomicoDTO> findAllEconomicos();

	List<CatTipoNecesidadDTO> findAllTipoNecesidad();
	
	List<CatTipoRefaccionesDTO> findAllTipoRefacciones();
	
	CatTipoEmpleadoDTO getById(int tipoEmpleadoId);
	
}
