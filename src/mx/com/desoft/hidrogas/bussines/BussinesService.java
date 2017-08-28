package mx.com.desoft.hidrogas.bussines;

import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;

public interface BussinesService {
	
	List<CatTipoEmpleadoDTO> findAllTipoEmpleados();
	
	List<EconomicoDTO> findAllEconomicos();

}
