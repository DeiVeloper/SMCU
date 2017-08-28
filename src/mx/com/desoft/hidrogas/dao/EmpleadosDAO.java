package mx.com.desoft.hidrogas.dao;

import java.util.List;

import mx.com.desoft.hidrogas.dto.EmpleadoDTO;
import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.Empleado;


public interface EmpleadosDAO extends HibernateDAO<Empleado, Integer> {
	
	Boolean getUsuarioByCretential(Integer usuario, String password);
	
	List<Empleado> getEmpleadoByVista(EmpleadoDTO empleadoDTO);
	

}
