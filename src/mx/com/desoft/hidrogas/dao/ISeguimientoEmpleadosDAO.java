package mx.com.desoft.hidrogas.dao;

import java.util.List;

import mx.com.desoft.hidrogas.hibernate.HibernateDAO;
import mx.com.desoft.hidrogas.model.SeguimientosEmpleado;
import mx.com.desoft.hidrogas.model.SeguimientosEmpleadoPK;

public interface ISeguimientoEmpleadosDAO extends HibernateDAO<SeguimientosEmpleado, SeguimientosEmpleadoPK>{

	List<SeguimientosEmpleado> getListaEmpleadosBySeguimiento(int seguimiento);



}
