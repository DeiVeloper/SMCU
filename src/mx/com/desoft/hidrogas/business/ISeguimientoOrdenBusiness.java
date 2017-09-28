package mx.com.desoft.hidrogas.business;

import mx.com.desoft.hidrogas.dto.SeguimientoOrdenDTO;

public interface ISeguimientoOrdenBusiness {

	void guardarSeguimiento(SeguimientoOrdenDTO seguimientoOrdenTO);

	SeguimientoOrdenDTO getSeguimientoOrdenByFolio(int folio);

}
