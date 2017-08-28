package mx.com.desoft.hidrogas.bussines;

import java.io.Serializable;


public interface LoginBussinesService extends Serializable {

	Boolean validarsesion(Integer usuario, String password);
}
