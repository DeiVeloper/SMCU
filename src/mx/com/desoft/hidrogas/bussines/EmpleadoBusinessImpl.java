package mx.com.desoft.hidrogas.bussines;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mx.com.desoft.hidrogas.dao.CatTipoEmpleadoDAO;
import mx.com.desoft.hidrogas.dao.EconomicoDAO;
import mx.com.desoft.hidrogas.dao.EmpleadosDAO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.model.CatTipoEmpleado;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.model.Empleado;
import mx.com.desoft.hidrogas.property.EmpleadoProperty;
import mx.com.desoft.hidrogas.util.Constantes;

@Service
public class EmpleadoBusinessImpl implements IEmpleadoBusiness {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private EmpleadosDAO empleadosImplDAO;
	
	@Autowired
	private EconomicoDAO economicoImplDAO;
	
	@Autowired
	private CatTipoEmpleadoDAO catTipoEmpleadoImplDAO;
	
	@Override
	public void guardarEmpleado(EmpleadoDTO empleadoDTO) {
		empleadosImplDAO.saveOrUpdate(this.convertirDTOToEntidad(empleadoDTO));

	}

	@Override
	public List<EmpleadoProperty> buscarEmpleadoByVista(EmpleadoDTO empleadoDTO) {
		List<EmpleadoProperty> listaDTO = new ArrayList<>();
		Image imageEditar = new Image("file:resources/images/edit.png");
		Image imageEliminar = new Image("file:resources/images/delete.png");
		List<Empleado> empleado = empleadosImplDAO.getEmpleadoByVista(empleadoDTO);
		for (Empleado lista : empleado) {
			EmpleadoProperty dto = new EmpleadoProperty(new SimpleStringProperty(String.valueOf(lista.getNominaEmpleado())),
					new SimpleStringProperty(lista.getNombreEmpleado()),
					new SimpleStringProperty(lista.getApellidoPatEmpleado()),
					new SimpleStringProperty(lista.getApellidoMatEmpleado()),
					new SimpleStringProperty(lista.getPassword()),
					new SimpleStringProperty(lista.getEconomico() != Constantes.NULL ? String.valueOf(lista.getEconomico().getEconomicoId()) : " - "),
					new SimpleStringProperty(lista.getFechaRegistro().toString()),
					new SimpleStringProperty(String.valueOf(lista.getNominaRegistro())),
					new SimpleStringProperty(String.valueOf(lista.getCatTipoEmpleado().getTipoEmpleadoId())),
					new SimpleStringProperty(lista.getCatTipoEmpleado().getDescripcion()),
					new Button("", new ImageView(imageEditar)),
					new Button("", new ImageView(imageEliminar))
					);
			listaDTO.add(dto);
		}
		return listaDTO;
	}

	private Empleado convertirDTOToEntidad(EmpleadoDTO empleadoDTO) {
		final CatTipoEmpleado catTipoEmpleado = catTipoEmpleadoImplDAO.get(empleadoDTO.getTipoEmpleadoId());
		final Economico economico = empleadoDTO.getEconomicoId() != Constantes.NULL ? economicoImplDAO.get(empleadoDTO.getEconomicoId()) : null;
		final Empleado empleado = new Empleado(empleadoDTO.getNominaEmpleado(), empleadoDTO.getNombreEmpleado(), empleadoDTO.getApellidoPatEmpleado(), 
				empleadoDTO.getApellidoMatEmpleado(), empleadoDTO.getPassword(),economico, catTipoEmpleado, 
				empleadoDTO.getFechaRegistro(), empleadoDTO.getNominaRegistro());
		return empleado;
	}

	@Override
	public void eliminarEmpleado(Integer empleadoId) {
		empleadosImplDAO.delete(empleadoId);
	}
}
