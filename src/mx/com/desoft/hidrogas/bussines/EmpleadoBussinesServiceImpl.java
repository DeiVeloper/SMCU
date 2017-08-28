package mx.com.desoft.hidrogas.bussines;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import mx.com.desoft.hidrogas.dao.CatTipoEmpleadoDAO;
import mx.com.desoft.hidrogas.dao.EconomicoDAO;
import mx.com.desoft.hidrogas.dao.EmpleadosDAO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.model.CatTipoEmpleado;
import mx.com.desoft.hidrogas.model.Economico;
import mx.com.desoft.hidrogas.model.Empleado;
import mx.com.desoft.hidrogas.property.EmpleadoProperty;

@Service
public class EmpleadoBussinesServiceImpl implements EmpleadoBussinesService {

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
		List<Empleado> empleado = empleadosImplDAO.getEmpleadoByVista(empleadoDTO);
		for (Empleado lista : empleado) {
			EmpleadoProperty dto = new EmpleadoProperty();
			dto.setNominaEmpleado(new SimpleStringProperty(String.valueOf(lista.getNominaEmpleado())));
			dto.setNombreEmpleado(new SimpleStringProperty(lista.getNombreEmpleado().concat(" ").concat(lista.getApellidoPatEmpleado()+"").concat(" ")
					.concat(lista.getApellidoMatEmpleado()+"")));
			dto.setTipoEmpleado(new SimpleStringProperty(lista.getCatTipoEmpleado().getDescripcion()));
			dto.setEconomicoId(new SimpleStringProperty(String.valueOf(lista.getEconomico().getEconomicoId())));
			listaDTO.add(dto);
		}
		return listaDTO;
	}

	private Empleado convertirDTOToEntidad(EmpleadoDTO empleadoDTO) {
		final CatTipoEmpleado catTipoEmpleado = catTipoEmpleadoImplDAO.get(empleadoDTO.getTipoEmpleadoId());
		final Economico economico = economicoImplDAO.get(empleadoDTO.getEconomicoId());
		final Empleado empleado = new Empleado(empleadoDTO.getNominaEmpleado(), empleadoDTO.getNombreEmpleado(), empleadoDTO.getApellidoPatEmpleado(), 
				empleadoDTO.getApellidoMatEmpleado(), empleadoDTO.getPassword(),economico, catTipoEmpleado, 
				empleadoDTO.getFechaRegistro(), empleadoDTO.getNominaRegistro());
		return empleado;
	}
}
