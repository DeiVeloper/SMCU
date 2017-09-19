package mx.com.desoft.hidrogas.business;

import java.util.List;

import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.property.OrdenProperty;

public interface IAdministrarOrdenBusiness {

	List<CatEstatusOrdenDTO> buscarTiposEstatus();

	List<OrdenProperty> buscarOrdenByVista(OrdenTrabajoDTO ordenTrabajoDTO, MainApp mainApp);

}
