package mx.com.desoft.hidrogas.business;

import java.io.Serializable;
import java.util.List;

import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.property.EmpleadoProperty;

public interface IEmpleadoBusiness extends Serializable {

	void guardarEmpleado(EmpleadoDTO empleadoDTO);
	
	List<EmpleadoProperty> buscarEmpleadoByVista(EmpleadoDTO empleadoDTO);
	
	void eliminarEmpleado(Integer empleadoId);
}
