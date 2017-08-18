package mx.com.desoft.hidrogas.dao;


import org.springframework.stereotype.Component;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.Empleados;

@Component
public class EmpleadosImplDAO extends HibernateImplDAO<Empleados, Long> implements EmpleadosDAO {

	

}
