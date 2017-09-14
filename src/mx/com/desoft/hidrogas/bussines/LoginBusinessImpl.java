package mx.com.desoft.hidrogas.bussines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.dao.EmpleadosDAO;

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

}
