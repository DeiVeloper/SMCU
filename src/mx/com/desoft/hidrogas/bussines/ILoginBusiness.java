package mx.com.desoft.hidrogas.bussines;

import java.io.Serializable;


public interface ILoginBusiness extends Serializable {

	Boolean validarSesion(Integer usuario, String password);
}
