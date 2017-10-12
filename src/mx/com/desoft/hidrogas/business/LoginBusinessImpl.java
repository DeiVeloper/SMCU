package mx.com.desoft.hidrogas.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.dao.EmpleadosDAO;
import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.model.Empleado;

@Service
public class LoginBusinessImpl implements ILoginBusiness {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EmpleadosDAO empleadosImplDAO;

	@Override
	public Boolean validarSesion(Integer usuario, String password) {
		return empleadosImplDAO.getUsuarioByCredential(usuario, password);
	}

	@Override
	public EmpleadoDTO getEmpleadoSesion(int noNomina) {
		return this.convertirEntidadToDTO(empleadosImplDAO.getEmpleadoByNomina(noNomina));
	}
	
	private EmpleadoDTO convertirEntidadToDTO(Empleado empleado){
		return new EmpleadoDTO(empleado.getNominaEmpleado(), 
				empleado.getNombreEmpleado(), 
				empleado.getApellidoPatEmpleado(), 
				empleado.getApellidoMatEmpleado(), 
				null, 
				empleado.getEconomico() != null ? empleado.getEconomico().getEconomicoId() : null, 
				null, 
				empleado.getNominaRegistro(), 
				empleado.getCatTipoEmpleado().getTipoEmpleadoId(), 
				empleado.getCatTipoEmpleado().getDescripcion());
	}

}
