package mx.com.desoft.hidrogas.business;

import java.util.List;

import mx.com.desoft.hidrogas.dto.SeguimientoOrdenDTO;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;
import mx.com.desoft.hidrogas.property.SeguimientoOrdenPartesProperty;

public interface ISeguimientoOrdenBusiness {

	void guardarSeguimiento(SeguimientoOrdenDTO seguimientoOrdenTO);

	SeguimientoOrdenDTO getSeguimientoOrdenByFolio(int folio);

	List<SeguimientoOrdenPartesProperty> getListaPartesByFolioTipo(int folio, int tipo);

	boolean eliminaRefaccion(int idRefaccion);

	OrdenTrabajo getOrdenByFolio(int folio);

	List<SeguimientoOrdenDTO> getOrdenesConReparacionMayor();

}
