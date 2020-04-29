package mx.com.desoft.hidrogas.business;

import java.util.List;

import mx.com.desoft.hidrogas.dto.CatTipoRefaccionesDTO;
import mx.com.desoft.hidrogas.dto.CatTipoEmpleadoDTO;
import mx.com.desoft.hidrogas.dto.CatTipoNecesidadDTO;
import mx.com.desoft.hidrogas.dto.EconomicoDTO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.dto.TipoReporteDTO;
import mx.com.desoft.hidrogas.model.CatTipoRefaccion;

public interface ICatalogoBusiness {

	List<CatTipoEmpleadoDTO> findAllTipoEmpleados();

	List<EconomicoDTO> findAllEconomicos();

	List<CatTipoNecesidadDTO> findAllTipoNecesidad();

	List<CatTipoRefaccionesDTO> findAllTipoRefacciones();

	CatTipoEmpleadoDTO getById(int tipoEmpleadoId);

	List<TipoReporteDTO> findAllTipoReporte();

	void imprimirReporte(OrdenTrabajoDTO ordenTrabajoDTO);

	List<CatTipoRefaccionesDTO> findAllCatTipoRefacciones();

	List<EmpleadoDTO> findAllOperadores();

	OrdenTrabajoDTO getOrdenById(Integer folio);

	List<OrdenTrabajoDTO> getIncidenciasOrdenes(OrdenTrabajoDTO ordenTrabajoDTO);

	List<OrdenTrabajoDTO> getOrdenByTipoNecesidad(OrdenTrabajoDTO ordenTrabajoDTO);
	
	CatTipoRefaccion obtieneTipoRefaccion(int idTipoRefaccion);

}
