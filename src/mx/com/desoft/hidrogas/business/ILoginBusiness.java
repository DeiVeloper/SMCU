package mx.com.desoft.hidrogas.business;

import java.io.Serializable;

import mx.com.desoft.hidrogas.dto.EmpleadoDTO;


public interface ILoginBusiness extends Serializable {

	Boolean validarSesion(Integer usuario, String password);
	
	EmpleadoDTO getEmpleadoSesion(int noNomina);
}
