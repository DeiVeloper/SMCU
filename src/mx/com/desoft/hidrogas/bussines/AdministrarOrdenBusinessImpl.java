package mx.com.desoft.hidrogas.bussines;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.dao.CatEstatusOrdenDAO;
import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;
import mx.com.desoft.hidrogas.model.CatEstatusOrden;

@Service
public class AdministrarOrdenBusinessImpl implements IAdministrarOrdenBusiness {

	@Autowired
	private CatEstatusOrdenDAO catEstatusOrdenDAO;

	@Override
	public List<CatEstatusOrdenDTO> buscarTiposEstatus() {
		List<CatEstatusOrdenDTO> listaEstatus = new ArrayList<CatEstatusOrdenDTO>();
		List<CatEstatusOrden> listaTipoEstatusModel = catEstatusOrdenDAO.findAll();
		for (final CatEstatusOrden tipoEstatus : listaTipoEstatusModel) {
			listaEstatus.add(new CatEstatusOrdenDTO(tipoEstatus.getEstatusOrdenId(), tipoEstatus.getDescripcion()));
		}
		return listaEstatus;
	}

}
