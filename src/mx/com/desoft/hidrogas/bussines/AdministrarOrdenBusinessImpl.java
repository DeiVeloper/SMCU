package mx.com.desoft.hidrogas.bussines;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import mx.com.desoft.hidrogas.MainApp;
import mx.com.desoft.hidrogas.dao.CatEstatusOrdenDAO;
import mx.com.desoft.hidrogas.dao.IOrdenTrabajoDAO;
import mx.com.desoft.hidrogas.dto.CatEstatusOrdenDTO;
import mx.com.desoft.hidrogas.dto.OrdenTrabajoDTO;
import mx.com.desoft.hidrogas.model.CatEstatusOrden;
import mx.com.desoft.hidrogas.model.OrdenTrabajo;
import mx.com.desoft.hidrogas.property.OrdenProperty;

@Service
public class AdministrarOrdenBusinessImpl implements IAdministrarOrdenBusiness {

	@Autowired
	private CatEstatusOrdenDAO catEstatusOrdenDAO;

	@Autowired
	private IOrdenTrabajoDAO ordenTrabajoImplDAO;

	@Override
	public List<CatEstatusOrdenDTO> buscarTiposEstatus() {
		List<CatEstatusOrdenDTO> listaEstatus = new ArrayList<CatEstatusOrdenDTO>();
		List<CatEstatusOrden> listaTipoEstatusModel = catEstatusOrdenDAO.findAll();
		for (final CatEstatusOrden tipoEstatus : listaTipoEstatusModel) {
			listaEstatus.add(new CatEstatusOrdenDTO(tipoEstatus.getEstatusOrdenId(), tipoEstatus.getDescripcion()));
		}
		return listaEstatus;
	}

	@Override
	public List<OrdenProperty> buscarOrdenByVista(OrdenTrabajoDTO ordenTrabajoDTO, MainApp mainApp) {
		List<OrdenProperty> listaDTO = new ArrayList<>();
		List<OrdenTrabajo> orden = ordenTrabajoImplDAO.getOrdenByVista(ordenTrabajoDTO);
		for (OrdenTrabajo lista : orden) {
			OrdenProperty dto = new OrdenProperty(new SimpleStringProperty(String.valueOf(lista.getFolio())), new SimpleStringProperty(String.valueOf(lista.getFechaRegistro())),
					new SimpleStringProperty(String.valueOf(lista.getEconomico().getEconomicoId())), new SimpleStringProperty(String.valueOf(lista.getEmpleado2().getNominaEmpleado())),
					new SimpleStringProperty(lista.getCatTipoNecesidad().getDescripcion()), new Button("Seguimiento"), mainApp);
			listaDTO.add(dto);
		}
		return listaDTO;
	}

}
