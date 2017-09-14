package mx.com.desoft.hidrogas.dao;


import org.springframework.stereotype.Repository;

import mx.com.desoft.hidrogas.hibernate.HibernateImplDAO;
import mx.com.desoft.hidrogas.model.CatTipoEmpleado;

@Repository
public class CatTipoEmpleadoImplDAO extends HibernateImplDAO<CatTipoEmpleado, Integer> implements CatTipoEmpleadoDAO {

}
