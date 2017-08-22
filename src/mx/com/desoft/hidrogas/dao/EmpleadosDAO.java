package mx.com.desoft.hidrogas.dao;



import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.Empleado;


public interface EmpleadosDAO extends HibernateDAO<Empleado, Long> {



}
