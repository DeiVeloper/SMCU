package mx.com.desoft.hidrogas.bussines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.dao.EmpleadosDAO;

@Service
public class LoginBussinesServiceImpl implements LoginBussinesService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private EmpleadosDAO empleadosImplDAO;

	@Override
	public Boolean validarsesion(Integer usuario, String password) {
		return empleadosImplDAO.getUsuarioByCretential(usuario, password);
	}

}
