package mx.com.desoft.hidrogas.bussines;

import java.io.Serializable;
import java.util.List;

import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.property.EmpleadoProperty;

public interface EmpleadoBussinesService extends Serializable {

	void guardarEmpleado(EmpleadoDTO empleadoDTO);
	
	List<EmpleadoProperty> buscarEmpleadoByVista(EmpleadoDTO empleadoDTO);
	
}
