package mx.com.desoft.hidrogas.dao;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import mx.com.desoft.hidrogas.ApplicationReporter;
import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.Empleado;

@Component
public class EmpleadosImplDAO extends HibernateImplDAO<Empleado, Long> implements EmpleadosDAO {
	
	
	private static final Logger LOGGER =
	        Logger.getLogger(EmpleadosImplDAO.class);
	     
	    //@Scheduled(fixedDelay = 10000)
	    public void reportApplication() {
	        LOGGER.info("La aplicacion esta corriendo.");
	    }
	    
	    public EmpleadosImplDAO() {
			
		}
	    
	   
	

}
