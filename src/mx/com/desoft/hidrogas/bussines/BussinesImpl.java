package mx.com.desoft.hidrogas.bussines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.dao.EmpleadosDAO;
import mx.com.desoft.hidrogas.model.Empleado;

@Service
public class BussinesImpl implements BussinesApp {
	
	@Autowired
	private EmpleadosDAO empleadosImplDAO;

	@Override
	public void agregarEMplado() {
		Empleado emp = new Empleado(1,"David");
		empleadosImplDAO.saveOrUpdate(emp);
		
	}

	public EmpleadosDAO getEmpleadosImplDAO() {
		return empleadosImplDAO;
	}

	public void setEmpleadosImplDAO(EmpleadosDAO empleadosImplDAO) {
		this.empleadosImplDAO = empleadosImplDAO;
	}

	

}
