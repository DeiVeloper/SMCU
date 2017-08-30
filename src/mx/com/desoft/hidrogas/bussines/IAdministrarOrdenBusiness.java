package mx.com.desoft.hidrogas.bussines;

import java.util.List;

import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;

public interface IAdministrarOrdenBusiness {

	List<CatEstatusOrdenDTO> buscarTiposEstatus();

}
