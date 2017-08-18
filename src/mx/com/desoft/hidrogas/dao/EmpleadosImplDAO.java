package mx.com.desoft.hidrogas.dao;


import org.springframework.stereotype.Component;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.Empleado;

@Component
public class EmpleadosImplDAO extends HibernateImplDAO<Empleado, Long> implements EmpleadosDAO {

	

}
