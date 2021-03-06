package mx.com.desoft.hidrogas.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		final Image imageSeguimiento = new Image("file:resources/images/seguimiento.png");
		for (OrdenTrabajo lista : orden) {
			OrdenProperty dto = new OrdenProperty(new SimpleStringProperty(String.valueOf(lista.getFolio())), new SimpleStringProperty(String.valueOf(lista.getFechaRegistro())),
					new SimpleStringProperty(String.valueOf(lista.getEconomico().getEconomicoId())),
					new SimpleStringProperty(lista.getEmpleado2().getNombreEmpleado() + " " + lista.getEmpleado2().getApellidoPatEmpleado() + " " + lista.getEmpleado2().getApellidoMatEmpleado()),
					new SimpleStringProperty(lista.getCatTipoNecesidad().getDescripcion()), lista.getEmpleado2().getNominaEmpleado(),lista.getCatEstatusOrden().getEstatusOrdenId(), new Button("", new ImageView(imageSeguimiento)), mainApp);
			listaDTO.add(dto);
		}
		return listaDTO;
	}

}
